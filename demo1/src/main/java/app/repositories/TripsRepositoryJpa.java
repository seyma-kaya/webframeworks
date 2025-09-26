package app.repositories;

import app.model.Scooter;
import app.model.Trip;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
@Primary
public class TripsRepositoryJpa implements EntityRepository<Trip>{

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Trip> findAll() {
        TypedQuery<Trip> query =
                this.entityManager.createQuery(
                        "select b from Trip b", Trip.class);
        return query.getResultList();
    }

    @Override
    public Trip findById(long id) {
        return this.entityManager.find(Trip.class, id);
    }

    @Override
    public Trip save(Trip entity) {
        return this.entityManager.merge(entity);
    }

    @Override
    public Trip deleteById(long id) {
        Trip trip = this.findById(id);
        if (!trip.getScooter().dissociateTrip(trip)) {
            System.out.println("Can't delete trip");
            return trip;
        }
        this.entityManager.remove(trip);
        return trip;
    }

    @Override
    public List<Trip> findByQuery(String jpqlName, Object... params) {
        TypedQuery<Trip> query =
                this.entityManager.createNamedQuery(jpqlName, Trip.class);

        for (int i = 0; i < params.length; i++) {
            query.setParameter(i+1, params[i]);
        }

        return query.getResultList();
    }
}
