package app.repositories;

import app.model.Scooter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntityRepository<E> {
    List<E> findAll();
    E findById(long id);
    E save(E entity);
    E deleteById(long id);
    List<E> findByQuery(String jpqlName, Object... params);
}
