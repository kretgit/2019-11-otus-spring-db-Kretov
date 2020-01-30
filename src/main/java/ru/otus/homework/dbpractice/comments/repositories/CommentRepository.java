package ru.otus.homework.dbpractice.comments.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.dbpractice.comments.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Comment save(Comment comment);

    List<Comment> findAllByBookId(long bookId);
}
