package ru.otus.homework.dbpractice.genres.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Component;
import ru.otus.homework.dbpractice.genres.domain.Genre;
import ru.otus.homework.dbpractice.utils.Resources;

import java.util.List;

@Component
public class GenreDaoImpl implements GenreDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Autowired
    public GenreDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    private final RowMapper<Genre> GENRE_ROW_MAPPER = (rs, rowNum) -> Genre.builder()
            .id(rs.getString("id"))
            .name(rs.getString("name"))
            .desc(rs.getString("desc"))
            .build();

    @Override
    public List<Genre> getAllGenres() {
        return namedParameterJdbcOperations.query(Resources.resourceAsString("sql/genres/genre_SELECT_ALL.sql"), GENRE_ROW_MAPPER);
    }
}
