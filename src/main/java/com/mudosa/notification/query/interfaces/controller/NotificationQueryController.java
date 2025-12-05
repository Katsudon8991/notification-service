package com.mudosa.notification.query.interfaces.controller;

import com.mudosa.notification.query.application.NotificationQueryService;
import com.mudosa.notification.query.dto.NotificationView;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mudosa.common.dto.ApiResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationQueryController {

    private final NotificationQueryService notificationQueryService;

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<Page<NotificationView>>> getNotifications(@PathVariable Long userId, Pageable pageable){
        Page<NotificationView> notifications = notificationQueryService.findByUserId(userId,pageable);
        return ResponseEntity.ok(ApiResponse.success(notifications));
    }
}
