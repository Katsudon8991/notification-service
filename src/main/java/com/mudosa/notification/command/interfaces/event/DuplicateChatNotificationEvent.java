package com.mudosa.notification.command.interfaces.event;

import com.mudosa.notification.command.domain.model.Notification;

import java.util.List;

public record DuplicateChatNotificationEvent(List<Notification> notifications) {
}
