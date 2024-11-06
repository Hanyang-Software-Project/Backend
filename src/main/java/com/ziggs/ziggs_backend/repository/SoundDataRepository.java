package com.ziggs.ziggs_backend.repository;

import com.ziggs.ziggs_backend.entity.SoundData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoundDataRepository extends JpaRepository<SoundData, Long> {
}
