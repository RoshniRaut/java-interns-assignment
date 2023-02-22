package com.example.SpringSecurityJWT.Service;

import com.example.SpringSecurityJWT.Entity.Developer;
import com.example.SpringSecurityJWT.Repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeveloperService {
    @Autowired
    private DeveloperRepository developerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public String addUser(Developer userInfo){
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        if(developerRepository.findByEmail(userInfo.getEmail()).isPresent()){
            return "email already registered";
        }
        if(developerRepository.findByName(userInfo.getName()).isPresent())
            return "user name already registered";
        if(userInfo.getName()==null || userInfo.getEmail()==null || userInfo.getPassword()==null)
            return "field cannot be null";
        else{
            developerRepository.save(userInfo);
            return "added";
        }
    }

    public List<Developer> getUser(){
        return developerRepository.findAll();
    }

    public String deleteById(Integer id){
        Optional<Developer> userInfo= developerRepository.findById(id);
        if(userInfo.isEmpty()){
            return "user not found";
        }
        else {
            developerRepository.deleteById(id);
            return "user record deleted ";
        }
    }
}
