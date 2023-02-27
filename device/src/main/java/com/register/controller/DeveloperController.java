package com.register.controller;

import com.register.entity.Developer;
import com.register.service.DeveloperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DeveloperController {
    Logger logger = LoggerFactory.getLogger(DeveloperController.class);
    @Autowired
    private DeveloperService developerService;
    @GetMapping("/developers")
    public List<Developer> getAllDevelopers(){
        logger.info("getAllDevelopers method is called");
        return developerService.getAllDevelopers();
    }
    @GetMapping("/developers/{dev_id}")
    public Optional<Developer> getDeveloper(@PathVariable int dev_id){
        logger.info("getDeveloper method is called");
        return developerService.getDeveloper(dev_id);
    }
    @PostMapping("/developers")
    public void addDeveloper(@RequestBody Developer developer){
        logger.info("addDeveloper method is called");
        developerService.addDeveloper(developer);
    }

    @DeleteMapping("/developers/{dev_id}")
    public void deleteDeveloper(@PathVariable int dev_id){
        logger.info("deleteDeveloper method is called");
        developerService.deleteDeveloper(dev_id);
    }
}
