package com.example;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CarsClientsRep implements PanacheRepository<CarsClients> {

    public Uni<Void> addClientsAndCars(List<Client> clients, List<Car> cars) {

        return getSession().chain(session -> {
            List<CarsClients> carsClientsList = new ArrayList<>();
            for (Client client : clients) {
                for (Car car : cars) {
                    CarsClients carsClients = new CarsClients(car, client, "location");
                    carsClientsList.add(carsClients);
                }
            }
            session.setBatchSize(5);
            return persist(carsClientsList).chain(this::flush);
        });
    }
}
