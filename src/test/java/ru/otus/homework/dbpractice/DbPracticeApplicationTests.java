package ru.otus.homework.dbpractice;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.homework.dbpractice.authors.dao.AuthorDao;
import ru.otus.homework.dbpractice.books.dao.BookDao;
import ru.otus.homework.dbpractice.books.domain.Book;
import ru.otus.homework.dbpractice.genres.dao.GenreDao;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class DbPracticeApplicationTests {

    @Autowired
    AuthorDao authorDao;

    @Autowired
    BookDao bookDao;

    @Autowired
    GenreDao genreDao;

    private static String testBookId;

    @Test
    @DisplayName("поднимается контекст")
    @Order(1)
    void contextLoads() {
    }

    @Test
    @DisplayName("Все объекты Автор выгружаются")
    @Order(2)
    void shouldSelectAllAuthors() {
        authorDao.getAllAuthors();
    }

    @Test
    @DisplayName("Все объекты Книга выгружаются")
    @Order(3)
    void shouldSelectAllBooks() {
        bookDao.getAllBooks();
    }

    @Test
    @DisplayName("Все объекты Жанр выгружаются")
    @Order(4)
    void shouldSelectAllGenres() {
        genreDao.getAllGenres();
    }

    @Test
    @DisplayName("Новая книга создается")
    @Order(5)
    void shouldCreateNewBook() {
        Book book = Book.builder()
                .name("test name")
                .desc("test desc")
                .build();
        testBookId = bookDao.createBook(book);
    }

    @Test
    @DisplayName("Тестовая книга удаляется")
    @Order(6)
    void shouldDeleteTestnewBook() {
        bookDao.deleteBook(testBookId);
    }

}
