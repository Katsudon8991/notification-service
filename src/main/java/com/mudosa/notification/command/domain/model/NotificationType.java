package com.mudosa.notification.command.domain.model;

/**
 * <p>
 *     알림의 작성 주체
 * </p>
 */
public enum NotificationType {
    USER("유저"),
    ADMIN("어드민"),
    BRAND("브랜드");

    private  String description;

    NotificationType(String description) {this.description = description;}
}
