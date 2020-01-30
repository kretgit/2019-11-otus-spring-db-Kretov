package ru.otus.homework.dbpractice.books.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.homework.dbpractice.books.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, String> {

    Book save(Book book);

    List<Book> findAll();

    Optional<Book> findById(String id);

    void deleteById(String id);

}
