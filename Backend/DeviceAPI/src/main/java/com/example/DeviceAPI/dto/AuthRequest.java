package com.example.DeviceAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    @NotNull(message="username cannot be null")
    private String username;
    @NotNull(message="password can not be null")
    private String password;
}
