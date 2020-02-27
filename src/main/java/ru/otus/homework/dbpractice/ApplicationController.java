package ru.otus.homework.dbpractice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework.dbpractice.authors.domain.Author;
import ru.otus.homework.dbpractice.authors.repositories.AuthorRepository;
import ru.otus.homework.dbpractice.books.domain.Book;
import ru.otus.homework.dbpractice.books.repositories.BookRepository;
import ru.otus.homework.dbpractice.comments.repositories.CommentRepository;
import ru.otus.homework.dbpractice.genres.domain.Genre;
import ru.otus.homework.dbpractice.genres.repositories.GenreRepository;

@RestController
public class ApplicationController {

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    private final BookRepository bookRepository;

    private final CommentRepository commentRepository;

    @Autowired
    public ApplicationController(AuthorRepository authorRepository, GenreRepository genreRepository, BookRepository bookRepository, CommentRepository commentRepository) {
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
    }

    @RequestMapping(value = "author", method = {RequestMethod.GET, RequestMethod.POST})
    public Flux<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @RequestMapping(value = "author/add", method = RequestMethod.POST)
    public Mono<Author> addAuthor(@RequestBody Author authorRq) {
        Author author = Author.builder()
                .name(authorRq.getName())
                .build();
        return authorRepository.save(author);
    }

    @RequestMapping(value = "genre", method = {RequestMethod.GET, RequestMethod.POST})
    public Flux<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @RequestMapping(value = "genre/add", method = RequestMethod.POST)
    public Mono<Genre> addGenre(@RequestBody Genre genreRq) {
        Genre genre = Genre.builder()
                .name(genreRq.getName())
                .description(genreRq.getDescription())
                .build();
        return genreRepository.save(genre);
    }

    @RequestMapping(value = "book", method = {RequestMethod.GET, RequestMethod.POST})
    public Flux<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @RequestMapping(value = "book/add", method = RequestMethod.POST)
    public Mono<Book> addBook(@RequestBody Book bookRq) {
        Book book = Book.builder()
                .name(bookRq.getName())
                .description(bookRq.getDescription())
                .authorId(StringUtils.isEmpty(bookRq.getAuthorId()) ? null : bookRq.getAuthorId())
                .genreId(StringUtils.isEmpty(bookRq.getGenreId()) ? null : bookRq.getGenreId())
                .build();
        return bookRepository.save(book);
    }

    @RequestMapping(value = "book/update", method = RequestMethod.POST)
    public Mono<Book> updateBook(@RequestBody Book bookRq) {
        Book newBook = Book.builder()
                .id(bookRq.getId())
                .name(bookRq.getName())
                .description(bookRq.getDescription())
                .authorId(bookRq.getAuthorId())
                .genreId(bookRq.getGenreId())
                .build();
        return bookRepository.save(newBook);
    }

    @RequestMapping(value = "book/delete", method = RequestMethod.POST)
    public Mono<Void> find(@RequestBody Book bookRq) {
        return bookRepository.deleteById(bookRq.getId());
    }

}
