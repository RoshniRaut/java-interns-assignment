package com.example.DeviceAPI.Service;

import com.example.DeviceAPI.Entity.Device;
import com.example.DeviceAPI.Exceptions.AlreadyRegistered;
import com.example.DeviceAPI.Repository.ArchitectureRepository;
import com.example.DeviceAPI.Repository.DeveloperRepository;
import com.example.DeviceAPI.Repository.DeviceRepository;
import com.example.DeviceAPI.Repository.RackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private ArchitectureRepository architectureRepository;
    @Autowired
    private DeveloperRepository developerRepository;
    @Autowired
    private RackRepository rackRepository;

    public List<Device> getAllDevices(){
        return deviceRepository.findAll();
    }

    public Optional<Device> addDevice(Device device)throws AlreadyRegistered {
        deviceRepository.save(device);
        return deviceRepository.findByDeviceNumber(device.getDeviceNumber());
    }

    public Optional<Device> updateDevice(int device_id,Device device) throws AlreadyRegistered{
        deviceRepository.save(device);
        return deviceRepository.findByDeviceNumber(device.getDeviceNumber());
    }

    public void deleteDevice(int device_id){
        deviceRepository.deleteById(device_id);
    }

    public Optional<Device> getDevice(int device_id) {
        return deviceRepository.findById(device_id);
    }
}
