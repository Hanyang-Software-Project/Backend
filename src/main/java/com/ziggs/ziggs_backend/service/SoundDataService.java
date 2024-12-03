package com.ziggs.ziggs_backend.service;

import com.ziggs.ziggs_backend.entity.SoundData;
import com.ziggs.ziggs_backend.repository.SoundDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SoundDataService {

    @Autowired
    private SoundDataRepository soundDataRepository;

    public List<SoundData> getAllSoundData() {
        return soundDataRepository.findAll();
    }

    public Optional<SoundData> getSoundDataById(Long id) {
        return soundDataRepository.findById(id);
    }

    public SoundData saveSoundData(SoundData soundData) {
        return soundDataRepository.save(soundData);
    }

    public void deleteSoundData(Long id) {
        soundDataRepository.deleteById(id);
    }
}
