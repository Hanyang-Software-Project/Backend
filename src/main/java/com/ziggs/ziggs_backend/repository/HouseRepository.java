package com.ziggs.ziggs_backend.repository;

import com.ziggs.ziggs_backend.entity.House;
import com.ziggs.ziggs_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HouseRepository extends JpaRepository<House, Long> {
    Optional<House> findById(Long id);

    House findByHouseName(String houseName);

    List<House> findByUsersContaining(User user);

}
