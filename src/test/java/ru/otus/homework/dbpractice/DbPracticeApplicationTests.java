package ru.otus.homework.dbpractice;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.homework.dbpractice.authors.repositories.AuthorJpa;
import ru.otus.homework.dbpractice.books.domain.Book;
import ru.otus.homework.dbpractice.books.repositories.BookJpa;
import ru.otus.homework.dbpractice.comments.domain.Comment;
import ru.otus.homework.dbpractice.comments.repositories.CommentJpa;
import ru.otus.homework.dbpractice.genres.repositories.GenreJpa;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
class DbPracticeApplicationTests {

    @Autowired
    AuthorJpa authorJpa;

    @Autowired
    BookJpa bookJpa;

    @Autowired
    GenreJpa genreJpa;

    @Autowired
    CommentJpa commentJpa;

    private static long testBookId;

    @Test
    @DisplayName("поднимается контекст")
    @Order(1)
    void contextLoads() {
    }

    @Test
    @DisplayName("Все объекты Автор выгружаются")
    @Order(2)
    void shouldSelectAllAuthors() {
        authorJpa.findAll();
    }

    @Test
    @DisplayName("Все объекты Книга выгружаются")
    @Order(3)
    void shouldSelectAllBooks() {
        bookJpa.findAll();
    }

    @Test
    @DisplayName("Все объекты Жанр выгружаются")
    @Order(4)
    void shouldSelectAllGenres() {
        genreJpa.findAll();
    }

    @Test
    @DisplayName("Новая книга создается")
    @Order(5)
    void shouldCreateNewBook() {
        Book book = Book.builder()
                .name("test name")
                .description("test description")
                .build();
        testBookId = bookJpa.save(book);
    }

    @Test
    @DisplayName("Комментарий к тестовой книге пишется")
    @Order(6)
    void shouldAddComment() {
        Comment comment = Comment.builder()
                .book(bookJpa.findById(testBookId))
                .commentText("some text here")
                .build();
        commentJpa.save(comment);
    }

    @Test
    @DisplayName("Тестовая книга удаляется")
    @Order(7)
    void shouldDeleteTestnewBook() {
        bookJpa.deleteBook(testBookId);
    }

}
