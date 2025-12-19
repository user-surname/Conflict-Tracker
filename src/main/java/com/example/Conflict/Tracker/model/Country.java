package com.example.Conflict.Tracker.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    private String name;

    @ManyToMany(mappedBy = "countries")
    private List<Conflict> conflicts = new ArrayList<>();

    @ManyToMany(mappedBy = "supportingCountries")
    private List<Faction> factions = new ArrayList<>();
}

