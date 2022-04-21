package com.kpi.spring_architecture_project.models;


import javax.persistence.*;

@Entity
@Table(name = "performances")
public class Performance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long id_actors;

    private Long id_concert;

    private String designation;

    public Performance() {

    }

    public Performance(Long id_actors, Long id_concert, String designation) {
        this.id_actors = id_actors;
        this.id_concert = id_concert;
        this.designation = designation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_actors() {
        return id_actors;
    }

    public void setId_actors(Long id_actors) {
        this.id_actors = id_actors;
    }

    public Long getId_concert() {
        return id_concert;
    }

    public void setId_concert(Long id_concert) {
        this.id_concert = id_concert;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}