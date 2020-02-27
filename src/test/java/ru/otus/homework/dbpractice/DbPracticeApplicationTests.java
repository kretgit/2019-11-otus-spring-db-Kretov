package ru.otus.homework.dbpractice;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import ru.otus.homework.dbpractice.authors.repositories.AuthorRepository;
import ru.otus.homework.dbpractice.books.domain.Book;
import ru.otus.homework.dbpractice.books.repositories.BookRepository;
import ru.otus.homework.dbpractice.comments.repositories.CommentRepository;
import ru.otus.homework.dbpractice.genres.repositories.GenreRepository;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class DbPracticeApplicationTests {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    CommentRepository commentRepository;

    private static Mono<Book> testBookId;

    @Test
    @DisplayName("поднимается контекст")
    @Order(1)
    void contextLoads() {
    }

    @Test
    @DisplayName("Все объекты Автор выгружаются")
    @Order(2)
    void shouldSelectAllAuthors() {
        authorRepository.findAll();
    }

    @Test
    @DisplayName("Все объекты Книга выгружаются")
    @Order(3)
    void shouldSelectAllBooks() {
        bookRepository.findAll();
    }

    @Test
    @DisplayName("Все объекты Жанр выгружаются")
    @Order(4)
    void shouldSelectAllGenres() {
        genreRepository.findAll();
    }

    @Test
    @DisplayName("Новая книга создается")
    @Order(5)
    void shouldCreateNewBook() {
        Book book = Book.builder()
                .name("test name")
                .description("test description")
                .build();
        testBookId = bookRepository.save(book);
    }

    @Test
    @DisplayName("Тестовая книга удаляется")
    @Order(7)
    void shouldDeleteTestnewBook() {
        bookRepository.deleteById(String.valueOf(testBookId));
    }

}
