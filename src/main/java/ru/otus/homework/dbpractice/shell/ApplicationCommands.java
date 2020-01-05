package ru.otus.homework.dbpractice.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.dbpractice.authors.domain.Author;
import ru.otus.homework.dbpractice.authors.dao.AuthorDao;
import ru.otus.homework.dbpractice.books.domain.Book;
import ru.otus.homework.dbpractice.books.dao.BookDao;
import ru.otus.homework.dbpractice.genres.domain.Genre;
import ru.otus.homework.dbpractice.genres.dao.GenreDao;

import java.util.List;

@ShellComponent
public class ApplicationCommands {

    private final GenreDao genreDao;
    private final AuthorDao authorDao;
    private final BookDao bookDao;

    @Autowired
    public ApplicationCommands(GenreDao genreDao, AuthorDao authorDao, BookDao bookDao) {
        this.genreDao = genreDao;
        this.authorDao = authorDao;
        this.bookDao = bookDao;
    }

    @ShellMethod(value = "Get all genres", key = {"genres get", "ge get"})
    public List<Genre> getAllGenres() {
        return genreDao.getAllGenres();
    }

    @ShellMethod(value = "get all authors", key = {"authors get", "au get"})
    public List<Author> getAllAuthors() {
        return authorDao.getAllAuthors();
    }

    @ShellMethod(value = "get all books", key = {"books get", "bo get"})
    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    @ShellMethod(value = "add new book", key = {"book add", "bo add"})
    public String addNewBook(String bookName, String bookDesc, @ShellOption(defaultValue = "is_null") String authorId, @ShellOption(defaultValue = "is_null") String genreId) {
        Book book = Book.builder()
                .name(bookName)
                .desc(bookDesc)
                .authorId(authorId.equals("is_null") ? null : authorId)
                .genreId(genreId.equals("is_null") ? null : genreId)
                .build();
        return bookDao.createBook(book);
    }

    @ShellMethod(value = "update book", key = {"book update", "bo upd"})
    public void updateBook(String bookId, String authorId, String genreId) {
        bookDao.updateBook(bookId, authorId, genreId);
    }

    @ShellMethod(value = "delete book", key = {"book delete", "bo del"})
    public void deleteBook(String bookId) {
        bookDao.deleteBook(bookId);
    }
}
