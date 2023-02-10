package com.example.SpringSecurityJWT.Service;

import com.example.SpringSecurityJWT.Config.UserInfoUserDetails;
import com.example.SpringSecurityJWT.Entity.UserInfo;
import com.example.SpringSecurityJWT.Repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
public class UserService {
    @Autowired
    private UserInfoRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public String addUser(UserInfo userInfo){
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        if(userRepository.findByEmail(userInfo.getEmail()).isPresent()){
            return "email already registered";
        }

        if(userRepository.findByName(userInfo.getName()).isPresent())
            return "user name already registered";
        if(userInfo.getName()==null || userInfo.getEmail()==null || userInfo.getRoles()==null || userInfo.getPassword()==null)
            return "field cannot be null";
        else{
            userRepository.save(userInfo);
            return "user added to system";
        }
    }

    public List<UserInfo> getUser(){
        return userRepository.findAll();
    }

    public String deleteById(Integer id){
        Optional<UserInfo> userInfo= userRepository.findById(id);
        if(userInfo.isEmpty()){
            return "user not found";
        }
        else {
            userRepository.deleteById(id);
            return "user record deleted ";
        }
    }
}
