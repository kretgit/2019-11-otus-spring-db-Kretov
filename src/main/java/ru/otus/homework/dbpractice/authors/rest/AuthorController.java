package ru.otus.homework.dbpractice.authors.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.homework.dbpractice.authors.dao.AuthorDao;
import ru.otus.homework.dbpractice.authors.domain.Author;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class AuthorController {

    private final AuthorDao authorDao;

    @Autowired
    public AuthorController(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @RequestMapping(value = "author", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<List<Author>> getAuthors() {
        return ok()
                .header("Content-Type", "application/json; charset=utf-8")
                .body(authorDao.getAllAuthors());
    }

}
