package com.example.DeviceAPI.Repository;

import com.example.DeviceAPI.Entity.Rack;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RackRepository extends CrudRepository<Rack, Integer> {
    Optional<Rack> findByRackName(String name);

}
