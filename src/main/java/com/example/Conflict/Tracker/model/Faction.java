package com.example.Conflict.Tracker.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "factiond")
public class Faction {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conflict_id") // columna FK en Faction
    private Conflict conflict;

    @ManyToMany
    @JoinTable(
            name = "faction_country",
            joinColumns = @JoinColumn(name = "faction_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id")
    )
    private List<Country> countries;

    public Faction(String name) {
        this.name = name;
    }

    public Faction() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Conflict getConflict() {
        return conflict;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setConflict(Conflict conflict) {
        this.conflict = conflict;
    }

    public List<Country> getCountries() {
        return countries;
    }
}

