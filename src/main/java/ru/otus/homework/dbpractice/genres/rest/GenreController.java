package ru.otus.homework.dbpractice.genres.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.homework.dbpractice.genres.dao.GenreDao;
import ru.otus.homework.dbpractice.genres.domain.Genre;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class GenreController {

    private final GenreDao genreDao;

    @Autowired
    public GenreController(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @RequestMapping(value = "genre", method = RequestMethod.GET)
    public ResponseEntity<List<Genre>> getGenres() {
        return ok()
                .header("Content-Type", "application/json; charset=utf-8")
                .body(genreDao.getAllGenres());
    }
}
