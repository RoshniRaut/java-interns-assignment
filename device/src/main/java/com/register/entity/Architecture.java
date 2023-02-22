package com.register.entity;

import javax.persistence.*;

@Entity
@Table(name = "architecture")
public class Architecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "arc_id")
    private int architecture_id;
    @Column(name = "arc_name")
    private String architecture_name;

    public int getArchitecture_id() {
        return architecture_id;
    }

    public void setArchitecture_id(int architecture_id) {
        this.architecture_id = architecture_id;
    }

    public String getArchitecture_name() {
        return architecture_name;
    }

    public void setArchitecture_name(String architecture_name) {
        this.architecture_name = architecture_name;
    }
}
