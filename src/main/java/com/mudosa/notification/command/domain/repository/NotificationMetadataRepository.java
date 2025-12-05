package com.mudosa.notification.command.domain.repository;

import com.mudosa.notification.command.domain.model.NotificationMetadata;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationMetadataRepository extends CrudRepository<NotificationMetadata, Long> {

    Optional<NotificationMetadata> findByCategory(String category);
}
