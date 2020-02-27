package ru.otus.homework.dbpractice.books.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework.dbpractice.books.domain.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {

    Mono<Book> save(Book book);

    Flux<Book> findAll();

    Mono<Book> findById(String id);

    Mono<Void> deleteById(String id);
}
