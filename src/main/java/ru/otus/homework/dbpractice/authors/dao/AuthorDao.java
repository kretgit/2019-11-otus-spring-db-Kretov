package ru.otus.homework.dbpractice.authors.dao;

import ru.otus.homework.dbpractice.authors.domain.Author;

import java.util.List;

public interface AuthorDao {
    List<Author> getAllAuthors();
}
