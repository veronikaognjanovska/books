package com.lod.books.web;

import com.lod.books.services.DbpediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/dbpedia")
public class DbpediaController {

    @Autowired
    protected DbpediaService dbpediaService;

    /**
     * Return first 100 books
     */
//    @GetMapping
//    public List<Book> getBooks() {
//        String url = Constants.getBooksURL(100);
//        List<Book> results = this.dbpediaService.getData(url, true);
//        return results;
//    }
//
//    /**
//     * Search books by title
//     */
//    @GetMapping("/search")
//    public List<Book> searchBooks(@RequestParam String search) {
//        String url = Constants.getBookSearchURL(search, 100);
//        List<Book> results = this.dbpediaService.getData(url, true);
//        return results;
//    }
//
//    /**
//     * Search books by title
//     */
//    @GetMapping("/book")
//    public List<Book> getBook(@RequestParam String book) {
//        String url = Constants.getBookDetailsURL(book);
//        List<Book> results = this.dbpediaService.getData(url, false);
//        return results;
//    }

}