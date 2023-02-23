package com.example.SpringSecurityJWT.Controller;

import com.example.SpringSecurityJWT.Entity.Developer;
import com.example.SpringSecurityJWT.Service.DeveloperService;
import com.example.SpringSecurityJWT.Service.JwtService;
import com.example.SpringSecurityJWT.dto.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
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
    public ResponseEntity addNewUser(@RequestBody Developer developer){
        return service.addUser(developer);
    }
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Map<String, String> token = new HashMap<>();
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            token.put("token", jwtService.generateToken((authRequest.getUsername())));
            token.put("username", authentication.getName());
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Username or password invalid",HttpStatus.NOT_FOUND);
        }
    }
}
