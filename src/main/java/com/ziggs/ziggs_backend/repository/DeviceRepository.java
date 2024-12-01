package com.ziggs.ziggs_backend.repository;

import com.ziggs.ziggs_backend.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
}
