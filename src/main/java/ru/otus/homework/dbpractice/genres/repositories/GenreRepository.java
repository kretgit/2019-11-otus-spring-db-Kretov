package ru.otus.homework.dbpractice.genres.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.homework.dbpractice.genres.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends CrudRepository<Genre, String> {

    @Override
    List<Genre> findAll();

    Optional<Genre> findById(String id);

    Genre save(Genre genre);
}
