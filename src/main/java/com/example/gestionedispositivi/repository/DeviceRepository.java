package com.example.gestionedispositivi.repository;

import com.example.gestionedispositivi.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
}