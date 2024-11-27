package com.ziggs.ziggs_backend.service;

import com.ziggs.ziggs_backend.entity.House;
import com.ziggs.ziggs_backend.entity.User;
import com.ziggs.ziggs_backend.repository.HouseRepository;
import com.ziggs.ziggs_backend.repository.UserRepository;
import com.ziggs.ziggs_backend.security.PasswordHasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HouseRepository houseRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));
    }

    public User createUser(User user) {
        if (user.getHouse() == null) {
            throw new IllegalArgumentException("House cannot be null");
        }

        if (isEmailTaken(user.getEmail())) {
            throw new IllegalArgumentException("Email already taken");
        }

        if (isUsernameTaken(user.getUsername())) {
            throw new IllegalArgumentException("Username already taken");
        }

        if (isPhoneTaken(user.getPhoneNumber())) {
            throw new IllegalArgumentException("Phone number already taken");
        }

        House house = houseRepository.findByHouseName(user.getHouse().getHouseName());
        if (house == null) {
            house = new House();
            house.setHouseName(user.getHouse().getHouseName());
            house.setAddress(user.getHouse().getAddress());
            house = houseRepository.save(house);
        }

        user.setHouse(house);

        if (house.getUsers() == null) {
            house.setUsers(new ArrayList<>());
        }
        house.getUsers().add(user);
        user.setPassword(PasswordHasher.hashPassword(user.getPassword()));

        userRepository.save(user);
        houseRepository.save(house);

        return user;
    }



    public boolean verifyUserCredentials(String email, String rawPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return PasswordHasher.checkPassword(rawPassword, user.getPassword());
    }

    public boolean isEmailTaken(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public boolean isUsernameTaken(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public boolean isPhoneTaken(String phone) {
        return userRepository.findByPhoneNumber(phone).isPresent();
    }


    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


}