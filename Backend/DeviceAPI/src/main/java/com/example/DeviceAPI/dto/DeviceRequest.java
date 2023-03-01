package com.example.DeviceAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceRequest {
    private int device_id;
    @NotNull
    private int deviceNumber;
    @NotNull
    private String architectureName;
    private String blocked_since;
    private String blocked_till;
    private String comments;
    private String developerName;
    @NotNull
    private String device_model;
    @NotNull
    private String mac;
    @NotNull
    private String rackName;
}
