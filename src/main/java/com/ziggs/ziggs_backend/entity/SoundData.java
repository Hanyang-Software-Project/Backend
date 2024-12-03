package com.ziggs.ziggs_backend.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "File path is mandatory.")
    @Column(name = "file_path", nullable = false, length = 255)
    private String filePath;

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    @JsonBackReference // Backward side of the relationship for Device
    private Device device;


    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }
}
