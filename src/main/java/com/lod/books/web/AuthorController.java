package com.lod.books.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedList;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    @GetMapping
    public String getBookPage(Model model) {
        model.addAttribute("authors", new LinkedList<>());
        model.addAttribute("bodyContent", "authors");
        return "master-template";
    }
}
