package com.lod.books.web;

import com.lod.books.constants.Constants;
import com.lod.books.model.Book;
import com.lod.books.services.DbpediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    protected DbpediaService dbpediaService;

    @GetMapping
    public String getBooks(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        String url = Constants.getBooksURL(100);
        List<Book> books = this.dbpediaService.getDataList(url);
        model.addAttribute("books", books);
        model.addAttribute("bodyContent", "books");
        return "master-template";
    }

    @PostMapping("/search")
    public String searchBooks(@RequestParam(required = false) String error, Model model, @RequestParam("search") String search) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        String url = Constants.getBookSearchURL(search, 100);
        List<Book> books = this.dbpediaService.getDataList(url);
        model.addAttribute("books", books);
        model.addAttribute("bodyContent", "books");
        return "master-template";
    }

    @GetMapping("/{book}")
    public String getBookDetails(@PathVariable String book, @RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        String url = Constants.getBookDetailsURL(book);
        Book bookDetails = this.dbpediaService.getDataDetails(url, book);
        model.addAttribute("book", bookDetails);
        model.addAttribute("bodyContent", "detail-book");
        return "master-template";
    }

}
