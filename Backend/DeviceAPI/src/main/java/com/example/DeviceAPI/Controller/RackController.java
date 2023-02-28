package com.example.DeviceAPI.Controller;

import com.example.DeviceAPI.Entity.Rack;
import com.example.DeviceAPI.Exceptions.AlreadyRegistered;
import com.example.DeviceAPI.Service.RackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RackController {

    Logger logger = LoggerFactory.getLogger(RackController.class);
    @Autowired
    private RackService rackService;

    @GetMapping("/getAllRack")
    public List<Rack> getAllRacks(){
        logger.info("getAllRacks method is called");
        return rackService.getAllRacks();
    }

    @PostMapping("/addRack")
    public void addRack(@Valid @RequestBody Rack rack) throws AlreadyRegistered {
        logger.info("addRack method is called");
        rackService.addRack(rack);
    }

    @DeleteMapping("/rack/{rack_id}")
    public void deleteRack(@PathVariable int rack_id){
        logger.info("deleteRack method is called");
        rackService.deleteRack(rack_id);
    }
}
