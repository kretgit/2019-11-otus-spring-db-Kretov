package ru.otus.homework.dbpractice.genres.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework.dbpractice.genres.domain.Genre;

public interface GenreRepository extends ReactiveMongoRepository<Genre, String> {

    @Override
    Flux<Genre> findAll();

    Mono<Genre> findById(String id);

    Mono<Genre> save(Genre genre);
}
