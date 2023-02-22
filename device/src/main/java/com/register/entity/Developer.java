package com.register.entity;

import javax.persistence.*;

@Entity
@Table(name = "developers")
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dev_id")
    private int dev_id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;

    public Developer(){

    }

    private Developer(int id, String name, String email){
        this.dev_id = id;
        this.name = name;
        this.email = email;
    }

    public int getDev_id() {
        return dev_id;
    }

    public void setDev_id(int dev_id) {
        this.dev_id = dev_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
