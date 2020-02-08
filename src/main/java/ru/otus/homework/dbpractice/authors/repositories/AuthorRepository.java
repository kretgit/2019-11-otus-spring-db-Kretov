package ru.otus.homework.dbpractice.authors.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.homework.dbpractice.authors.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, String> {

    @Override
    List<Author> findAll();

    Optional<Author> findById(String id);

    Author findByFullName(String name);

    Author save(Author author);
}
