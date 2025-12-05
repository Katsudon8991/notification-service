package com.mudosa.notification.command.application;


import com.mudosa.notification.command.domain.model.Notification;
import com.mudosa.notification.command.domain.model.NotificationMetadata;
import com.mudosa.notification.command.domain.repository.NotificationMetadataRepository;
import com.mudosa.notification.command.domain.repository.NotificationRepository;
import com.mudosa.notification.command.interfaces.event.ChatNotificationCreatedEvent;
import com.mudosa.notification.command.interfaces.event.NotificationEventPublisher;
import com.mudosa.user.domain.model.User;
import com.mudosa.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class NotificationCommandService {

    private final NotificationRepository notificationRepository;
    private final NotificationMetadataRepository notificationMetadataRepository;
    private final NotificationEventPublisher eventPublisher;
    private final UserRepository userRepository;

    @Transactional
    public void createChatNotification(ChatNotificationCreatedEvent event){

        List<User> users = userRepository.findByIdIn(event.userIds());
        NotificationMetadata notificationMetadata = notificationMetadataRepository.findByCategory("CHAT").orElseThrow(
                ()-> new NoSuchElementException("NotificationMetadata not found")
                );

        List<Notification> notifications = users.stream().map(u->Notification.builder()
                .notificationMetadata(notificationMetadata)
                .user(u)
                .url(notificationMetadata.getUrl()+event.chatId())
                .title(notificationMetadata.getTitle())
                .message(notificationMetadata.getMessage())
                .build()
        ).toList();

        notificationRepository.saveAll(notifications);

        eventPublisher.publishNotificationCreated(notifications);
    }
}
