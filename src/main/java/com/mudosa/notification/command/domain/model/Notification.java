package com.mudosa.notification.command.domain.model;

import com.mudosa.common.domain.BaseEntity;
import com.mudosa.user.domain.model.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import java.time.LocalDateTime;

@Entity
@Table(name="notification")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class Notification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="notification_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="n_metadata_id")
    private NotificationMetadata notificationMetadata;

    @Column(name="notification_title",nullable = false)
    private String title;

    @Column(name="notification_message",nullable = false)
    private String message;

    @Column(name="notification_url",nullable = false)
    private String url;

    @Column(name="notification_status",nullable = false)
    private Boolean status;

    @Column(name="read_at", nullable = true)
    private LocalDateTime readAt;

    @Builder
    public Notification(User user, NotificationMetadata notificationMetadata, String title, String message, String url) {
        this.user = user;
        this.notificationMetadata = notificationMetadata;
        this.title = title;
        this.message = message;
        this.url = url;
        this.status = Boolean.FALSE;
    }
}
