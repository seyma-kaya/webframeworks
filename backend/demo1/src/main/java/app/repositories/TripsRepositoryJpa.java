package app.repositories;

import app.models.Scooter;
import app.models.Trip;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@Repository("TRIPS.JPA")
@Transactional
public class TripsRepositoryJpa implements EntityRepository<Trip> {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Trip> findAll() {
        TypedQuery<Trip> query = manager.createQuery("select t from Trip t", Trip.class);
        return query.getResultList();
    }

    @Override
    public Trip findById(long id) {
        return manager.find(Trip.class, id);
    }

    @Override
    public Trip save(Trip trip) {
        return manager.merge(trip);
    }

    @Override
    public Trip deleteById(long id) {
        Trip trip = this.findById(id);
        if (!trip.getScooter().dissociateTrip(trip)){
            System.out.println("Can't delete trip");
            return trip;
        }
        manager.remove(trip);
        return trip;
    }

    @Override
    public List<Trip> findByQuery(String jpqlName, Object... params) {
        TypedQuery<Trip> query =
                this.manager.createNamedQuery(jpqlName, Trip.class);

        for (int i = 0; i < params.length; i++) {
            query.setParameter(i+1, params[i]);
        }
        return query.getResultList();
    }
}
