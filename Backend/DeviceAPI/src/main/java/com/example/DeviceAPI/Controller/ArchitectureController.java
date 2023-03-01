package com.example.DeviceAPI.Controller;

import com.example.DeviceAPI.Entity.Architecture;
import com.example.DeviceAPI.Exceptions.AlreadyRegistered;
import com.example.DeviceAPI.Service.ArchitectureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class ArchitectureController {

    Logger logger = LoggerFactory.getLogger(ArchitectureController.class);

    @Autowired
    private ArchitectureService architectureService;

    @GetMapping("/getAllArchitecture")
    public List<Architecture> getAllArchitectures(){
        logger.info("getAllArchitecture method is called");
        return architectureService.getAllArchitectures();
    }

    @PostMapping("/addArchitecture")
    public Optional<Architecture> addArchitecture(@Valid @RequestBody Architecture architecture)throws AlreadyRegistered {
        logger.info("addArchitecture method is called");
        return architectureService.addArchitecture(architecture);
    }

    @DeleteMapping("/architecture/{architecture_id}")
    public void deleteArchitecture(@PathVariable int architecture_id){
        logger.info("deleteArchitecture method is called");
        architectureService.deleteArchitecture(architecture_id);
    }
}
