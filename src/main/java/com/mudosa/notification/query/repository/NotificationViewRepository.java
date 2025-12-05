package com.mudosa.notification.query.repository;

import com.mudosa.notification.query.dto.NotificationView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationViewRepository extends CrudRepository<NotificationView, Long> {

    /**
     * userId 필드에 @Indexed 어노테이션이 있으므로, Spring Data Redis가 자동으로 메서드명을 기반으로 쿼리를 생성하여 실행합니다.
     * @param userId
     * @param pageable
     * @return
     */
    Page<NotificationView> findByUserId(Long userId, Pageable pageable);
}

