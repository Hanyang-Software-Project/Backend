package com.ziggs.ziggs_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SoundData")
public class SoundData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sound_data_id")
    private Long soundDataId;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "file_path", nullable = false, length = 255, unique = true)
    private String filePath;

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    @JsonIgnore
    private Device device;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }
}
