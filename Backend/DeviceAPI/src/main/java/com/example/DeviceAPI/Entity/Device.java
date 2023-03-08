package com.example.DeviceAPI.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "device")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "device_id")
    private int device_id;
    @Column(name = "device_number")
    @NotNull
    private int deviceNumber;
    @Column(name = "arcId")
    @NotNull
    private int architectureId;
    @Column(name = "blocked_since")
    private String blocked_since;
    @Column(name = "blocked_till")
    private String blocked_till;
    @Column(name = "comments")
    private String comments;
    @Column(name = "devId",nullable = true)
    private Integer developerId;
    @Column(name = "device_model")
    @NotNull
    private String device_model;
    @Column(name = "mac")
    @NotNull
    private String mac;
    @Column(name = "rackId")
    @NotNull
    private int rackId;
}
