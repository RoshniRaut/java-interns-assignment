package com.example.SpringSecurityJWT.Service;

import com.example.SpringSecurityJWT.Entity.Developer;
import com.example.SpringSecurityJWT.Repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public ResponseEntity<?> addUser(Developer userInfo){
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        try {
            if (developerRepository.findByEmail(userInfo.getEmail()).isPresent()) {
                throw new Exception("email already registered");
//                throw new UsernameNotFoundException("email already registered");
            }
            if (developerRepository.findByName(userInfo.getName()).isPresent())
                throw new Exception("user name already registered");
            if (userInfo.getName() == null || userInfo.getEmail() == null || userInfo.getPassword() == null)
                throw new Exception("field cannot be null");
            else {
                developerRepository.save(userInfo);
                return new ResponseEntity<>("New Developer Added", HttpStatus.OK);
            }
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
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
