package com.example.DeviceAPI.Service;

import com.example.DeviceAPI.Entity.Device;
import com.example.DeviceAPI.Exceptions.AlreadyRegistered;
import com.example.DeviceAPI.Repository.ArchitectureRepository;
import com.example.DeviceAPI.Repository.DeveloperRepository;
import com.example.DeviceAPI.Repository.DeviceRepository;
import com.example.DeviceAPI.Repository.RackRepository;
import com.example.DeviceAPI.dto.DeviceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<DeviceRequest> getAllDevices(){
        List<DeviceRequest> devices = new ArrayList<>();
        devices=deviceRepository.findAllDevice();
        return devices;
    }

    public Optional<DeviceRequest> addDevice(DeviceRequest deviceRequest)throws AlreadyRegistered {
        Device device=new Device();
        if(deviceRepository.findByDeviceNumber(deviceRequest.getDeviceNumber()).isPresent())
            throw new AlreadyRegistered("Device number already present");
        device.setDeviceNumber(deviceRequest.getDeviceNumber());
        device.setDevice_model(deviceRequest.getDevice_model());
        if(architectureRepository.findByArchitectureName(deviceRequest.getArchitectureName()).isEmpty())
            throw new AlreadyRegistered("ArchitectureName not found");
        device.setArchitectureId(architectureRepository.findByArchitectureName(deviceRequest.getArchitectureName()).get().getArchitecture_id());
        device.setBlocked_since(deviceRequest.getBlocked_since());
        device.setBlocked_till(deviceRequest.getBlocked_till());
        device.setComments(deviceRequest.getComments());
        if(developerRepository.findByName(deviceRequest.getDeveloperName()).isEmpty())
            throw  new AlreadyRegistered("DeveloperName not found");
        device.setDeveloperId(developerRepository.findByName(deviceRequest.getDeveloperName()).get().getId());
        device.setMac(deviceRequest.getMac());
        if(rackRepository.findByRackName(deviceRequest.getRackName()).isEmpty())
            throw new AlreadyRegistered("Rack name not found");
        device.setRackId(rackRepository.findByRackName(deviceRequest.getRackName()).get().getRack_id());
        deviceRepository.save(device);
        return deviceRepository.findByDeviceNumber(device.getDeviceNumber());
    }

    public void updateDevice(Device device){
        deviceRepository.save(device);
    }

    public void deleteDevice(int device_id){
        deviceRepository.deleteById(device_id);
    }

    public Optional<Device> getDevice(int device_id) {
        return deviceRepository.findById(device_id);
    }
}
