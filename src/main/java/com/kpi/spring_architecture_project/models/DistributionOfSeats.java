package com.kpi.spring_architecture_project.models;


import javax.persistence.*;

@Entity
@Table(name = "distribution_of_seats")
public class DistributionOfSeats {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long id_concert;
    private String type_of_distribution;

    public DistributionOfSeats() {

    }

    public DistributionOfSeats(Long id_concert, String type_of_distribution) {
        this.id_concert = id_concert;
        this.type_of_distribution = type_of_distribution;
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

    public String getType_of_distribution() {
        return type_of_distribution;
    }

    public void setType_of_distribution(String type_of_distribution) {
        this.type_of_distribution = type_of_distribution;
    }


}