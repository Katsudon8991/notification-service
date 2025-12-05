package com.mudosa.notification.command.domain.repository;

import com.mudosa.notification.command.domain.model.FirebaseToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FirebaseTokenRepository extends JpaRepository<FirebaseToken, Long> {
    List<FirebaseToken> findByUserIdIn(List<Long> userId);
}
