package com.example.DeviceAPI.Service;

import com.example.DeviceAPI.Entity.Developer;
import com.example.DeviceAPI.Exceptions.AlreadyRegistered;
import com.example.DeviceAPI.Repository.DeveloperRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DeveloperService {
    Logger logger = LoggerFactory.getLogger(DeveloperService.class);

    @Autowired
    private DeveloperRepository developerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public ResponseEntity<?> addUser(Developer userInfo) throws AlreadyRegistered {
        Map<String, String> message=new HashMap<>();
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
       if (developerRepository.findByEmail(userInfo.getEmail()).isPresent()) {
           logger.warn("Provided email already registered");
           throw new AlreadyRegistered("email already registered");
       }
       if (developerRepository.findByName(userInfo.getName()).isPresent()) {
           logger.warn("Provided username already registered");
           throw new AlreadyRegistered("user name already registered");
       }
       else {
           System.out.println("user found");
            developerRepository.save(userInfo);
            message.put("message","New Developer Added");
            return new ResponseEntity<>(message, HttpStatus.CREATED);
       }
    }

    public List<Developer> getUser(){
        return developerRepository.findAll();
    }

    public ResponseEntity<?> deleteById(Integer id){
        Map<String, String> message=new HashMap<>();
        Optional<Developer> userInfo= developerRepository.findById(id);
        if(userInfo.isEmpty()){
            message.put("message","User not found");
            logger.warn("Developer to be deleted not found");
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
        }
        else {
            developerRepository.deleteById(id);

            message.put("message","user record deleted ");
            return new ResponseEntity<>(message,HttpStatus.OK);
        }
    }
}