package ru.otus.homework.dbpractice.books.repositories;

import ru.otus.homework.dbpractice.books.domain.Book;

import java.util.List;

public interface BookJpa {

    long save (Book book);

    List<Book> findAll();

    Book findById(long id);

    void updateBook(long bookId, long authorId, long genreId);

    void deleteBook(long id);
}
