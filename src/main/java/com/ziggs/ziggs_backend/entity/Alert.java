package com.ziggs.ziggs_backend.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "Alerts")
@Getter
@Setter
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alert_id")
    private Integer alertId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @Column(name = "alert_type", length = 100)
    private String alertType;

    @Column(name = "status", length = 50)
    private String status;

    @ManyToOne
    @JoinColumn(name = "sound_data_id")
    private SoundData soundData;
}
