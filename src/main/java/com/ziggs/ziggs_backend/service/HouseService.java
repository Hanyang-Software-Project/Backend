package com.ziggs.ziggs_backend.service;

import com.ziggs.ziggs_backend.entity.House;
import com.ziggs.ziggs_backend.entity.User;
import com.ziggs.ziggs_backend.repository.HouseRepository;
import com.ziggs.ziggs_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseService {

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private UserRepository userRepository;

    public List<House> getAllHouses() {
        return houseRepository.findAll();
    }

    public List<House> getHousesByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
        return houseRepository.findByUsersContaining(user);
    }

    public House createHouse(House house) {
        return houseRepository.save(house);
    }
}
