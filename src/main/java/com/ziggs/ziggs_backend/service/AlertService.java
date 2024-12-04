package com.ziggs.ziggs_backend.service;

import com.ziggs.ziggs_backend.dto.AlertDTO;
import com.ziggs.ziggs_backend.entity.Alert;
import com.ziggs.ziggs_backend.entity.SoundData;
import com.ziggs.ziggs_backend.entity.User;
import com.ziggs.ziggs_backend.repository.AlertRepository;
import com.ziggs.ziggs_backend.repository.SoundDataRepository;
import com.ziggs.ziggs_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private SoundDataRepository soundDataRepository;

    @Autowired
    private UserRepository userRepository;

    public Alert createAlert(String filePath) {
        SoundData soundData = soundDataRepository.findByFilePath(filePath)
                .orElseThrow(() -> new RuntimeException("SoundData not found for filePath: " + filePath));

        Alert alert = new Alert();
        alert.setSoundData(soundData);
        return alertRepository.save(alert);
    }

    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }

    public Optional<Alert> getAlertById(Long id) {
        return alertRepository.findById(id);
    }

    public Alert updateThreatFlag(Long id, Boolean threatFlag) {
        Alert alert = alertRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alert not found for ID: " + id));

        alert.setThreatFlag(threatFlag);
        return alertRepository.save(alert);
    }

    public List<AlertDTO> getAlertsByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found for ID: " + userId));

        List<Alert> alerts = alertRepository.findBySoundData_User(user);

        return alerts.stream()
                .map(alert -> new AlertDTO(
                        alert.getAlertId(),
                        alert.getSoundData().getFilePath(),
                        alert.getThreatFlag(),
                        alert.getTimestamp()
                ))
                .collect(Collectors.toList());
    }


    public void deleteAlert(Long id) {
        alertRepository.deleteById(id);
    }
}
