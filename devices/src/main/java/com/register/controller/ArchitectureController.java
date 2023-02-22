package com.register.controller;

import com.register.entity.Architecture;
import com.register.service.ArchitectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArchitectureController {

    @Autowired
    private ArchitectureService architectureService;

    @GetMapping("/architecture")
    public List<Architecture> getAllArchitectures(){
        return architectureService.getAllArchitectures();
    }

    @PostMapping("/architecture")
    public void addArchitecture(@RequestBody Architecture architecture){
        architectureService.addArchitecture(architecture);
    }

    @DeleteMapping("/architecture/{architecture_id}")
    public void deleteArchitecture(@PathVariable int architecture_id){
        architectureService.deleteArchitecture(architecture_id);
    }
}
