package com.example.DeviceAPI.Service;

import com.example.DeviceAPI.Entity.Developer;
import com.example.DeviceAPI.Exceptions.UserAlreadyRegistered;
import com.example.DeviceAPI.Repository.DeveloperRepository;
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
    @Autowired
    private DeveloperRepository developerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public ResponseEntity<?> addUser(Developer userInfo) throws UserAlreadyRegistered {
        Map<String, String> message=new HashMap<>();
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
       if (developerRepository.findByEmail(userInfo.getEmail()).isPresent())
            throw new UserAlreadyRegistered("email already registered");
       if (developerRepository.findByName(userInfo.getName()).isPresent())
            throw new UserAlreadyRegistered("user name already registered");
       else {
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
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
        }
        else {
            developerRepository.deleteById(id);

            message.put("message","user record deleted ");
            return new ResponseEntity<>(message,HttpStatus.OK);
        }
    }
}