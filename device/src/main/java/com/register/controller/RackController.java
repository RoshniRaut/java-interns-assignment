package com.register.controller;

import com.register.entity.Rack;
import com.register.service.RackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RackController {
    Logger logger = LoggerFactory.getLogger(DeviceController.class);
    @Autowired
    private RackService rackService;

    @GetMapping("/rack")
    public List<Rack> getAllRacks(){
        logger.info("getAllRacks method is called");
        return rackService.getAllRacks();
    }

    @PostMapping("/rack")
    public void addRack(@RequestBody Rack rack){
        logger.info("addRack method is called");
        rackService.addRack(rack);
    }

    @DeleteMapping("/rack/{rack_id}")
    public void deleteRack(@PathVariable int rack_id){
        logger.info("deleteRack method is called");
        rackService.deleteRack(rack_id);
    }
}
