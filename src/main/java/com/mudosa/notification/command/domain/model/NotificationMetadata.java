package com.mudosa.notification.command.domain.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <h6>Metadata that guides the notification format.</h6>
 * <p>
 *     This entity has the title, content, and URL information of the notification, as well as the entity sending the notification and the notification type information.
 * </p>
 *
 */
@Entity
@Table(
        name = "notification_metadata"
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class NotificationMetadata {


    /**
     * <p>
     * A unique identifier for the notification metadata.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="n_matadata_id")
    private Long nMetadataId;

    /**
     * <p>
     *     The title of the notification.<br>
     *     (Maximum 255 characters)
     * </p>
     */
    @Column(name="notification_title",nullable = false, length=255)
    private String title;

    /**
     * <p>
     *     The message to be displayed in the notification.<br>
     *     (Maximum 255 characters)
     * </p>
     */
    @Column(name="notification_message",nullable = false, length=255)
    private String message;

    /**
     * <p>
     *     The URL to which the user will be directed when they click the notification.<br>
     *     (Maximum 255 characters)
     * </p>
     */
    @Column(name="notification_url",nullable = false, length=255)
    private String url;

    @Column(name="notification_category",nullable = false)
    private NotificationCategory category;

    @Column(name="notification_type",nullable = false)
    private NotificationType type;

}
