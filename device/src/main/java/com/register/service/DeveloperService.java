package com.register.service;

import com.register.entity.Developer;
import com.register.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeveloperService {
    @Autowired
    private DeveloperRepository developerRepository;

    //Getting all developers values
    public List<Developer> getAllDevelopers(){
        List<Developer> developers = new ArrayList<>();
        developerRepository.findAll()
                .forEach(developers::add);
        return developers;
    }

    //Adding new developers
    public void addDeveloper(Developer developer){
        developerRepository.save(developer);
    }

    //Deleting developer id
    public void deleteDeveloper(int dev_id) {
        developerRepository.deleteById(dev_id);
    }
    //Getting specific developer
    public Optional<Developer> getDeveloper(int dev_id) {
        return developerRepository.findById(dev_id);
    }
}
