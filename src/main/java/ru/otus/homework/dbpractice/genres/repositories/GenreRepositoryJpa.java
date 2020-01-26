package ru.otus.homework.dbpractice.genres.repositories;

import ru.otus.homework.dbpractice.genres.domain.Genre;

import java.util.List;

public interface GenreRepositoryJpa {

    long save (Genre genre);

    List<Genre> findAll();

    Genre findById(long id);
}
