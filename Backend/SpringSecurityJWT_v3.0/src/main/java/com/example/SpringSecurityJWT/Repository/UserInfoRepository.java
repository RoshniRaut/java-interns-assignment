package com.example.SpringSecurityJWT.Repository;

import com.example.SpringSecurityJWT.Entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo,Integer> {

    Optional<UserInfo> findByName(String username);

    @Query("Select u from UserInfo u where u.email=?1")
    Optional<UserInfo> findByEmail(String email);

    @Override
    List<UserInfo> findAll();

    @Override
    void deleteById(Integer integer);

    @Override
    Optional<UserInfo> findById(Integer integer);
}
