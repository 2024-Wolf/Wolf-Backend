package com.kdt.wolf.global.fcm.entity;

import com.kdt.wolf.domain.user.entity.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "fcmTokens")
public class FcmEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_fcm_token_id")
    @SequenceGenerator(name = "seq_fcm_token_id", sequenceName = "fcm_token_sequence", allocationSize = 1)
    private Long fcmTokenId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String fcmToken;

    @Builder
    public FcmEntity(UserEntity user, String fcmToken) {
        this.user = user;
        this.fcmToken = fcmToken;
    }
}
