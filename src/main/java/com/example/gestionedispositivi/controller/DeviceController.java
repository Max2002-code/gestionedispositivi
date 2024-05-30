package com.example.gestionedispositivi.controller;

import com.example.gestionedispositivi.model.Device;
import com.example.gestionedispositivi.model.DeviceStatus;
import com.example.gestionedispositivi.model.Employee;
import com.example.gestionedispositivi.service.DeviceService;
import com.example.gestionedispositivi.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final DeviceService deviceService;
    private final EmployeeService employeeService;

    public DeviceController(DeviceService deviceService, EmployeeService employeeService) {
        this.deviceService = deviceService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Device> getAllDevices() {
        return deviceService.getAllDevices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable Long id) {
        Device device = deviceService.getDeviceById(id);
        return device != null ? ResponseEntity.ok(device) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Device> createDevice(@RequestBody Device device) {
        Device createdDevice = deviceService.saveDevice(device);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDevice);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable Long id, @RequestBody Device deviceDetails) {
        Device device = deviceService.getDeviceById(id);
        if (device == null) {
            return ResponseEntity.notFound().build();
        }
        device.setType(deviceDetails.getType());
        device.setSerialNumber(deviceDetails.getSerialNumber());
        // Aggiorna altri campi se necessario
        Device updatedDevice = deviceService.saveDevice(device);
        return ResponseEntity.ok(updatedDevice);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Long id) {
        deviceService.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{deviceId}/assign/{employeeId}")
    public ResponseEntity<Device> assignDeviceToEmployee(@PathVariable Long deviceId, @PathVariable Long employeeId) {
        Device device = deviceService.getDeviceById(deviceId);
        Employee employee = employeeService.getEmployeeById(employeeId);

        if (device == null || employee == null) {
            return ResponseEntity.notFound().build();
        }

        device.setEmployee(employee);
        device.setStatus(DeviceStatus.ASSIGNED);
        deviceService.saveDevice(device);

        return ResponseEntity.ok(device);
    }
}