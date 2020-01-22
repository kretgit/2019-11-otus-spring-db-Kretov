package ru.otus.homework.dbpractice.books.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.dbpractice.authors.repositories.AuthorJpa;
import ru.otus.homework.dbpractice.books.domain.Book;
import ru.otus.homework.dbpractice.genres.repositories.GenreJpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Transactional
@Repository
public class BookJpaImpl implements BookJpa {

    @PersistenceContext
    private EntityManager entityManager;

    private final AuthorJpa authorJpa;

    private final GenreJpa genreJpa;

    @Autowired
    public BookJpaImpl(AuthorJpa authorJpa, GenreJpa genreJpa) {
        this.authorJpa = authorJpa;
        this.genreJpa = genreJpa;
    }

    @Override
    public long save(Book book) {
        entityManager.persist(book);
        return book.getId();
    }

    @Override
    public List<Book> findAll() {
        return entityManager.createQuery("from Book", Book.class).getResultList();
    }

    @Override
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
                .author(authorJpa.findById(authorId))
                .genre(genreJpa.findById(genreId))
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
