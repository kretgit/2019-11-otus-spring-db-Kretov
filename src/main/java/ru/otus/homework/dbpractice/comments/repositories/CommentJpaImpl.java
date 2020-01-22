package ru.otus.homework.dbpractice.comments.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.dbpractice.books.repositories.BookJpa;
import ru.otus.homework.dbpractice.comments.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Transactional
@Repository
public class CommentJpaImpl implements CommentJpa {

    @PersistenceContext
    private EntityManager entityManager;

    private final BookJpa bookJpa;

    @Autowired
    public CommentJpaImpl(BookJpa bookJpa) {
        this.bookJpa = bookJpa;
    }

    @Override
    public long save(Comment comment) {
        entityManager.persist(comment);
        return comment.getId();
    }

    @Override
    public List<Comment> findAllByBookId(long bookId) {
        TypedQuery<Comment> query = entityManager.createQuery("from Comment co where co.book = :book_id", Comment.class);
        query.setParameter("book_id", bookJpa.findById(bookId));
        return query.getResultList();
    }
}
