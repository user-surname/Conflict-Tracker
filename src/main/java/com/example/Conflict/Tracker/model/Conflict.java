package com.example.Conflict.Tracker.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "conflicts")
public class Conflict {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Lob // Per a textos llargs (es traduirà a CLOB o TEXT)
    private String description;

    private LocalDateTime startDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private Status status; //     ACTIVE,FROZEN,ENDED


    // --- Aquí la relació inversa ---
    // Moltes incidències pertanyen a un client.

    @ManyToMany
    @JoinTable(
            name = "conflict_country",
            joinColumns = @JoinColumn(name = "conflict_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id")
    )
    private List<Country> countries;

    @OneToMany(
            mappedBy = "conflict",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Faction> factions;

    @OneToMany(
            mappedBy = "conflict",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Event> events;

    // Constructors, Getters i Setters...

    public Conflict() {
    }

    public Conflict(Long id, String name, String description, Status status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public Conflict(String name, String description, Status status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setStatus(Status status) {
        this.status = status;
    }

    // Important: Cal sobreescriure equals() i hashCode()
    // si es treballa amb entitats fora de la transacció (DTOs, etc.),
    // però per a un exemple inicial, ho podem ometre.


    public Long getId() {
        return id;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public List<Faction> getFactions() {
        return factions;
    }

    public List<Event> getEvents() {
        return events;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public Status getStatus() {
        return status;
    }
}
