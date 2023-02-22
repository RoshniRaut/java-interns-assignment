package com.register.controller;

import com.register.entity.Developer;
import com.register.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeveloperController {
    @Autowired
    private DeveloperService developerService;
    @GetMapping("/developers")
    public List<Developer> getAllDevelopers(){
        return developerService.getAllDevelopers();
    }
    @PostMapping("/developers")
    public void addDeveloper(@RequestBody Developer developer){
        developerService.addDeveloper(developer);
    }

    @DeleteMapping("/developers/{dev_id}")
    public void deleteDeveloper(@PathVariable int id){
        developerService.deleteDeveloper(id);
    }
}
