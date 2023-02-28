package com.example.DeviceAPI.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="developer")
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="dev_id")
    private int id;

    @NotNull(message="username cannot be null")
    @Column(name = "name")
    private String name;
    
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",flags = Pattern.Flag.CASE_INSENSITIVE,message="Invalid email")
    @NotNull(message="email cannot be null")
    @Column(name = "email")
    private String email;
    
    @NotNull(message="password cannot be null")
    @Column(name="password")
    private String password;
}
