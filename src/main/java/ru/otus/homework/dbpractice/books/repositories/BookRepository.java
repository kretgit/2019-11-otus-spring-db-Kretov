package ru.otus.homework.dbpractice.books.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.dbpractice.books.domain.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book save(Book book);

    List<Book> findAll();

    Book findById(long id);

    void deleteById(long id);

}
