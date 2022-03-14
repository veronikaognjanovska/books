package com.lod.books.constants;

import org.springframework.stereotype.Component;

/**
 * Utility class that holds values for constants used
 */
@Component
public class Constants {
    public static final String DBPEDIA_SPARQL_URL = "https://dbpedia.org/sparql";
    public static final String DBPEDIA_SPARQL_URL_GRAPH = "?default-graph-uri=http%3A%2F%2Fdbpedia.org&query=";
    public static final String DBPEDIA_SPARQL_URL_END = "&format=application%2Fsparql-results%2Bjson&timeout=30000&signal_void=on&signal_unconnected=on";

    public static final String getBookDetailsURL(String searchBookTitle) {
        String searchText = searchBookTitle;
        return DBPEDIA_SPARQL_URL + DBPEDIA_SPARQL_URL_GRAPH +
                "select+distinct+%3Fabstract+%3Fauthor+%3Flabel+%3Fname+%3FliteraryGenre+%3Fgenre+%3FmediaType+%3Fpages+%3FnumberOfPages+%3Fpublisher+%3Fpublished+%3Fthumbnail+%3Flanguage+where+%7B"
                + searchText + "+dbo%3Aabstract+%3Fabstract.OPTIONAL+%7B+"
                + searchText + "+dbo%3Aauthor+%3Fauthor%7D.OPTIONAL+%7B+"
                + searchText + "+rdfs%3Alabel+%3Flabel%7D.OPTIONAL+%7B+"
                + searchText + "+foaf%3Aname+%3Fname%7D.OPTIONAL+%7B+"
                + searchText + "+dbo%3AliteraryGenre+%3FliteraryGenre%7D.OPTIONAL+%7B+"
                + searchText + "+dbp%3Agenre+%3Fgenre%7D.OPTIONAL+%7B+"
                + searchText + "+dbo%3AmediaType+%3FmediaType%7D.OPTIONAL+%7B+"
                + searchText + "+dbo%3AnumberOfPages+%3FnumberOfPages%7D.OPTIONAL+%7B+"
                + searchText + "+dbp%3Apages+%3Fpages%7D.OPTIONAL+%7B+"
                + searchText + "+dbo%3Apublisher+%3Fpublisher+%7D.+OPTIONAL+%7B+"
                + searchText + "+dbp%3Apublished+%3Fpublished%7D.OPTIONAL+%7B+"
                + searchText + "+dbo%3Athumbnail+%3Fthumbnail%7D.OPTIONAL+%7B+dbr%3A"
                + searchText + "+dbp%3Alanguage+%3Flanguage%7D.%7D+LIMIT+1"
                + DBPEDIA_SPARQL_URL_END;
    }

    public static final String getBooksURL(Integer limit) {
        return DBPEDIA_SPARQL_URL + DBPEDIA_SPARQL_URL_GRAPH +
                "select+distinct+%3Fbook+where+%7B%3Fbook+rdf%3Atype+dbo%3ABook%7D+LIMIT+"
                + limit.toString()
                + DBPEDIA_SPARQL_URL_END;
    }

    public static final String getBookSearchURL(String searchBookTitle, Integer limit) {
        return DBPEDIA_SPARQL_URL + DBPEDIA_SPARQL_URL_GRAPH +
                "select+distinct+%3Fbook+where+%7B%3Fbook+rdf%3Atype+dbo%3ABook%3Brdfs%3Alabel+%3Flabel.FILTER+regex%28lcase%28str%28%3Flabel%29%29%2C+%22.*"
                + searchBookTitle.replace(" ", "+")
                + "%22%29.FILTER+%28lang%28%3Flabel%29+%3D+%22en%22%29.%7D+LIMIT+"
                + limit.toString()
                + DBPEDIA_SPARQL_URL_END;
    }
}