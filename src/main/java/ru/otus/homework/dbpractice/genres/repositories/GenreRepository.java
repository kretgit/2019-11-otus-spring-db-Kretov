package ru.otus.homework.dbpractice.genres.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.dbpractice.genres.domain.Genre;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Override
    List<Genre> findAll();

    Genre findById(long id);

    Genre save (Genre genre);
}
