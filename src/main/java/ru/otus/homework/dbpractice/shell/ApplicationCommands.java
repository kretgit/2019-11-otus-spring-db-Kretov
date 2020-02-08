package ru.otus.homework.dbpractice.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.dbpractice.authors.domain.Author;
import ru.otus.homework.dbpractice.authors.repositories.AuthorRepository;
import ru.otus.homework.dbpractice.books.domain.Book;
import ru.otus.homework.dbpractice.books.repositories.BookRepository;
import ru.otus.homework.dbpractice.comments.domain.Comment;
import ru.otus.homework.dbpractice.comments.repositories.CommentRepository;
import ru.otus.homework.dbpractice.genres.domain.Genre;
import ru.otus.homework.dbpractice.genres.repositories.GenreRepository;

import java.util.List;

@ShellComponent
public class ApplicationCommands {

    private final BookRepository bookRepository;

    private final GenreRepository genreRepository;

    private final AuthorRepository authorRepository;

    private final CommentRepository commentRepository;

    @Autowired
    public ApplicationCommands(BookRepository bookRepository, GenreRepository genreRepository, AuthorRepository authorRepository, CommentRepository commentRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
        this.commentRepository = commentRepository;
    }

    //genre commands
    @ShellMethod(value = "Get all genres", key = {"genre get", "ge get"})
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @ShellMethod(value = "add genre", key = {"genre add", "ge add"})
    public String addNewGenre(String name, String description) {
        Genre genre = Genre.builder()
                .name(name)
                .description(description)
                .build();
        return genreRepository.save(genre).getId();
    }

    @ShellMethod(value = "find genre", key = {"genre find", "ge find"})
    public Genre getGenreById(String id) {
        return genreRepository.findById(id).orElse(null);
    }

    //author commands
    @ShellMethod(value = "get all authors", key = {"author get", "au get"})
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @ShellMethod(value = "add author", key = {"author add", "au add"})
    public String addNewAuthor(String name) {
        Author author = Author.builder()
                .fullName(name)
                .build();
        return authorRepository.save(author).getId();
    }

    @ShellMethod(value = "find author", key = {"author find", "au find"})
    public Author getAuthorById(String id) {
        return authorRepository.findById(id).orElse(null);
    }

    //book commands
    @ShellMethod(value = "get all books", key = {"book get", "bo get"})
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @ShellMethod(value = "add new book", key = {"book add", "bo add"})
    public String addNewBook(String bookName, String bookDesc, @ShellOption(defaultValue = "is_null") String authorId, @ShellOption(defaultValue = "is_null") String genreId) {
        Book book = Book.builder()
                .name(bookName)
                .description(bookDesc)
                .author(authorId.equals("is_null") ? null : authorRepository.findById(authorId).orElse(null))
                .genre(genreId.equals("is_null") ? null : genreRepository.findById(genreId).orElse(null))
                .build();
        return bookRepository.save(book).getId();
    }

    @ShellMethod(value = "update book", key = {"book update", "bo upd"})
    public void updateBook(String bookId, String authorId, String genreId) {
        Book bookForUpdate = bookRepository.findById(bookId).orElse(null);
        bookForUpdate.setAuthor(authorRepository.findById(authorId).orElse(null));
        bookForUpdate.setGenre(genreRepository.findById(genreId).orElse(null));
        bookRepository.save(bookForUpdate);
    }

    @ShellMethod(value = "delete book", key = {"book delete", "bo del"})
    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }

    @ShellMethod(value = "find book", key = {"book find", "bo find"})
    public Book getBookById(String id) {
        return bookRepository.findById(id).orElse(null);
    }

    //comment commands
    @ShellMethod(value = "add comment", key = {"comment add", "co add"})
    public String addNewComment(String bookId, String commentText) {
        Comment comment = Comment.builder()
                .book(bookRepository.findById(bookId).orElse(null))
                .commentText(commentText)
                .build();
        return commentRepository.save(comment).getId();
    }

    @ShellMethod(value = "get all comment by bookId", key = {"comment get", "co get"})
    public List<Comment>getAllCommentsByBookId(String bookId) {
        return commentRepository.findAllByBookId(bookId);
    }
}
