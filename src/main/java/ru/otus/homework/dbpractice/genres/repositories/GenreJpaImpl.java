package ru.otus.homework.dbpractice.genres.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.dbpractice.genres.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class GenreJpaImpl implements GenreJpa {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long save(Genre genre) {
        entityManager.persist(genre);
        return genre.getId();
    }

    @Override
    public List<Genre> findAll() {
        return entityManager.createQuery("from Genre", Genre.class).getResultList();
    }

    @Override
    public Genre findById(long id) {
        return entityManager.find(Genre.class, id);
    }
}
