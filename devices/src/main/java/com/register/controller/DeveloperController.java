package com.register.controller;

import com.register.entity.Developer;
import com.register.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DeveloperController {
    @Autowired
    private DeveloperService developerService;
    @GetMapping("/developers")
    public List<Developer> getAllDevelopers(){
        return developerService.getAllDevelopers();
    }
    @GetMapping("/developers/{dev_id}")
    public Optional<Developer> getDeveloper(@PathVariable int dev_id){
        return developerService.getDeveloper(dev_id);
    }
    @PostMapping("/developers")
    public void addDeveloper(@RequestBody Developer developer){
        developerService.addDeveloper(developer);
    }

    @DeleteMapping("/developers/{dev_id}")
    public void deleteDeveloper(@PathVariable int dev_id){
        developerService.deleteDeveloper(dev_id);
    }
}
