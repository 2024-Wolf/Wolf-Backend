package com.kdt.wolf.domain.user.entity;

import com.kdt.wolf.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity @Table(name = "alert")
public class AlertEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_alert_id")
    @SequenceGenerator(name = "seq_alert_id", sequenceName = "alert_sequence", allocationSize = 1)
    private Long alertId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private Long groupPostId;

    @Column(nullable = false)
    private String alertContent;

    @Column(columnDefinition = "CHAR(1) DEFAULT '0' CHECK(is_read IN ('0', '1'))")
    private boolean isRead = false; // false: 읽지 않음, true: 읽음

    private String alertLink;


    public void makeRead() {
        this.isRead = true;
    }
}
