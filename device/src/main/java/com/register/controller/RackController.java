package com.register.controller;

import com.register.entity.Rack;
import com.register.service.RackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RackController {

    @Autowired
    private RackService rackService;

    @GetMapping("/rack")
    public List<Rack> getAllRacks(){
        return rackService.getAllRacks();
    }

    @PostMapping("/rack")
    public void addRack(@RequestBody Rack rack){
        rackService.addRack(rack);
    }

    @DeleteMapping("/rack/{rack_id}")
    public void deleteRack(@PathVariable int rack_id){
        rackService.deleteRack(rack_id);
    }
}
