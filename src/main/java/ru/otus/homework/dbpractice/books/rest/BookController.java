package ru.otus.homework.dbpractice.books.rest;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.otus.homework.dbpractice.books.dao.BookDao;
import ru.otus.homework.dbpractice.books.domain.Book;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class BookController {

    private final BookDao bookDao;

    @Autowired
    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=utf-8");
        return httpHeaders;
    }

    @RequestMapping(value = "book", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> getBook(@RequestParam(value = "id", required = false) String id) {
        if (StringUtils.isEmpty(id)) {
            return ok()
                    .headers(getHttpHeaders())
                    .body(bookDao.getAllBooks());
        } else {
            return ok()
                    .headers(getHttpHeaders())
                    .body(bookDao.getBookById(id));
        }
    }

    @RequestMapping(value = "book/delete", method = RequestMethod.GET)
    public ResponseEntity deleteBook(@RequestParam(value = "id") String id) {
        bookDao.deleteBook(id);
        return ResponseEntity.ok(id + " deleted");
    }

    @RequestMapping(value = "book/add", method = RequestMethod.POST)
    public ResponseEntity createBook(@RequestBody Book book) {
        return ok(bookDao.createBook(book));
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateBookRq {
        @NonNull
        public String id;
        public String authorId;
        public String genreId;
    }

    @RequestMapping(value = "book/update", method = RequestMethod.POST)
    public ResponseEntity updateBook(@RequestBody UpdateBookRq rq) {
        bookDao.updateBook(rq.id, rq.authorId, rq.genreId);
        return ok(rq.id + " updated");
    }

}
