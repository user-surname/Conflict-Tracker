package com.example.Conflict.Tracker.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "factions")
public class Faction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conflict_id")
    private Conflict conflict;

    @ManyToMany
    @JoinTable(
            name = "faction_countries",
            joinColumns = @JoinColumn(name = "faction_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id")
    )
    private List<Country> supportingCountries = new ArrayList<>();
}

