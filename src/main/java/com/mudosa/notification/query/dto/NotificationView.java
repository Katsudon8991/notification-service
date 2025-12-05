package com.mudosa.notification.query.dto;

import com.mudosa.notification.command.domain.model.Notification;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("notificationView") // "notificationView" 라는 Key prefix로 Redis에 저장됩니다.
public class NotificationView implements Serializable {

    @Id // Redis Key
    private Long notificationId;

    @Indexed // 이 필드로 검색이 가능하도록 설정 (findByUserId)
    private Long userId;

    private Long nMetadataId;
    private Boolean notificationStatus;
    private LocalDateTime createdAt;
    private LocalDateTime readAt;
    private LocalDateTime updatedAt;
    private String notificationMessage;
    private String notificationTitle;
    private String notificationUrl;

    public static NotificationView from(Notification notification) {
        return NotificationView.builder()
                .notificationId(notification.getId())
                .userId(notification.getUser().getId())
                .nMetadataId(notification.getNotificationMetadata().getNMetadataId())
                .notificationStatus(notification.getStatus())
                .createdAt(notification.getCreatedAt())
                .readAt(notification.getReadAt())
                .updatedAt(notification.getUpdatedAt())
                .notificationMessage(notification.getMessage())
                .notificationTitle(notification.getTitle())
                .notificationUrl(notification.getUrl())
                .build();
    }
}
