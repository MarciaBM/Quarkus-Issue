package com.example;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@IdClass(CarsClientsId.class)
@Table(name = "cars_clients")
public class CarsClients extends PanacheEntityBase {

    @ManyToOne
    @Id
    protected Car car;

    @ManyToOne
    @Id
    protected Client client;

    public CarsClients() {
    }

    public CarsClients(Car car, Client client, String location) {
        this.car = car;
        this.client = client;
        this.location = location;
    }

    protected String location;
}

class CarsClientsId implements Serializable {
    private Car car;

    private Client client;

    public CarsClientsId() {
    }
}
