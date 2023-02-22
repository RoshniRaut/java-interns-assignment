package com.example.SpringSecurityJWT.Service;

import com.example.SpringSecurityJWT.Config.DeveloperUserDetails;
import com.example.SpringSecurityJWT.Entity.Developer;
import com.example.SpringSecurityJWT.Repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DeveloperUserDetailsService implements UserDetailsService {
    @Autowired
    private DeveloperRepository developerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Developer> developer=developerRepository.findByName(username);

        return developer.map(DeveloperUserDetails::new)
                .orElseThrow(()->new UsernameNotFoundException("user not found "+username));

    }
}
