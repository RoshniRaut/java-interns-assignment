package com.example.SpringSecurityJWT.Controller;

import com.example.SpringSecurityJWT.Entity.Developer;
import com.example.SpringSecurityJWT.Service.DeveloperService;
import com.example.SpringSecurityJWT.Service.JwtService;
import com.example.SpringSecurityJWT.dto.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:8080")
public class developerController {
    @Autowired
    private DeveloperService service;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @GetMapping("/testing")
    String welcome(){
        return "testing...";
    }

    @GetMapping("/validate")
    //get user details from the token
    String user_admin(){
        return "valid";
    }
    @GetMapping("/allDeveloper")
    public List<Developer> getAll(){
        return service.getUser();
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id){
        return service.deleteById(id);
    }
    @PostMapping("/addDeveloper")
    public String addNewUser(@RequestBody Developer developer){
        return service.addUser(developer);
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
