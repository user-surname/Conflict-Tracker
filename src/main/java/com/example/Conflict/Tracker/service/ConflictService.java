package com.example.Conflict.Tracker.service;

import com.example.Conflict.Tracker.dto.ConflictDTO;
import com.example.Conflict.Tracker.mapper.ConflictMapper;
import com.example.Conflict.Tracker.model.ConflictStatus;
import com.example.Conflict.Tracker.model.Customer;
import com.example.Conflict.Tracker.model.Conflict;
import com.example.Conflict.Tracker.repository.ConflictRepository;
import com.example.Conflict.Tracker.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConflictService {

    @Autowired
    private CustomerRepository customerRepository;

    // No necessitem 'incidenciaRepository.save()' si fem servir Cascade
    // @Autowired
    // private IncidenciaRepository incidenciaRepository;

    /**
     * Aquest és el mètode que hauries de cridar.
     * @Transactional assegura que tot el mètode s'executa
     * o es fa "rollback" si hi ha algun error.
     */
    @Transactional
    public Conflict crearIncidenciaPerCustomer(Long customerId) {

        // --- PAS 1: Recuperar el "pare" (l'amo de la relació "One") ---
        // Fem servir findById. Si no el troba, llançarà una excepció.
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer no trobat amb ID: " + customerId));

        // --- PAS 2: Crear el "fill" (la nova incidència) ---
        // (Aquí podríem fer servir un Mapper, però ho fem manual per claredat)
        Conflict novaIncidencia = new Conflict();
        novaIncidencia.setTitol("Incidència de prova");
        novaIncidencia.setDescripcio("Incidència de prova");
        novaIncidencia.setEstat(ConflictStatus.ACTIVE);

        // --- PAS 3: ASSOCIAR (Aquest és el pas MÉS important) ---
        // Cridem el mètode 'helper' que vam crear a l'entitat Customer.
        // Això fa DUES coses:
        // 1. Afegeix la incidència a la llista (costat Java)
        // 2. Assigna el 'customer' a la incidència (costat JPA)
        customer.addIncidencia(novaIncidencia);

        // --- PAS 4: DESAR ---
        // Com que tenim CascadeType.ALL al Customer,
        // només hem de desar el 'customer'.
        // JPA (Hibernate) veurà que la llista 'incidencies' té un
        // nou objecte i farà l'INSERT a la taula 'incidencies' automàticament.
        customerRepository.save(customer);

        return novaIncidencia;
    }

    public List<ConflictDTO> getAll() {
        List<Conflict> Conflicts = new ArrayList<>();
        conflictRepository.findAll().forEach(Conflicts::add);
        return ConflictMapper.toDTO(Conflicts);
        //return customers.stream().map(this::convertToCustomerDTO).toList();
        //return (List<Customer>)customerRepository.findAll();
    }

    public List<ConflictDTO> getByStatus(ConflictStatus status) {

    }
}
