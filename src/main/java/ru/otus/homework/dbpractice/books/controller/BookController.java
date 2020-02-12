package ru.otus.homework.dbpractice.books.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.otus.homework.dbpractice.books.dao.BookDao;
import ru.otus.homework.dbpractice.books.domain.Book;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Controller
public class BookController {

    private final BookDao bookDao;

    @Autowired
    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @RequestMapping(value = "book", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView getBook() {
        ModelAndView model = new ModelAndView("list");
        model.addObject("books", bookDao.getAllBooks());
        return model;
    }

    @RequestMapping(value = "book/delete", method = RequestMethod.GET)
    public ModelAndView deleteBook(@RequestParam(value = "id") String id) {
        bookDao.deleteBook(id);
        ModelAndView model = new ModelAndView("list");
        model.addObject("books", bookDao.getAllBooks());
        return model;
    }

    @RequestMapping(value = "book/add", method = RequestMethod.POST)
    public String createBook(@RequestBody Book book, Model model) {
        bookDao.createBook(book);
        model.addAttribute("books", bookDao.getAllBooks());
        return "list";
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
    public String updateBook(@RequestParam("id") String id, Model model) {
        Book book = bookDao.getBookById(id);
        model.addAttribute("book", book);
        //bookDao.updateBook(rq.id, rq.authorId, rq.genreId);
        return "edit";
    }

}
