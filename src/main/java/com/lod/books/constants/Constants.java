package com.lod.books.constants;

import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Utility class that holds values for constants used
 */
@Component
public class Constants {
    public static final String DBPEDIA_SPARQL_URL = "https://dbpedia.org/sparql";
    public static final String DBPEDIA_SPARQL_URL_GRAPH = "?default-graph-uri=http%3A%2F%2Fdbpedia.org&query=";
    public static final String DBPEDIA_SPARQL_URL_END = "&format=application%2Fsparql-results%2Bjson&timeout=30000&signal_void=on&signal_unconnected=on";

    // books
    public static final String getBookDetailsURL(String searchBookTitle) {
        String search = "dbr%3A" + encodeValue(replaceSymbol(searchBookTitle));
        return DBPEDIA_SPARQL_URL + DBPEDIA_SPARQL_URL_GRAPH +
                "select+distinct+%3Fab+%3Fa+%3FaL+%3Fl+%3Fn+%3FlG+%3Fg+%3Fp+%3FnP+%3Fpr+%3Fpd+%3Ft+%3Flg+where+%7B%0D%0A" +
                search + "+dbo%3Aabstract+%3Fab.%0D%0AOPTIONAL%7B" +
                search + "+dbo%3Aauthor+%3FaL.+%3FaL+dbp%3Aname+%3Fa%7D%0D%0AOPTIONAL%7B+" +
                search + "+rdfs%3Alabel+%3Fl%7D%0D%0AOPTIONAL%7B+" +
                search + "+foaf%3Aname+%3Fn%7D%0D%0AOPTIONAL%7B+" +
                search + "+dbo%3AliteraryGenre+%3FlG%7D%0D%0AOPTIONAL%7B+" +
                search + "+dbp%3Agenre+%3Fg%7D%0D%0AOPTIONAL%7B+" +
                search + "+dbo%3AnumberOfPages+%3FnP%7D%0D%0AOPTIONAL%7B+" +
                search + "+dbp%3Apages+%3Fp%7D%0D%0AOPTIONAL%7B+" +
                search + "+dbo%3Apublisher+%3Fpe.%3Fpe+dbp%3Aname+%3Fpr%7D%0D%0AOPTIONAL%7B+" +
                search + "+dbp%3Apublished+%3Fpd%7D%0D%0AOPTIONAL%7B+" +
                search + "+dbo%3Athumbnail+%3Ft%7D%0D%0AOPTIONAL%7B+" +
                search + "+dbp%3Alanguage+%3Flg%7D%0D%0AFILTER%28lang%28%3Fab%29%3D%22en%22%29%7DLIMIT+10+" +
                DBPEDIA_SPARQL_URL_END;
    }

    public static final String getBooksURL(Integer limit) {
        return DBPEDIA_SPARQL_URL + DBPEDIA_SPARQL_URL_GRAPH +
                "select+distinct+%3Fbook+%3Flabel+%3Fauthor+%3FnumberOfPages%3Fpages+where+%7B%3Fbook+rdf%3Atype+dbo%3ABook%3Brdfs%3Alabel+%3Flabel.%0D%0A"
                + "OPTIONAL+%7B+%3Fbook+dbo%3Aauthor+%3FauthorLink.+%3FauthorLink+dbp%3Aname+%3Fauthor+%7D%0D%0A"
                + "OPTIONAL+%7B+%3Fbook+dbo%3AnumberOfPages+%3FnumberOfPages%7D%0D%0A."
                + "OPTIONAL+%7B+%3Fbook+dbp%3Apages+%3Fpages%7D.%0D%0A"
                + "FILTER+%28lang%28%3Flabel%29+%3D+%22en%22%29.%7D+LIMIT+"
                + limit.toString()
                + DBPEDIA_SPARQL_URL_END;
    }

    public static final String getBookSearchURL(String searchBookTitle, Integer limit) {
        String search = encodeValue(searchBookTitle.toLowerCase());
        return DBPEDIA_SPARQL_URL + DBPEDIA_SPARQL_URL_GRAPH +
                "select+distinct+%3Fbook+%3Flabel+%3Fauthor+%3FnumberOfPages%3Fpages+where+%7B%3Fbook+rdf%3Atype+dbo%3ABook%3Brdfs%3Alabel+%3Flabel.%0D%0A"
                + "OPTIONAL+%7B+%3Fbook+dbo%3Aauthor+%3FauthorLink.+%3FauthorLink+dbp%3Aname+%3Fauthor+%7D%0D%0A"
                + "OPTIONAL+%7B+%3Fbook+dbo%3AnumberOfPages+%3FnumberOfPages%7D%0D%0A."
                + "OPTIONAL+%7B+%3Fbook+dbp%3Apages+%3Fpages%7D.%0D%0A"
                + "FILTER+regex%28lcase%28str%28%3Flabel%29%29%2C+%22.*"
                + search
                + "%22%29.FILTER+%28lang%28%3Flabel%29+%3D+%22en%22%29.%7D+LIMIT+"
                + limit.toString()
                + DBPEDIA_SPARQL_URL_END;
    }

    // authors
    public static final String getAuthorDetailsURL(String searchAuthorName) {
        String search = "dbr:" + replaceSymbol(searchAuthorName);
        return DBPEDIA_SPARQL_URL + DBPEDIA_SPARQL_URL_GRAPH +
                encodeValue("select ?l ?ab ?bd ?bp ?dd ?dp ?t ?n ?o ?ay (count(?w) as ?nw) ?wl where {"
                        + "?w dbo:author " + search + ". "
                        + search + " rdfs:label ?l. "
                        + "OPTIONAL { ?w rdfs:label ?wl}"
                        + "OPTIONAL { " + search + " dbo:abstract ?ab}"
                        + "OPTIONAL { " + search + " dbo:birthDate ?bd}"
                        + "OPTIONAL { " + search + " dbo:birthPlace ?bPL. ?bPL rdfs:label ?bp}"
                        + "OPTIONAL { " + search + " dbo:deathDate ?dd}"
                        + "OPTIONAL { " + search + " dbp:nationality ?dPL. ?dPL rdfs:label ?dp}"
                        + "OPTIONAL { " + search + " dbo:thumbnail ?t}"
                        + "OPTIONAL { " + search + " dbp:nationality ?n}"
                        + "OPTIONAL { " + search + " dbp:occupation ?o}"
                        + "OPTIONAL { " + search + " dbo:activeYearsStartYear ?ay}"
                        + "FILTER (lang(?l) = \"en\")."
                        + "FILTER (lang(?ab) = \"en\")."
                        + "FILTER (lang(?bp) = \"en\")."
                        + "FILTER (lang(?dp) = \"en\")."
                        + "FILTER (lang(?o) = \"en\")."
                        + "FILTER (lang(?n) = \"en\")."
                        + "}"
                        + "group by ?l ?ab ?bd ?bp ?dd ?dp ?t ?n ?o ?ay ?wl "
                        + "LIMIT 10") +
                DBPEDIA_SPARQL_URL_END;
    }

    public static final String getAuthorsURL(Integer limit) {
        return DBPEDIA_SPARQL_URL + DBPEDIA_SPARQL_URL_GRAPH +
                encodeValue("select ?author ?label ?occupation (count(?work) as ?nw) where {" +
                        "?work dbo:author ?author." +
                        "?author rdfs:label ?label." +
                        "OPTIONAL { ?author dbp:occupation ?occupation}" +
                        "FILTER (lang(?label) = \"en\")." +
                        "}" +
                        "group by ?author ?label ?occupation " +
                        "LIMIT " + limit.toString())
                + DBPEDIA_SPARQL_URL_END;
    }

    public static final String getAuthorSearchURL(String searchAuthorName, Integer limit) {
        String search = encodeValue(searchAuthorName.toLowerCase());
        return DBPEDIA_SPARQL_URL + DBPEDIA_SPARQL_URL_GRAPH +
                encodeValue("select ?author ?label ?occupation (count(?work) as ?nw) where {" +
                        "?work dbo:author ?author." +
                        "?author rdfs:label ?label." +
                        "OPTIONAL { ?author dbp:occupation ?occupation}" +
                        "FILTER (lang(?label) = \"en\")." +
                        "FILTER regex(lcase(str(?label)), \".*" + search + "\")." +
                        "}" +
                        "group by ?author ?label ?occupation " +
                        "LIMIT " + limit.toString())
                + DBPEDIA_SPARQL_URL_END;
    }


    //private functions
    private static String encodeValue(String value) {
        String encodedValue = "";
        try {
            encodedValue = URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (Exception ex) {
            ex.getMessage();
        }
        return encodedValue;
    }

    private static String replaceSymbol(String value) {
        value = value.replaceAll(",", "\\\\,");
        value = value.replaceAll("'", "\\\\'");
        value = value.replaceAll("\\(", "\\\\(");
        value = value.replaceAll("\\)", "\\\\)");
        value = value.replaceAll("&", "\\\\&");
        return value;
    }


}

/*
select distinct ?book ?label ?author ?numberOfPages?pages where {?book rdf:type dbo:Book;rdfs:label ?label.
OPTIONAL { ?book dbo:author ?authorLink. ?authorLink dbp:name ?author }
OPTIONAL { ?book dbo:numberOfPages ?numberOfPages}
.OPTIONAL { ?book dbp:pages ?pages}.
FILTER regex(lcase(str(?label)), ".*dreams").FILTER (lang(?label) = "en").} LIMIT 100
*/