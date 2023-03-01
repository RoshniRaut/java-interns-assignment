package com.example.DeviceAPI.Service;

import com.example.DeviceAPI.Entity.Architecture;
import com.example.DeviceAPI.Exceptions.AlreadyRegistered;
import com.example.DeviceAPI.Repository.ArchitectureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArchitectureService {
    Logger logger = LoggerFactory.getLogger(ArchitectureService.class);

    @Autowired
    private ArchitectureRepository architectureRepository;

    public List<Architecture> getAllArchitectures(){
        List<Architecture> architectures = new ArrayList<>();
        architectureRepository.findAll()
                .forEach(architectures::add);
        return architectures;
    }

    public Optional<Architecture> addArchitecture(Architecture architecture){
//        if (architectureRepository.findByArchitectureName(architecture.getArchitectureName()).isPresent()) {
//            logger.warn("Provided architecture already registered");
////            throw new AlreadyRegistered("architecture already registered");
//        }
//        else{
            architectureRepository.save(architecture);
            return architectureRepository.findByArchitectureName(architecture.getArchitectureName());
//        }
    }

    public void deleteArchitecture(int architecture_id){
        architectureRepository.deleteById(architecture_id);
    }
}
