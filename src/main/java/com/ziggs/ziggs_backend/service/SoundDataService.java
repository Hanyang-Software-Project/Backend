package com.ziggs.ziggs_backend.service;

import com.ziggs.ziggs_backend.dto.SoundDataDTO;
import com.ziggs.ziggs_backend.entity.Device;
import com.ziggs.ziggs_backend.entity.SoundData;
import com.ziggs.ziggs_backend.entity.User;
import com.ziggs.ziggs_backend.repository.DeviceRepository;
import com.ziggs.ziggs_backend.repository.SoundDataRepository;
import com.ziggs.ziggs_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SoundDataService {

    @Autowired
    private SoundDataRepository soundDataRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    public List<SoundData> getAllSoundData() {
        return soundDataRepository.findAll();
    }

    public Optional<SoundData> getSoundDataById(Long id) {
        return soundDataRepository.findById(id);
    }


    public SoundData createSoundData(SoundDataDTO soundDataDTO) {
        Device device = deviceRepository.findById(soundDataDTO.getDeviceId())
                .orElseThrow(() -> new IllegalArgumentException("Device not found"));

        User user = userRepository.findById(soundDataDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        SoundData soundData = new SoundData();
        soundData.setFilePath(soundDataDTO.getFilePath());
        soundData.setDevice(device);
        soundData.setUser(user);

        return soundDataRepository.save(soundData);
    }

    public SoundData saveSoundData(SoundData soundData) {
        return soundDataRepository.save(soundData);
    }

    public void deleteSoundData(Long id) {
        soundDataRepository.deleteById(id);
    }
}
