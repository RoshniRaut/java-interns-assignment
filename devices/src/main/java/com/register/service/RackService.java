package com.register.service;

import com.register.entity.Rack;
import com.register.repository.RackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RackService {

    @Autowired
    private RackRepository rackRepository;

    public List<Rack> getAllRacks(){
        List<Rack> racks = new ArrayList<>();
        rackRepository.findAll()
                .forEach(racks::add);
        return racks;
    }

    public void addRack(Rack rack){
        rackRepository.save(rack);
    }

    public void deleteRack(int rack_id){
        rackRepository.deleteById(rack_id);
    }
}
