package com.example.DeviceAPI.Repository;

import com.example.DeviceAPI.Entity.Device;
import com.example.DeviceAPI.dto.DeviceRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository extends CrudRepository<Device, Integer> {


    Optional<Device> findByDeviceNumber(int deviceNumber);


    @Override
    List<Device> findAll();
}
