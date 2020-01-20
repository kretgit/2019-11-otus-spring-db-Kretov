package ru.otus.homework.dbpractice.books.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Component;
import ru.otus.homework.dbpractice.books.domain.Book;
import ru.otus.homework.dbpractice.utils.IdGenerator;
import ru.otus.homework.dbpractice.utils.Resources;

import java.util.List;

@Component
public class BookDaoImpl implements BookDao {


    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final JdbcOperations jdbcOperations;

    @Autowired
    public BookDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations, JdbcOperations jdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.jdbcOperations = jdbcOperations;
    }

    private final RowMapper<Book> BOOK_ROW_MAPPER = (rs, rowNum) -> Book.builder()
            .id(rs.getString("id"))
            .name(rs.getString("name"))
            .desc(rs.getString("desc"))
            .genreId(rs.getString("genre_id"))
            .authorId(rs.getString("author_id"))
            .build();

    @Override
    public List<Book> getAllBooks() {
        return namedParameterJdbcOperations.query(Resources.resourceAsString("sql/books/book_SELECT_ALL.sql"), BOOK_ROW_MAPPER);
    }

    @Override
    public String createBook(Book book) {
        String bookId = IdGenerator.getNextId(jdbcOperations, IdGenerator.SequenceType.BOOK);
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id",bookId);
        param.addValue("name", book.getName());
        param.addValue("desc", book.getDesc());
        param.addValue("author_id", book.getAuthorId());
        param.addValue("genre_id", book.getGenreId());
        namedParameterJdbcOperations.update(Resources.resourceAsString("sql/books/book_INSERT.sql"), param);
        return bookId;
    }

    @Override
    public void updateBook(String bookId, String authorId, String genreId) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("book_id", bookId);
        param.addValue("author_id", authorId);
        param.addValue("genre_id", genreId);
        namedParameterJdbcOperations.update(Resources.resourceAsString("sql/books/book_UPDATE.sql"), param);
    }

    @Override
    public void deleteBook(String bookId) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("book_id", bookId);
        namedParameterJdbcOperations.update(Resources.resourceAsString("sql/books/book_DELETE.sql"), param);
    }
}
