package com.lod.books.web;

import com.lod.books.constants.Constants;
import com.lod.books.model.Author;
import com.lod.books.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    protected AuthorService authorService;

    @GetMapping
    public String getAuthors(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        String url = Constants.getAuthorsURL(100);
        List<Author> authors = this.authorService.getDataList(url);
        model.addAttribute("authors", authors);
        model.addAttribute("bodyContent", "authors");
        return "master-template";
    }

    @PostMapping("/search")
    public String searchAuthors(@RequestParam(required = false) String error, Model model, @RequestParam("search") String search) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        String url = Constants.getAuthorSearchURL(search, 100);
        List<Author> authors = this.authorService.getDataList(url);
        model.addAttribute("authors", authors);
        model.addAttribute("bodyContent", "authors");
        return "master-template";
    }

    @GetMapping("/{author}")
    public String getAuthorDetails(@PathVariable String author, @RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        String url = Constants.getAuthorDetailsURL(author);
        Author authorDetails = this.authorService.getDataDetails(url, author);
        model.addAttribute("author", authorDetails);
        model.addAttribute("bodyContent", "detail-author");
        return "master-template";
    }

}
