package com.example.DeviceAPI.Service;

import com.example.DeviceAPI.Entity.Architecture;
import com.example.DeviceAPI.Repository.ArchitectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArchitectureService {

    @Autowired
    private ArchitectureRepository architectureRepository;

    public List<Architecture> getAllArchitectures(){
        List<Architecture> architectures = new ArrayList<>();
        architectureRepository.findAll()
                .forEach(architectures::add);
        return architectures;
    }

    public void addArchitecture(Architecture architecture){
        architectureRepository.save(architecture);
    }

    public void deleteArchitecture(int architecture_id){
        architectureRepository.deleteById(architecture_id);
    }
}
