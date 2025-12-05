package com.mudosa.notification.command.interfaces.event;

import java.util.List;

public record ChatNotificationCreatedEvent(List<Long> userIds, Long chatId) {
}
