package ru.otus.homework.dbpractice.genres.dao;

import ru.otus.homework.dbpractice.genres.domain.Genre;

import java.util.List;

public interface GenreDao {
    List<Genre> getAllGenres();

}
