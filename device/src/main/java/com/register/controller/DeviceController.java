package com.register.controller;

import com.register.entity.Device;
import com.register.service.DeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DeviceController {
    Logger logger = LoggerFactory.getLogger(DeviceController.class);
    @Autowired
    private DeviceService deviceService;
    @GetMapping("/device")
    public List<Device> getAllDevices(){
        logger.info("getAllDevices method is called");
        return deviceService.getAllDevices();
    }
    @GetMapping("/device/{device_id}")
    public Optional<Device> getDevice(@PathVariable int device_id){
        logger.info("getDevice method is called");
        return deviceService.getDevice(device_id);
    }
    @PostMapping("/device")
    public void addDevice(@RequestBody Device device){
//        device1.setArchitecture();
        logger.info("addDevices method is called");
        deviceService.addDevice(device);
    }
    @PutMapping("/device/{device_id}")
    public void updateDevice(@RequestBody Device device){
        logger.info("updateDevice method is called");
        deviceService.updateDevice(device);
    }
    @DeleteMapping("/device/{device_id}")
    public void deleteDevice(@PathVariable int device_id){
        logger.info("deleteDevice method is called");
        deviceService.deleteDevice(device_id);
    }
}
