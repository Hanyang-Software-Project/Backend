package com.ziggs.ziggs_backend.repository;

import com.ziggs.ziggs_backend.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
        Optional<User> findByEmail(String email);
        Optional<User> findByUsername(String username);
        Optional<User> findByPhoneNumber(String phone);
}
