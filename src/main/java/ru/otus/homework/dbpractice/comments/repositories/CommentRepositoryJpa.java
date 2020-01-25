package ru.otus.homework.dbpractice.comments.repositories;

import ru.otus.homework.dbpractice.comments.domain.Comment;

import java.util.List;

public interface CommentRepositoryJpa {

    long save (Comment comment);

    List<Comment> findAllByBookId(long bookId);
}
