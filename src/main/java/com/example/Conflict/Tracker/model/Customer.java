package com.example.Conflict.Tracker.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 100, name = "first_name")
    private String firstName;
    @Column(length = 100)
    private String lastName;
    @Column(unique = true)
    private String email;

    protected Customer() {}

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

    @OneToMany(
            mappedBy = "customer", // "customer" és el NOM DEL CAMP a la classe Incidencia
            cascade = CascadeType.ALL, // Si esborro un client, esborra les seves incidències
            orphanRemoval = true, // Si trec una incidència de la llista, s'esborra
            fetch = FetchType.LAZY // No carreguis les incidències fins que no les demani
    )
    private List<Conflict> incidencies;

    // És important tenir mètodes per mantenir la consistència
    public void addIncidencia(Conflict incidencia) {
        incidencies.add(incidencia);
        incidencia.setCustomer(this);
    }

    public void removeIncidencia(Conflict incidencia) {
        incidencies.remove(incidencia);
        incidencia.setCustomer(null);
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public List<Conflict> getIncidencies() {
        return incidencies;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
