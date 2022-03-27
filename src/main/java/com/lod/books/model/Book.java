package com.lod.books.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor
@Data
public class Book implements Serializable {

    private String bookDBR;
    private String bookWIKI;
    private String label; // label - name
    private String abstractDescription; // abstract
    private String authorDBR;
    private String authorName;
    private List<String> literaryGenre; // literaryGenre - genre
    private String numberOfPages; // numberOfPages - pages
    private String publisher;
    private String published;
    private String language;
    private String basedOn; // wiki
    private String derivativeWork; // wiki
    private List<String> characters; // wiki

    public Book(String bookDBR, String label, String author, String numberOfPages) {
        this.bookDBR = bookDBR;
        this.label = label;
        this.authorName = author;
        this.numberOfPages = numberOfPages;
    }

    public Book(String bookDBR, String bookWIKI, String label, String abstractDescription, String authorDBR, String authorName, String numberOfPages, String publisher, String published, String language) {
        this.bookDBR = bookDBR;
        this.bookWIKI = bookWIKI;
        this.label = label;
        this.abstractDescription = abstractDescription;
        this.authorDBR = authorDBR;
        this.authorName = authorName;
        this.literaryGenre = new LinkedList<>();
        this.numberOfPages = numberOfPages;
        this.publisher = publisher;
        this.published = published;
        this.language = language;
        this.basedOn = "";
        this.derivativeWork = "";
        this.characters = new LinkedList<>();
    }
}

