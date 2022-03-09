package com.lod.books.model;

import java.util.List;

public class Book {
    private Long id;
    private String label; // label - name
    private String abstractDescription; // abstract
    private Author author;
    private List<String> literaryGenre; // literaryGenre - genre
    private String mediaType;
    private String numberOfPages; // numberOfPages - pages
    private String publisher;
    private String published;
    private String thumbnail;
    private String language;
}
