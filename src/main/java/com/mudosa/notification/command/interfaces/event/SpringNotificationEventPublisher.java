package com.mudosa.notification.command.interfaces.event;

import com.mudosa.notification.command.domain.model.Notification;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Qualifier("notificationEventPublisher")
@AllArgsConstructor
@Component
public class SpringNotificationEventPublisher implements NotificationEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void publishNotificationCreated(List<Notification> notifications) {
        eventPublisher.publishEvent(new DuplicateChatNotificationEvent(notifications));
    }
}
