package ru.otus.homework.dbpractice.comments.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.dbpractice.books.repositories.BookRepositoryJpa;
import ru.otus.homework.dbpractice.comments.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Transactional
@Repository
public class CommentRepositoryJpaImpl implements CommentRepositoryJpa {

    @PersistenceContext
    private EntityManager entityManager;

    private final BookRepositoryJpa bookRepositoryJpa;

    @Autowired
    public CommentRepositoryJpaImpl(BookRepositoryJpa bookRepositoryJpa) {
        this.bookRepositoryJpa = bookRepositoryJpa;
    }

    @Override
    public long save(Comment comment) {
        entityManager.persist(comment);
        return comment.getId();
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Comment> findAllByBookId(long bookId) {
        TypedQuery<Comment> query = entityManager.createQuery("from Comment co where co.book = :book_id", Comment.class);
        query.setParameter("book_id", bookRepositoryJpa.findById(bookId));
        return query.getResultList();
    }
}
