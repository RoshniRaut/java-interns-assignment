package com.example.SpringSecurityJWT.Repository;

import com.example.SpringSecurityJWT.Entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DeveloperRepository extends JpaRepository<Developer,Integer> {

    Optional<Developer> findByName(String username);

    @Query("Select u from Developer u where u.email=?1")
    Optional<Developer> findByEmail(String email);

    @Override
    List<Developer> findAll();

    @Override
    void deleteById(Integer integer);

    @Override
    Optional<Developer> findById(Integer integer);
}
