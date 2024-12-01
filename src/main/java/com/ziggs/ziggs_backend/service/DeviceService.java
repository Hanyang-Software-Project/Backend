package com.ziggs.ziggs_backend.service;

import com.ziggs.ziggs_backend.dto.DeviceDTO;
import com.ziggs.ziggs_backend.entity.Device;
import com.ziggs.ziggs_backend.entity.User;
import com.ziggs.ziggs_backend.repository.DeviceRepository;
import com.ziggs.ziggs_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public Optional<Device> getDeviceById(Long id) {
        return deviceRepository.findById(id);
    }

    public Device createDeviceFromDTO(DeviceDTO deviceDTO) {
        User user = userRepository.findById(deviceDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + deviceDTO.getUserId()));

        Device device = new Device();
        device.setDeviceName(deviceDTO.getDeviceName());
        device.setUser(user);

        return deviceRepository.save(device);
    }


    public Device updateDevice(Long id, Device deviceDetails) {
        return deviceRepository.findById(id)
                .map(device -> {
                    device.setDeviceName(deviceDetails.getDeviceName());
                    device.setUser(deviceDetails.getUser());
                    return deviceRepository.save(device);
                })
                .orElseThrow(() -> new IllegalArgumentException("Device not found with ID: " + id));
    }

    public void deleteDevice(Long id) {
        if (!deviceRepository.existsById(id)) {
            throw new IllegalArgumentException("Device not found with ID: " + id);
        }
        deviceRepository.deleteById(id);
    }
}
