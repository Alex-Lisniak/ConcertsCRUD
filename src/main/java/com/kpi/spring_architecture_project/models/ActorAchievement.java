package com.kpi.spring_architecture_project.models;


import javax.persistence.*;

@Entity
@Table(name = "actors_achievements")
public class ActorAchievement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long id_actors;
    private String achievement;

    public ActorAchievement() {

    }

    public ActorAchievement(Long id_actors) {
        this.id_actors = id_actors;
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

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

}