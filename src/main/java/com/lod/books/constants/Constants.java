package com.lod.books.constants;

import org.springframework.stereotype.Component;

/**
 * Utility class that holds values for constants used
 */
@Component
public class Constants {
    public static final String PREFIXES = "PREFIX xsd:     <http://www.w3.org/2001/XMLSchema#>\n" +
            "PREFIX rdf:     <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +
            "PREFIX rdfs:    <http://www.w3.org/2000/01/rdf-schema#>\n" +
            "PREFIX owl:     <http://www.w3.org/2002/07/owl#>\n" +
            "PREFIX dbo:      <http://dbpedia.org/ontology/>\n";
    public static final String DBPEDIA_SPARQL_URL = "http://dbpedia.org/sparql";
}