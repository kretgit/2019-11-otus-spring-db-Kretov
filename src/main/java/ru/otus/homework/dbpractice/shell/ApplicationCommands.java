package ru.otus.homework.dbpractice.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.dbpractice.authors.domain.Author;
import ru.otus.homework.dbpractice.authors.repositories.AuthorRepositoryJpa;
import ru.otus.homework.dbpractice.books.domain.Book;
import ru.otus.homework.dbpractice.books.repositories.BookRepositoryJpa;
import ru.otus.homework.dbpractice.comments.domain.Comment;
import ru.otus.homework.dbpractice.comments.repositories.CommentRepositoryJpa;
import ru.otus.homework.dbpractice.genres.domain.Genre;
import ru.otus.homework.dbpractice.genres.repositories.GenreJpa;

import java.util.List;

@ShellComponent
public class ApplicationCommands {

    private final BookRepositoryJpa bookRepositoryJpa;

    private final GenreJpa genreJpa;

    private final AuthorRepositoryJpa authorRepositoryJpa;

    private final CommentRepositoryJpa commentRepositoryJpa;

    @Autowired
    public ApplicationCommands(BookRepositoryJpa bookRepositoryJpa, GenreJpa genreJpa, AuthorRepositoryJpa authorRepositoryJpa, CommentRepositoryJpa commentRepositoryJpa) {
        this.bookRepositoryJpa = bookRepositoryJpa;
        this.genreJpa = genreJpa;
        this.authorRepositoryJpa = authorRepositoryJpa;
        this.commentRepositoryJpa = commentRepositoryJpa;
    }

    //genre commands
    @ShellMethod(value = "Get all genres", key = {"genres get", "ge get"})
    public List<Genre> getAllGenres() {
        return genreJpa.findAll();
    }

    @ShellMethod(value = "add genre", key = {"genre add", "ge add"})
    public long addNewGenre(String name, String description) {
        Genre genre = Genre.builder()
                .name(name)
                .description(description)
                .build();
        return genreJpa.save(genre);
    }

    @ShellMethod(value = "find genre", key = {"genre find", "ge find"})
    public Genre getGenreById(long id) {
        return genreJpa.findById(id);
    }

    //author commands
    @ShellMethod(value = "get all authors", key = {"authors get", "au get"})
    public List<Author> getAllAuthors() {
        return authorRepositoryJpa.findAll();
    }

    @ShellMethod(value = "add author", key = {"author add", "au add"})
    public long addNewAuthor(String name) {
        Author author = Author.builder()
                .fullName(name)
                .build();
        return authorRepositoryJpa.save(author);
    }

    @ShellMethod(value = "find author", key = {"author find", "au find"})
    public Author getAuthorById(long id) {
        return authorRepositoryJpa.findById(id);
    }

    //book commands
    @ShellMethod(value = "get all books", key = {"books get", "bo get"})
    public List<Book> getAllBooks() {
        return bookRepositoryJpa.findAll();
    }

    @ShellMethod(value = "add new book", key = {"book add", "bo add"})
    public long addNewBook(String bookName, String bookDesc, @ShellOption(defaultValue = "is_null") String authorId, @ShellOption(defaultValue = "is_null") String genreId) {
        Book book = Book.builder()
                .name(bookName)
                .description(bookDesc)
                .author(authorId.equals("is_null") ? null : authorRepositoryJpa.findById(Long.valueOf(authorId)))
                .genre(genreId.equals("is_null") ? null : genreJpa.findById(Long.valueOf(genreId)))
                .build();
        return bookRepositoryJpa.save(book);
    }

    @ShellMethod(value = "update book", key = {"book update", "bo upd"})
    public void updateBook(long bookId, long authorId, long genreId) {
        bookRepositoryJpa.updateBook(bookId, authorId, genreId);
    }

    @ShellMethod(value = "delete book", key = {"book delete", "bo del"})
    public void deleteBook(long id) {
        bookRepositoryJpa.deleteBook(id);
    }

    @ShellMethod(value = "find book", key = {"book find", "bo find"})
    public Book getBookById(long id) {
        return bookRepositoryJpa.findById(id);
    }

    //comment commands
    @ShellMethod(value = "add comment", key = {"comment add", "co add"})
    public long addNewComment(long bookId, String commentText) {
        Comment comment = Comment.builder()
                .book(bookRepositoryJpa.findById(bookId))
                .commentText(commentText)
                .build();
        return commentRepositoryJpa.save(comment);
    }

    @ShellMethod(value = "get all comment by bookId", key = {"comment get", "co get"})
    public List<Comment>getAllCommentsByBookId(long bookId) {
        return commentRepositoryJpa.findAllByBookId(bookId);
    }
}
