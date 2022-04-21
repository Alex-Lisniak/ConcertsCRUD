package com.kpi.spring_architecture_project.models;

import javax.persistence.*;

@Entity
@Table(name = "concerts")
public class Concert {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String place;
    private String designation;
    private String distributionOfSeats;
    private Long costOfTicket = 0L;
    private Long countOfSeats = 0L;
    private Long views = 0L;
    private double popularity = 0;

    public Concert() {

    }

    public Concert(String place , String designation , String distributionOfSeats,  Long costOfTicket , Long countOfSeats) {
        this.distributionOfSeats = distributionOfSeats;
        this.designation = designation;
        this.place = place;
        this.costOfTicket = costOfTicket;
        this.countOfSeats = countOfSeats;
        calculatePopularity();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDistributionOfSeats() {
        return distributionOfSeats;
    }

    public void setDistributionOfSeats(String distributionOfSeats) {
        this.distributionOfSeats = distributionOfSeats;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Long getCostOfTicket() {
        return costOfTicket;
    }

    public void setCostOfTicket(Long costOfTicket) {
        this.costOfTicket = costOfTicket;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public Long getCountOfSeats() {
        return countOfSeats;
    }

    public void setCountOfSeats(Long countOfSeats) {
        this.countOfSeats = countOfSeats;
    }

    public double getPopularity() {
        return popularity;
    }

    public void calculatePopularity() {
        this.popularity = views*50 + countOfSeats - costOfTicket/1000;
    }

    public void incrementViews(){ this.views = this.views + 1;}
}
