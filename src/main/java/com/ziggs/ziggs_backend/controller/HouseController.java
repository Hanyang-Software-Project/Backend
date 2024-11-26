package com.ziggs.ziggs_backend.controller;

import com.ziggs.ziggs_backend.entity.House;
import com.ziggs.ziggs_backend.repository.HouseRepository;
import com.ziggs.ziggs_backend.service.HouseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/house")
@Validated
public class HouseController {

    @Autowired
    private HouseRepository houseRepository;

    @PostMapping
    public ResponseEntity<House> createHouse(@RequestBody @Valid House house) {
        House savedHouse = houseRepository.save(house);
        return ResponseEntity.ok(savedHouse);
    }
}

