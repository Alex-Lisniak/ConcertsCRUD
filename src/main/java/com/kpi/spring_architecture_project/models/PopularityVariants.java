package com.kpi.spring_architecture_project.models;

import javax.persistence.*;


@Entity
@Table(name = "popularity_variants")
public class PopularityVariants {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long id_performance;
    private String popularity;

    public PopularityVariants() {

    }

    public PopularityVariants(Long id_performance, String popularity) {
        this.id_performance = id_performance;
        this.popularity = popularity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_performance() {
        return id_performance;
    }

    public void setId_performance(Long id_performance) {
        this.id_performance = id_performance;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }
}