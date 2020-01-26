package ru.otus.homework.dbpractice.authors.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.dbpractice.authors.domain.Author;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Override
    List<Author> findAll();

    Author findById(long id);

    Author findByFullName(String name);

    Author save(Author author);
}
