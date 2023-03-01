package com.example.DeviceAPI.Controller;

import com.example.DeviceAPI.Entity.Device;
import com.example.DeviceAPI.Exceptions.AlreadyRegistered;
import com.example.DeviceAPI.Service.DeviceService;
import com.example.DeviceAPI.dto.DeviceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class DeviceController {

    Logger logger = LoggerFactory.getLogger(DeviceController.class);
    @Autowired
    private DeviceService deviceService;
    @GetMapping("/getAllDevice")
    public List<DeviceRequest> getAllDevices(){
        logger.info("getAllDevices method is called");
        return deviceService.getAllDevices();
    }
    @GetMapping("/device/{device_id}")
    public Optional<Device> getDevice(@PathVariable int device_id){
        logger.info("getDevice method is called");
        return deviceService.getDevice(device_id);
    }
    @PostMapping("/addDevice")
    public Optional<DeviceRequest> addDevice(@Valid @RequestBody DeviceRequest deviceRequest)throws AlreadyRegistered {
        logger.info("addDevices method is called");
        return deviceService.addDevice(deviceRequest);
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
