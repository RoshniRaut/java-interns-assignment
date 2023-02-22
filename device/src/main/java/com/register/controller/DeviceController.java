package com.register.controller;

import com.register.entity.Device;
import com.register.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeviceController {

    @Autowired
    private DeviceService deviceService;
    @GetMapping("/device")
    public List<Device> getAllDevices(){
        return deviceService.getAllDevices();
    }
    @PostMapping("/device")
    public void addDevice(@RequestBody Device device){
        deviceService.addDevice(device);
    }
    @PutMapping("/device/{device_id}")
    public void updateDevice(@RequestBody Device device){
        deviceService.updateDevice(device);
    }
    @DeleteMapping("/device/{device_id}")
    public void deleteDevice(@PathVariable int device_id){
        deviceService.deleteDevice(device_id);
    }
}
