package com.lod.books.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private Long id;
    private String label; // label - name
    private String abstractDescription; // abstract
    //private Author author;
    //private List<String> literaryGenre; // literaryGenre - genre
    private String mediaType;
    private String numberOfPages; // numberOfPages - pages
    private String publisher;
    private String published;
    private String thumbnail;
    private String language;
}
