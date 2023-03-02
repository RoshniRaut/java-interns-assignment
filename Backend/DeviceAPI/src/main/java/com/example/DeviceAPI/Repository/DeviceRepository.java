package com.example.DeviceAPI.Repository;

import com.example.DeviceAPI.Entity.Device;
import com.example.DeviceAPI.dto.DeviceRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository extends CrudRepository<Device, Integer> {

    @Query("Select new com.example.DeviceAPI.dto.DeviceRequest(d.device_id," +
            "d.deviceNumber, a.architectureName," +
            "d.blocked_since, d.blocked_till," +
            "d.comments, dev.name," +
            "d.device_model, d.mac," +
            "r.rackName ) " +
            "from Device d, Architecture a,Rack r,Developer dev " +
            "where d.deviceNumber=?1 " +
            "and d.architectureId=a.architecture_id " +
            "and d.rackId=r.rack_id " +
            "and d.developerId=dev.id")
    Optional<DeviceRequest> findByDeviceNumber(int deviceNumber);


    @Query("Select new com.example.DeviceAPI.dto.DeviceRequest(d.device_id," +
            "d.deviceNumber, a.architectureName," +
            "d.blocked_since, d.blocked_till," +
            "d.comments, dev.name," +
            "d.device_model, d.mac," +
            "r.rackName ) " +
            "from Device d, Architecture a,Rack r,Developer dev " +
            "where d.architectureId=a.architecture_id " +
            "and d.rackId=r.rack_id " +
            "and d.developerId=dev.id")
    List<DeviceRequest> findAllDevice();

}
