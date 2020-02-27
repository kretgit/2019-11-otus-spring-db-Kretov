package ru.otus.homework.dbpractice.authors.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework.dbpractice.authors.domain.Author;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {

    @Override
    Flux<Author> findAll();

    Mono<Author> findById(String id);

    Flux<Author> findByName(String name);

    Mono<Author> save (Author author);

    @Override
    Mono<Boolean> existsById(String id);
}
