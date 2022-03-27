package com.lod.books.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor
@Data
public class Author {
    private String authorDBR;
    private String authorWIKI;
    private String name; // label - name
    private String abstractDescription; // abstract
    private String birthDate;
    private String birthPlace;
    private String deathDate;
    private String deathPlace;
    private String thumbnail;
    private String nationality;
    private String occupation;
    private List<String> occupationArray; // wiki
    private String activeYearsStartYear;
    private String numberOfWorks;
    private String genre; // wiki
    private List<String> isAuthorOf;

    public Author(String authorDBR, String label, String occupation, String nw) {
        this.authorDBR = authorDBR;
        this.name = label;
        this.occupation = occupation;
        this.numberOfWorks = nw;
    }

    public Author(String authorDBR, String authorWIKI, String name, String abstractDescription,
                  String birthDate, String birthPlace, String deathDate, String deathPlace,
                  String thumbnail, String occupation, String nationality, String activeYearsStartYear,
                  String numberOfWorks) {
        this.authorDBR = authorDBR;
        this.authorWIKI = authorWIKI;
        this.name = name;
        this.abstractDescription = abstractDescription;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
        this.deathDate = deathDate;
        this.deathPlace = deathPlace;
        this.thumbnail = thumbnail;
        this.nationality = nationality;
        this.occupation = occupation;
        this.activeYearsStartYear = activeYearsStartYear;
        this.numberOfWorks = numberOfWorks;
        this.isAuthorOf = new LinkedList<>();
        this.genre = "";
        this.occupationArray = new LinkedList<>();
    }

}
