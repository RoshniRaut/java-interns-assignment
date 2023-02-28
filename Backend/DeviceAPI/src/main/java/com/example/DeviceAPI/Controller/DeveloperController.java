package com.example.DeviceAPI.Controller;

import com.example.DeviceAPI.Entity.Developer;
import com.example.DeviceAPI.Exceptions.AlreadyRegistered;
import com.example.DeviceAPI.Service.DeveloperService;
import com.example.DeviceAPI.Service.JwtService;
import com.example.DeviceAPI.dto.AuthRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DeveloperController {

    Logger logger = LoggerFactory.getLogger(DeveloperController.class);

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

    @GetMapping("/allDeveloper")
    public List<Developer> getAllDevelopers(){
        logger.info("getAllDevelopers method is called");
        return service.getUser();
    }
    @DeleteMapping("/developer/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        logger.info("Delete method for developer is called");
        return service.deleteById(id);
    }
    @PostMapping("/addDeveloper")
    public ResponseEntity<?> addNewDeveloper(@Valid @RequestBody Developer developer) throws AlreadyRegistered {
        logger.info("addDeveloper method is called");
        return service.addUser(developer);
    }
    @PostMapping("/authenticate")
    public Map<String, String> authenticateAndGetToken(@Valid @RequestBody AuthRequest authRequest) {
        logger.info("authenticateAndGetToken method is called");
        Map<String, String> token = new HashMap<>();
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            token.put("token", jwtService.generateToken((authRequest.getUsername())));
            token.put("username", authentication.getName());
            logger.info("Authentication token generated");
            return token;
        }
        else {
            logger.warn("password invalid");
            throw new UsernameNotFoundException("Username or password invalid");
        }
    }
}
