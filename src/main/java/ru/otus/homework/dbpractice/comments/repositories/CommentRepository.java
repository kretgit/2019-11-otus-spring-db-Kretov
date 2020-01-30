package ru.otus.homework.dbpractice.comments.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.homework.dbpractice.comments.domain.Comment;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    Comment save(Comment comment);

    List<Comment> findAllByBookId(String bookId);
}
