package com.example;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ClientRep implements PanacheRepository<Client> {

//    public Uni<Void> addClients1(List<Client> clients) {
//        return persist(clients).chain(this::flush).replaceWithVoid();
//    }
//
//    public Uni<Void> addClients2(List<Client> clients) {
//
//        return getSession().chain(session -> {
//            session.setBatchSize(100);
//            return persist(clients).chain(this::flush).replaceWithVoid();
//        });
//    }
//
//    public Uni<Void> addClients3(List<Client> clients) {
//        return getSession().chain(session -> {
//            int N_THREADS = 24;
//            int geomsPerThread = (int) (clients.size() / N_THREADS);
//            List<Uni<Void>> unis = new ArrayList<>();
//            for (int i = 0; i < N_THREADS; i++) {
//                int start = i * geomsPerThread;
//                int end = (i + 1) * geomsPerThread;
//                if (i == N_THREADS - 1)
//                    end = clients.size();
//                var subList = clients.subList(start, end);
//                unis.add(persist(subList).chain(this::flush));
//            }
//            return Uni.join().all(unis).usingConcurrencyOf(1).andFailFast().replaceWithVoid();
//        });
//    }
//
//    public Uni<Void> addClients4(List<Client> clients) {
//
//        return getSession().chain(session -> {
//            int counter = 0;
//            List<Uni<Void>> list = new ArrayList<>();
//            for (Client client : clients) {
//                if (counter % 100 == 0) {
//                    list.add(session.persist(client).chain(session::flush).chain(()->Uni.createFrom().item(session.clear())).replaceWithVoid());
//                } else {
//                    list.add(session.persist(client).replaceWithVoid());
//                }
//                counter++;
//            }
//            return Uni.join().all(list).andFailFast().replaceWithVoid();
//        });
//    }
//
//    public Uni<Void> addClients5(List<Client> clients) {
//        List<Uni<Client>> list = new ArrayList<>();
//        for (Client geom : clients) {
//            list.add(persistAndFlush(geom));
//        }
//        return Uni.join().all(list).andFailFast().replaceWithVoid();
//    }

    public Uni<Void> addClients(List<Client> clients) {
        return getSession().chain(session -> {
            session.setBatchSize(5);
            return persist(clients).chain(this::flush).chain(() -> {
                System.out.println("First client id: " + clients.get(0).id);
                return Uni.createFrom().voidItem();
            });
        });
    }
}