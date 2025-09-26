package app.repositories;

import app.model.Scooter;
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
public class ScootersRepositoryJpa implements EntityRepository<Scooter>{

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Scooter> findAll() {
        TypedQuery<Scooter> query =
                this.entityManager.createQuery(
                        "select b from Scooter b", Scooter.class);
        return query.getResultList();
    }

    @Override
    public Scooter findById(long id) {
        return this.entityManager.find(Scooter.class, id);
    }

    @Override
    public Scooter save(Scooter scooter) {
        return this.entityManager.merge(scooter);
    }

    @Override
    public Scooter deleteById(long id) {
        Scooter scooter = this.findById(id);
        this.entityManager.remove(scooter);
        return scooter;
    }

    @Override
    public List<Scooter> findByQuery(String jpqlName, Object... params) {
        TypedQuery<Scooter> query =
                this.entityManager.createNamedQuery(jpqlName, Scooter.class);

        for (int i = 0; i < params.length; i++) {
            query.setParameter(i+1, params[i]);
        }

        return query.getResultList();
    }
}
