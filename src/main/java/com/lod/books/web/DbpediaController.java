package com.lod.books.web;

import com.lod.books.constants.Constants;
import com.lod.books.services.DbpediaService;
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
        String sparqlQueryString = Constants.PREFIXES +
                "select distinct ?book " +
                "where { " +
                "?book rdf:type dbo:Book" +
                "}  LIMIT 100";

        this.dbpediaService.getData(sparqlQueryString);
        return new Object();
    }

    /**
     * Search books by title
     */
    @GetMapping("/search")
    public Object searchBooks(@RequestParam String search) {
        String sparqlQueryString = Constants.PREFIXES +
                "select distinct ?book ?label \n" +
                "where { " +
                "?book rdf:type dbo:Book ; " +
                "rdfs:label ?label. " +
                "FILTER regex(lcase(str(?label)), \".*" + search + "\")." +
                "FILTER (lang(?label) = \"en\")." +
                "} LIMIT 100";
        this.dbpediaService.getData(sparqlQueryString);
        return new Object();
    }

    /**
     * Search books by title
     */
    @GetMapping("/")
    public Object getBook(@RequestParam String book) {
        String sparqlQueryString = Constants.PREFIXES +
                "select distinct ?abstract ?author ?label ?name ?literaryGenre ?genre\n" +
                "?mediaType ?pages ?numberOfPages ?publisher ?published ?thumbnail?language\n" +
                "where {\n" +
                book +
                "dbr:A_Demon_of_Our_Own_Design  dbo:abstract ?abstract.\n" +
                "OPTIONAL { dbr:A_Demon_of_Our_Own_Design dbo:author ?author}\n" +
                "OPTIONAL { dbr:A_Demon_of_Our_Own_Design rdfs:label ?label}\n" +
                "OPTIONAL { dbr:A_Demon_of_Our_Own_Design foaf:name ?name}\n" +
                "OPTIONAL { dbr:A_Demon_of_Our_Own_Design dbo:literaryGenre ?literaryGenre}\n" +
                "OPTIONAL { dbr:A_Demon_of_Our_Own_Design dbp:genre ?genre}\n" +
                "OPTIONAL { dbr:A_Demon_of_Our_Own_Design dbo:mediaType ?mediaType}\n" +
                "OPTIONAL { dbr:A_Demon_of_Our_Own_Design dbo:numberOfPages ?numberOfPages}\n" +
                "OPTIONAL { dbr:A_Demon_of_Our_Own_Design dbp:pages ?pages}\n" +
                "OPTIONAL { dbr:A_Demon_of_Our_Own_Design dbo:publisher ?publisher }\n" +
                "OPTIONAL { dbr:A_Demon_of_Our_Own_Design dbp:published ?published}\n" +
                "OPTIONAL { dbr:A_Demon_of_Our_Own_Design dbo:thumbnail ?thumbnail}\n" +
                "OPTIONAL { dbr:A_Demon_of_Our_Own_Design dbp:language ?language}\n" +
                "}";

        this.dbpediaService.getData(sparqlQueryString);
        return new Object();
    }

}