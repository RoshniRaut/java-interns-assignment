package com.example.DeviceAPI.Service;

import com.example.DeviceAPI.Entity.Rack;
import com.example.DeviceAPI.Exceptions.AlreadyRegistered;
import com.example.DeviceAPI.Repository.RackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RackService {
    Logger logger = LoggerFactory.getLogger(RackService.class);

    @Autowired
    private RackRepository rackRepository;

    public List<Rack> getAllRacks(){
        List<Rack> racks = new ArrayList<>();
        rackRepository.findAll()
                .forEach(racks::add);
        return racks;
    }

    public void addRack(Rack rack) throws AlreadyRegistered {
        if(rackRepository.findByRackName(rack.getRackName()).isPresent()){
            logger.warn("Provided rack already registered");
            throw new AlreadyRegistered("rack already registered");
        }
        else {
            rackRepository.save(rack);
        }
    }

    public void deleteRack(int rack_id){
        rackRepository.deleteById(rack_id);
    }
}
