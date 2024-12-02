package com.ziggs.ziggs_backend.repository;

import com.ziggs.ziggs_backend.entity.Device;
import com.ziggs.ziggs_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findByUser(User user);
}
