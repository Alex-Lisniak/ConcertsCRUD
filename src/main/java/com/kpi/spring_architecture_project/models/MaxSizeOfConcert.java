package com.kpi.spring_architecture_project.models;

import javax.persistence.*;


@Entity
@Table(name = "max_size_of_concerts")
public class MaxSizeOfConcert {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long id_concert;
    private Long size;


    public MaxSizeOfConcert() {

    }

    public MaxSizeOfConcert(Long id_concert, Long size) {
        this.id_concert = id_concert;
        this.size = size;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_concert() {
        return id_concert;
    }

    public void setId_concert(Long id_concert) {
        this.id_concert = id_concert;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }


}