package com.example;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "client")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Client extends PanacheEntityBase {

    @Id
    @SequenceGenerator(name = "seq", sequenceName = "id_seq", allocationSize = 1)
    @GeneratedValue(generator = "seq", strategy = GenerationType.SEQUENCE)
    protected Long id;

    protected String name;

    protected String email;

    protected String phone;

    @OneToMany(mappedBy = "client")
    protected Set<CarsClients> cars;

}