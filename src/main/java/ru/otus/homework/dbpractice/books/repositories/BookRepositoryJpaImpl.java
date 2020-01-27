package ru.otus.homework.dbpractice.books.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.dbpractice.authors.repositories.AuthorRepositoryJpa;
import ru.otus.homework.dbpractice.books.domain.Book;
import ru.otus.homework.dbpractice.genres.repositories.GenreRepositoryJpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Transactional
@Repository
public class BookRepositoryJpaImpl implements BookRepositoryJpa {

    @PersistenceContext
    private EntityManager entityManager;

    private final AuthorRepositoryJpa authorRepositoryJpa;

    private final GenreRepositoryJpa genreRepositoryJpa;

    @Autowired
    public BookRepositoryJpaImpl(AuthorRepositoryJpa authorRepositoryJpa, GenreRepositoryJpa genreRepositoryJpa) {
        this.authorRepositoryJpa = authorRepositoryJpa;
        this.genreRepositoryJpa = genreRepositoryJpa;
    }

    @Override
    public long save(Book book) {
        entityManager.persist(book);
        return book.getId();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public List<Book> findAll() {
        return entityManager.createQuery("from Book", Book.class).getResultList();
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Book findById(long id) {
        return entityManager.find(Book.class, id);
    }

    @Override
    public void updateBook(long bookId, long authorId, long genreId) {
        Book existBook = findById(bookId);
        Book updBook = Book.builder()
                .id(bookId)
                .name(existBook.getName())
                .description(existBook.getDescription())
                .author(authorRepositoryJpa.findById(authorId))
                .genre(genreRepositoryJpa.findById(genreId))
                .build();
        entityManager.merge(updBook);
    }

    @Override
    public void deleteBook(long id) {
        Query query = entityManager.createQuery("delete from Book bo where bo.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
