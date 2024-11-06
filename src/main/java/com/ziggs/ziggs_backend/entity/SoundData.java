package com.ziggs.ziggs_backend.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "SoundData")
@Getter
@Setter
public class SoundData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sound_data_id")
    private Long soundDataId;

    @Column(name = "analysis_result", columnDefinition = "JSON")
    private String analysisResult;

    @Column(name = "detected_anomaly")
    private Boolean detectedAnomaly;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}
