package com.mudosa.notification.query.application;

import com.mudosa.notification.query.dto.NotificationView;
import com.mudosa.notification.query.repository.NotificationViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationQueryService {

    private final NotificationViewRepository notificationViewRepository;

    public Page<NotificationView> findByUserId(Long userId, Pageable pageable){
        return notificationViewRepository.findByUserId(userId,pageable);
    }
}
