package com.example.DeviceAPI.Repository;

import com.example.DeviceAPI.Entity.Architecture;
import org.springframework.data.repository.CrudRepository;

public interface ArchitectureRepository extends CrudRepository<Architecture, Integer> {
    Architecture getByArchitecturename(String architecturename);
}
