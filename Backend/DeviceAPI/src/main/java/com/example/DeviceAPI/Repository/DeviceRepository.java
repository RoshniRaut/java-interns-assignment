package com.example.DeviceAPI.Repository;

import com.example.DeviceAPI.Entity.Device;
import org.springframework.data.repository.CrudRepository;

public interface DeviceRepository extends CrudRepository<Device, Integer> {

    Device findByDeviceNumber(int deviceNumber);
}
