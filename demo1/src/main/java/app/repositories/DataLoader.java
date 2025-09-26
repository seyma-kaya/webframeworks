package app.repositories;

import app.model.Scooter;
import app.model.Trip;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        System.out.println("Running CommandLine Startup");
        this.createInitialScooters();
        this.createInitialTrip();
    }

    @Autowired
    private EntityRepository<Scooter> scootersRepo;
    private void createInitialScooters(){
        List<Scooter> scooters = this.scootersRepo.findAll();
        if (scooters.size() > 0) {
            return;
        }

        for (int i = 0; i < 11; i++) {
            Scooter scooter;
            scooter = Scooter.createSampleScooter(0);
            this.scootersRepo.save(scooter);
        }
    }

    @Autowired
    private EntityRepository<Trip> tripRepo;

    private void createInitialTrip(){
        List<Trip> trips = this.tripRepo.findAll();
        if (trips.size() > 0) {
            return;
        }

        for (int i = 0; i < 11; i++){
            List<Scooter> list = this.scootersRepo.findAll();
            Trip trip;
            trip = Trip.createSampleTrip(0, list.get(i));
            this.tripRepo.save(trip);
        }
    }
}
