package ru.otus.homework.dbpractice.authors.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.dbpractice.authors.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class AuthorJpaImpl implements AuthorJpa {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long save(Author author) {
        entityManager.persist(author);
        return author.getId();
    }

    @Override
    public List<Author> findAll() {
        return entityManager.createQuery("from Author", Author.class)
                .getResultList();
    }

    @Override
    public Author findById(long id) {
       return entityManager.find(Author.class, id);
    }

}
