package com.example.DeviceAPI.Repository;

import com.example.DeviceAPI.Entity.Architecture;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ArchitectureRepository extends CrudRepository<Architecture, Integer> {
    Optional<Architecture> findByArchitectureName(String architectureName);
}
