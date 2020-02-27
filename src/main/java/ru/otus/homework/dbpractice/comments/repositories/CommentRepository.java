package ru.otus.homework.dbpractice.comments.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework.dbpractice.comments.domain.Comment;

public interface CommentRepository extends ReactiveMongoRepository<Comment, Long> {

    Mono<Comment> save(Comment comment);

    Flux<Comment> findAllByBookId(String bookId);
}
