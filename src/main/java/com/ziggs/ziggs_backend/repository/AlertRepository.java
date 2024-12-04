package com.ziggs.ziggs_backend.repository;

import com.ziggs.ziggs_backend.entity.Alert;
import com.ziggs.ziggs_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findBySoundData_User(User user);
}

