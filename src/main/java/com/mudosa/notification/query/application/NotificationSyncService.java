package com.mudosa.notification.query.application;

import com.mudosa.notification.command.interfaces.event.DuplicateChatNotificationEvent;
import com.mudosa.notification.query.dto.NotificationView;
import com.mudosa.notification.query.repository.NotificationViewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationSyncService {

    private final NotificationViewRepository notificationViewRepository;

    public void syncNotificationView(DuplicateChatNotificationEvent event){
        List<NotificationView> notificationViews = event.notifications().stream()
                .map(NotificationView::from) // NotificationView.from() 메서드를 사용하여 변환
                .toList();
        notificationViewRepository.saveAll(notificationViews);
        log.info("{}개의 알림을 Redis에 동기화했습니다.", notificationViews.size());
    }
}
