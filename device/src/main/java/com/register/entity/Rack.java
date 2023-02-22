package com.register.entity;

import javax.persistence.*;

@Entity
@Table(name = "rack")
public class Rack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rack_id")
    private int rack_id;
    @Column(name = "rack_name")
    private String rack_name;

    public int getRack_id() {
        return rack_id;
    }

    public void setRack_id(int rack_id) {
        this.rack_id = rack_id;
    }

    public String getRack_name() {
        return rack_name;
    }

    public void setRack_name(String rack_name) {
        this.rack_name = rack_name;
    }

}
