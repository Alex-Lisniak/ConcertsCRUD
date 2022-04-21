package com.kpi.spring_architecture_project.models;

import javax.persistence.*;

@Entity
@Table(name = "actors")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name_actor;

    public Actor() {

    }

    public Actor(String name_actor){ this.name_actor = name_actor;}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName_actor() {
        return name_actor;
    }

    public void setName_actor(String name_actor) {
        this.name_actor = name_actor;
    }
}
