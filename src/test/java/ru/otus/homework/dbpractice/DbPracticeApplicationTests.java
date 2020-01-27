package ru.otus.homework.dbpractice;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.homework.dbpractice.authors.repositories.AuthorRepositoryJpa;
import ru.otus.homework.dbpractice.books.domain.Book;
import ru.otus.homework.dbpractice.books.repositories.BookRepositoryJpa;
import ru.otus.homework.dbpractice.comments.domain.Comment;
import ru.otus.homework.dbpractice.comments.repositories.CommentRepositoryJpa;
import ru.otus.homework.dbpractice.genres.repositories.GenreRepositoryJpa;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
class DbPracticeApplicationTests {

    @Autowired
    AuthorRepositoryJpa authorRepositoryJpa;

    @Autowired
    BookRepositoryJpa bookRepositoryJpa;

    @Autowired
    GenreRepositoryJpa genreRepositoryJpa;

    @Autowired
    CommentRepositoryJpa commentRepositoryJpa;

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
        authorRepositoryJpa.findAll();
    }

    @Test
    @DisplayName("Все объекты Книга выгружаются")
    @Order(3)
    void shouldSelectAllBooks() {
        bookRepositoryJpa.findAll();
    }

    @Test
    @DisplayName("Все объекты Жанр выгружаются")
    @Order(4)
    void shouldSelectAllGenres() {
        genreRepositoryJpa.findAll();
    }

    @Test
    @DisplayName("Новая книга создается")
    @Order(5)
    void shouldCreateNewBook() {
        Book book = Book.builder()
                .name("test name")
                .description("test description")
                .build();
        testBookId = bookRepositoryJpa.save(book);
    }

    @Test
    @DisplayName("Комментарий к тестовой книге пишется")
    @Order(6)
    void shouldAddComment() {
        Comment comment = Comment.builder()
                .book(bookRepositoryJpa.findById(testBookId))
                .commentText("some text here")
                .build();
        commentRepositoryJpa.save(comment);
    }

    @Test
    @DisplayName("Тестовая книга удаляется")
    @Order(7)
    void shouldDeleteTestnewBook() {
        bookRepositoryJpa.deleteBook(testBookId);
    }

}
