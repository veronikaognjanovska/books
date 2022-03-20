package com.lod.books.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Author {
    @Id
    @GeneratedValue
    private Long id;
    private String name; // label - name
    private String abstractDescription; // abstract
    private String birthDate;
    private String birthPlace;
    private String deathDate;
    private String deathPlace;
    private String thumbnail;
    private String nationality;
    private String occupation;
    private String activeYearsStartYear;
//    private String notablework;
//    private String isAuthorOf;
}
