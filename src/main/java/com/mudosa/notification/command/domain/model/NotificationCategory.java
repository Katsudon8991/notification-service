package com.mudosa.notification.command.domain.model;

import lombok.Getter;

@Getter
public enum NotificationCategory {

    OUT_OF_STOCK("재고부족"),
    RESTOCK("재입고"),
    SETTLEMENT("정산완료"),
    CHAT("채팅");

    private final String description;

    NotificationCategory(String description) {this.description = description;}
}