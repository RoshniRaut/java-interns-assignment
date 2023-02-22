package com.register.service;

import com.register.entity.Device;
import com.register.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    public List<Device> getAllDevices(){
        List<Device> devices = new ArrayList<>();
        deviceRepository.findAll()
                .forEach(devices::add);
        return devices;
    }

    public void addDevice(Device device){
        deviceRepository.save(device);
    }

    public void updateDevice(Device device){
        deviceRepository.save(device);
    }

    public void deleteDevice(int device_id){
        deviceRepository.deleteById(device_id);
    }
}
