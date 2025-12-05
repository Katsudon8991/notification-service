package com.mudosa.notification.command.interfaces.event;

import com.mudosa.notification.command.application.FcmService;
import com.mudosa.notification.command.application.NotificationCommandService;
import com.mudosa.notification.command.domain.model.FirebaseToken;
import com.mudosa.notification.command.domain.model.Notification;
import com.mudosa.notification.command.domain.repository.FirebaseTokenRepository;
import com.mudosa.notification.query.application.NotificationSyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationEventListener {

    private final NotificationCommandService notificationCommandService;
    private final FcmService fcmService;
    private final FirebaseTokenRepository firebaseTokenRepository;
    private final NotificationSyncService notificationSyncService;


    @KafkaListener(topics = "chat-notification-events", groupId="notification-group")
    public void handleChatNotificationCreatedEvent(ChatNotificationCreatedEvent event){
        notificationCommandService.createChatNotification(event);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleNotificationCreatedAfterCommit(DuplicateChatNotificationEvent event) {
        List<Notification> notifications = event.notifications();
        if (notifications == null || notifications.isEmpty()) {
            log.info("No notifications to process after commit.");
            return;
        }

        // 1. Send FCM Push Notification
        List<Long> userIds = notifications.stream()
                .map(n -> n.getUser().getId())
                .distinct()
                .toList();
        List<FirebaseToken> firebaseTokens = firebaseTokenRepository.findByUserIdIn(userIds);

        if (!firebaseTokens.isEmpty()) {
            Notification firstNotification = notifications.get(0);
            fcmService.sendMessageByToken(
                    firstNotification.getTitle(),
                    firstNotification.getMessage(),
                    firebaseTokens
            );
        } else {
            log.warn("No Firebase tokens found for userIds: {}", userIds);
        }

        // 2. Sync to Redis
        notificationSyncService.syncNotificationView(event);
    }
}
