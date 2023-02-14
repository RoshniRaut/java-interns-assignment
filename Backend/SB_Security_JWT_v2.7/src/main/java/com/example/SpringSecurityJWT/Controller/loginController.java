package com.example.SpringSecurityJWT.Controller;

import com.example.SpringSecurityJWT.Entity.UserInfo;
import com.example.SpringSecurityJWT.Service.JwtService;
import com.example.SpringSecurityJWT.Service.UserService;
import com.example.SpringSecurityJWT.dto.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@CrossOrigin(origins = "http://localhost:8080")
public class loginController {
    @Autowired
    private UserService service;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @GetMapping("/")
    String welcome(){
        return "welcome hye";
    }
    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    String admin() {
        return "only admin can have access";
    }
    @GetMapping("/user")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    String user(){
        return "only users has access";
    }

    @GetMapping("/userAdmin")
    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    //get user details from the token
    String user_admin(){
        return "both has access";
    }
    @GetMapping("/all")
    public List<UserInfo> getAll(){
        return service.getUser();
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id){
        return service.deleteById(id);
    }
    @PostMapping("/newUser")
    public String addNewUser(@RequestBody UserInfo userInfo){
        return service.addUser(userInfo);
    }
    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest){
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(authRequest.getUsername());
        }else{
            return "invalid";
        }
    }
}
