package com.ziggs.ziggs_backend.controller;

import com.ziggs.ziggs_backend.entity.House;
import com.ziggs.ziggs_backend.repository.HouseRepository;
import com.ziggs.ziggs_backend.service.HouseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/house")
@Validated
public class HouseController {

    @Autowired
    private HouseService houseService;

    @GetMapping
    public List<House> getAllHouses() {
        return houseService.getAllHouses();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<House>> getHousesByUserId(@PathVariable Long userId) {
        try {
            List<House> houses = houseService.getHousesByUserId(userId);
            return ResponseEntity.ok(houses);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping
    public ResponseEntity<House> createHouse(@RequestBody @Valid House house) {
        House savedHouse = houseService.createHouse(house);
        return ResponseEntity.ok(savedHouse);
    }
}

