package com.example.gestionedispositivi.service;

import com.example.gestionedispositivi.model.Device;
import com.example.gestionedispositivi.model.DeviceStatus;
import com.example.gestionedispositivi.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public Device getDeviceById(Long id) {
        return deviceRepository.findById(id).orElse(null);
    }

    public Device saveDevice(Device device) {
        return deviceRepository.save(device);
    }

    public void deleteDevice(Long id) {
        deviceRepository.deleteById(id);
    }

    public Device updateDeviceStatus(Long id, DeviceStatus status) {
        Device device = getDeviceById(id);
        if (device != null) {
            device.setStatus(status);
            return deviceRepository.save(device);
        }
        return null;
    }
}