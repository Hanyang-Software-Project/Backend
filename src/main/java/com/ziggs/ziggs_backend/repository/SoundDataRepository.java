package com.ziggs.ziggs_backend.repository;

import com.ziggs.ziggs_backend.entity.SoundData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SoundDataRepository extends JpaRepository<SoundData, Long> {
    Optional<SoundData> findByFilePath(String filePath);
}
