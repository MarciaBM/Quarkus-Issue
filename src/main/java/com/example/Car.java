package com.example;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class Car extends PanacheEntityBase {

    @Id
    @SequenceGenerator(name = "seq_car", sequenceName = "id_seq_car", allocationSize = 1)
    @GeneratedValue(generator = "seq_car", strategy = GenerationType.SEQUENCE)
    protected Long id;

    protected String brand;


    protected String model;

    @OneToMany(mappedBy = "car")
    protected Set<CarsClients> clients;
}
