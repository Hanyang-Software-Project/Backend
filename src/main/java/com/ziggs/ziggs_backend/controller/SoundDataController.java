package com.ziggs.ziggs_backend.controller;

import com.ziggs.ziggs_backend.dto.SoundDataDTO;
import com.ziggs.ziggs_backend.entity.SoundData;
import com.ziggs.ziggs_backend.service.SoundDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/soundData")
public class SoundDataController {

    @Autowired
    private SoundDataService soundDataService;

    @GetMapping
    public List<SoundData> getAllSoundData() {
        return soundDataService.getAllSoundData();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SoundData> getSoundDataById(@PathVariable Long id) {
        Optional<SoundData> soundData = soundDataService.getSoundDataById(id);
        return soundData.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SoundData> createSoundData(@RequestBody SoundDataDTO soundDataDTO) {
        SoundData createdSoundData = soundDataService.createSoundData(soundDataDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSoundData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SoundData> updateSoundData(@PathVariable Long id, @RequestBody SoundData soundDataDetails) {
        Optional<SoundData> updatedSoundData = soundDataService.getSoundDataById(id)
                .map(data -> {
                    return soundDataService.saveSoundData(soundDataDetails);
                });
        return updatedSoundData.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSoundData(@PathVariable Long id) {
        soundDataService.deleteSoundData(id);
        return ResponseEntity.noContent().build();
    }
}
