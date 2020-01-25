package ru.otus.homework.dbpractice.authors.repositories;

import ru.otus.homework.dbpractice.authors.domain.Author;

import java.util.List;

public interface AuthorRepositoryJpa {

    long save(Author author);

    List<Author> findAll();

    Author findById(long id);

}
