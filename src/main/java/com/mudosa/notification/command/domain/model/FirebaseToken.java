package com.mudosa.notification.command.domain.model;

import com.mudosa.common.domain.BaseEntity;
import com.mudosa.user.domain.model.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name="firebase_token",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id","firebase_token_key"})
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FirebaseToken extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="token_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name="firebase_token_key",nullable=false)
    private String token;

}
