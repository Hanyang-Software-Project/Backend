package com.ziggs.ziggs_backend.service;

import com.ziggs.ziggs_backend.entity.House;
import com.ziggs.ziggs_backend.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseService {

    @Autowired
    private HouseRepository houseRepository;

    public List<House> getAllHouses() {
        return houseRepository.findAll();
    }
}
