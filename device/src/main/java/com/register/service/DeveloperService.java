package com.register.service;

import com.register.entity.Developer;
import com.register.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public void deleteDeveloper(int id) {
        developerRepository.deleteById(id);
    }
}
