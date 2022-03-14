package com.lod.books.web;

import com.lod.books.constants.Constants;
import com.lod.books.services.DbpediaService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/dbpedia")
public class DbpediaController {

    @Autowired
    protected DbpediaService dbpediaService;

    /**
     * Return first 100 books
     */
    @GetMapping
    public Object getBooks() {
        String url = Constants.getBooksURL(100);
        JSONObject results = this.dbpediaService.getData(url);
        return new Object();
    }

    /**
     * Search books by title
     */
    @GetMapping("/search")
    public Object searchBooks(@RequestParam String search) {
        String url = Constants.getBookSearchURL(search, 100);
        JSONObject results = this.dbpediaService.getData(url);
        return new Object();
    }

    /**
     * Search books by title
     */
    @GetMapping("/book")
    public Object getBook(@RequestParam String book) {
        String url = Constants.getBookDetailsURL(book);
        JSONObject results = this.dbpediaService.getData(url);
        return new Object();
    }

}