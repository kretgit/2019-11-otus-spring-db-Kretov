package ru.otus.homework.dbpractice.authors.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Component;
import ru.otus.homework.dbpractice.authors.domain.Author;
import ru.otus.homework.dbpractice.utils.Resources;

import java.util.List;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Autowired
    public AuthorDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    private final RowMapper<Author> AUTHOR_ROW_MAPPER = (rs, rowNum) -> Author.builder()
            .id(rs.getString("id"))
            .fullName(rs.getString("full_name"))
            .build();

    @Override
    public List<Author> getAllAuthors() {
        return namedParameterJdbcOperations.query(Resources.resourceAsString("sql/authors/author_SELECT_ALL.sql"), AUTHOR_ROW_MAPPER);
    }
}
