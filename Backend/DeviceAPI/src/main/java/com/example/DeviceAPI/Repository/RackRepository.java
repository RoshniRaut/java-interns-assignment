package com.example.DeviceAPI.Repository;

import com.example.DeviceAPI.Entity.Rack;
import org.springframework.data.repository.CrudRepository;

public interface RackRepository extends CrudRepository<Rack, Integer> {

    Rack getByRackname(String rackName);
}
