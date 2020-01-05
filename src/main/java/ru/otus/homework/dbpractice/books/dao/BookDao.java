package ru.otus.homework.dbpractice.books.dao;

import ru.otus.homework.dbpractice.books.domain.Book;

import java.util.List;

public interface BookDao {

    List<Book> getAllBooks();

    String createBook(Book book);

    void updateBook(String bookId, String authorId, String genreId);

    void deleteBook(String id);
}
