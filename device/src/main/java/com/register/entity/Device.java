package com.register.entity;

import javax.persistence.*;

@Entity
@Table(name = "device")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "device_id")
    private int device_id;
    @Column(name = "arcId")
    private int architectureId;
    @Column(name = "blocked_since")
    private String blocked_since;
    @Column(name = "blocked_till")
    private String blocked_till;
    @Column(name = "comments")
    private String comments;
    @Column(name = "devId")
    private int developerId;
    @Column(name = "device_model")
    private String device_model;
    @Column(name = "mac")
    private String mac;
    @Column(name = "rackId")
    private int rackId;

    public int getDevice_id() {
        return device_id;
    }

    public void setDevice_id(int device_id) {
        this.device_id = device_id;
    }

    public int getArchitectureId() {
        return architectureId;
    }

    public void setArchitectureId(int architectureId) {
        this.architectureId = architectureId;
    }

    public String getBlocked_since() {
        return blocked_since;
    }

    public void setBlocked_since(String blocked_since) {
        this.blocked_since = blocked_since;
    }

    public String getBlocked_till() {
        return blocked_till;
    }

    public void setBlocked_till(String blocked_till) {
        this.blocked_till = blocked_till;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(int developerId) {
        this.developerId = developerId;
    }

    public String getDevice_model() {
        return device_model;
    }

    public void setDevice_model(String device_model) {
        this.device_model = device_model;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public int getRackId() {
        return rackId;
    }

    public void setRackId(int rackId) {
        this.rackId = rackId;
    }
}
