package com.kpi.spring_architecture_project.models;

import javax.persistence.*;


@Entity
@Table(name = "tickets_cost")
public class TicketsCost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long id_concert;
    private Long cost;


    public TicketsCost() {

    }

    public TicketsCost(Long id_concert, Long cost) {
        this.id_concert = id_concert;
        this.cost = cost;
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

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }
}