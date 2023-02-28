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
@Table(name = "rack")
public class Rack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rack_id")
    private int rack_id;
    @Column(name = "rack_name")
    @NotNull
    private String rackName;

}
