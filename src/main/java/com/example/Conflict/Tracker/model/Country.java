package com.example.Conflict.Tracker.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;
    private String code;

    @ManyToMany(mappedBy = "countries")
    private List<Conflict> conflicts;

    @ManyToMany(mappedBy = "countries")
    private List<Faction> factions;

    protected Country() {}

    public Country(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public List<Conflict> getConflicts() {
        return conflicts;
    }
}

