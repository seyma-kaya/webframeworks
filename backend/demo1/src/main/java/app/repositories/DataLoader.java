package app.repositories;

import app.models.Scooter;
import app.models.Trip;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {
    @Override
    @Transactional
    public void run(String... args) throws Exception {
        System.out.println("Running CommandLine Startup");
        this.createInititalScooters();
        this.createIntitialTrips();
    }

    @Autowired
    private EntityRepository<Scooter> scootersRepo;
    private void createInititalScooters(){
        List<Scooter> scooters = this.scootersRepo.findAll();
        if (scooters.size() > 0) return;
        System.out.println("Configuring some initial scooter data");

        for (int i = 0; i < 11; i++) {
            Scooter scooter = Scooter.createSampleScooter(0);
            this.scootersRepo.save(scooter);
        }
    }
    @Autowired
    private EntityRepository<Trip> tripsRepo;
    private void createIntitialTrips(){
        List<Trip> trips = this.tripsRepo.findAll();
        if (trips.size() > 0) return;
        System.out.println("Configuring some initial scooter data");

        for (int i = 0; i < 11; i++) {
            List<Scooter> list = this.scootersRepo.findAll();
            Trip trip = Trip.createSampleTrip(0, list.get(i));
            this.tripsRepo.save(trip);
        }

    }

}
