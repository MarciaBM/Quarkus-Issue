package com.example;

import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Path("/hello")
public class ExampleResource {

    @Inject
    ClientRep clientRep;

    @Inject
    CarRep carRep;

    @Inject
    CarsClientsRep carsClientsRep;

//    @POST
//    @Produces(MediaType.TEXT_PLAIN)
//    @WithTransaction
//    public Uni<String> hello() {
//        List<Client> clients = new ArrayList<>();
//        for(int i = 0; i < 80000; i++) {
//            Client client = new Client();
//            client.name = "name" + i;
//            client.email = "email" + i;
//            client.phone = "phone" + i;
//            clients.add(client);
//        }
//        LocalDateTime start = LocalDateTime.now();
//        return clientRep.addClients2(clients).chain(() -> {
//            LocalDateTime end = LocalDateTime.now();
//            LocalDateTime res = LocalDateTime.from(start);
//            var min = res.until(end, ChronoUnit.MINUTES);
//            res = res.plusMinutes(min);
//
//            var sec = res.until(end, ChronoUnit.SECONDS);
//            res = res.plusSeconds(sec);
//            return Uni.createFrom().item("Time: " +min + " minutos e " + sec + " segundos");
//        });
//    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @WithTransaction
    public Uni<Void> hello() {
        List<Client> clients = new ArrayList<>();
        for(int i = 0; i < 100; i++) {
            ClientA client = new ClientA();
            client.name = "name" + i;
            client.email = "email" + i;
            client.phone = "phone" + i;
            clients.add(client);
        }

        List<Car> cars = new ArrayList<>();

        for(int i = 0; i < 2; i++) {
            Car car = new Car();
            car.brand = "brand" + i;
            car.model = "model" + i;
            cars.add(car);
        }

        return carRep.persist(cars)
                .chain(() -> clientRep.addClients(clients))
                .chain(() -> carsClientsRep.addClientsAndCars(clients,cars)).replaceWithVoid();

    }
}
