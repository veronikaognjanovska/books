package com.lod.books.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Data
public class Book implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String bookDBR;
    private String label; // label - name
    private String abstractDescription; // abstract
    private String authorDBR;
    private String authorName;
    private String literaryGenre; // literaryGenre - genre
    private String mediaType;
    private String numberOfPages; // numberOfPages - pages
    private String publisher;
    private String published;
    private String thumbnail;
    private String language;

    public Book(String bookDBR, String label, String author, String numberOfPages) {
        this.bookDBR = bookDBR;
        this.label = label;
        this.authorName = author;
        this.numberOfPages = numberOfPages;
    }

    public Book(String bookDBR, String label, String abstractDescription, String authorDBR, String authorName, String literaryGenre, String mediaType, String numberOfPages, String publisher, String published, String thumbnail, String language) {
        this.bookDBR = bookDBR;
        this.label = label;
        this.abstractDescription = abstractDescription;
        this.authorDBR = authorDBR;
        this.authorName = authorName;
        this.literaryGenre = literaryGenre;
        this.mediaType = mediaType;
        this.numberOfPages = numberOfPages;
        this.publisher = publisher;
        this.published = published;
        this.thumbnail = thumbnail;
        this.language = language;
    }
}

