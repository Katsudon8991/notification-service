package com.mudosa.notification.command.domain.repository;

import com.mudosa.notification.command.domain.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Modifying
    @Query("UPDATE Notification n SET n.status = true, n.readAt = CURRENT_TIMESTAMP WHERE n.id = :notificationId")
    int updateNotificationStatus(@Param("notificationId") Long notificationId);
}
