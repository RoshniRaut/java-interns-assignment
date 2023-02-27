package com.register.controller;

import com.register.entity.Architecture;
import com.register.service.ArchitectureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArchitectureController {
    Logger logger = LoggerFactory.getLogger(DeviceController.class);
    @Autowired
    private ArchitectureService architectureService;

    @GetMapping("/architecture")
    public List<Architecture> getAllArchitectures(){
        logger.info("getAllArchitecture method is called");
        return architectureService.getAllArchitectures();
    }

    @PostMapping("/architecture")
    public void addArchitecture(@RequestBody Architecture architecture){
        logger.info("addArchitecture method is called");
        architectureService.addArchitecture(architecture);
    }

    @DeleteMapping("/architecture/{architecture_id}")
    public void deleteArchitecture(@PathVariable int architecture_id){
        logger.info("deleteArchitecture method is called");
        architectureService.deleteArchitecture(architecture_id);
    }
}
