package com.example.DeviceAPI.Controller;

import com.example.DeviceAPI.Entity.Device;
import com.example.DeviceAPI.Exceptions.AlreadyRegistered;
import com.example.DeviceAPI.Repository.DeveloperRepository;
import com.example.DeviceAPI.Repository.DeviceRepository;
import com.example.DeviceAPI.Service.DeviceService;
import com.example.DeviceAPI.dto.DeviceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class DeviceController {

    Logger logger = LoggerFactory.getLogger(DeviceController.class);
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private DeveloperRepository developerRepository;
    @Autowired
    private DeviceRepository deviceRepository;

    @GetMapping("/getAllDevice")
    public List<Device> getAllDevices(){
        logger.info("getAllDevices method is called");
        return deviceService.getAllDevices();
    }
    @GetMapping("/device/{device_id}")
    public Optional<Device> getDevice(@PathVariable int device_id){
        logger.info("getDevice method is called");
        return deviceService.getDevice(device_id);
    }
    @PostMapping("/addDevice")
    public Optional<Device> addDevice(@Valid @RequestBody Device deviceRequest)throws AlreadyRegistered {
        logger.info("addDevices method is called");
        return deviceService.addDevice(deviceRequest);
    }
    @PutMapping("/device/{device_id}")
    public Optional<Device> updateDevice(@PathVariable int device_id, @RequestBody Device device) throws AlreadyRegistered {
        logger.info("updateDevice method is called");
        if(deviceRepository.findById(device_id).isEmpty())
            throw new AlreadyRegistered("Device not found");
        return deviceService.updateDevice(device_id,device);
    }
    @DeleteMapping("/device/{device_id}")
    public void deleteDevice(@PathVariable int device_id){
        logger.info("deleteDevice method is called");
        deviceService.deleteDevice(device_id);
    }
}
