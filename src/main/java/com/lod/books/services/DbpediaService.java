package com.lod.books.services;

import com.lod.books.constants.Constants;
import org.apache.jena.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DbpediaService {
    @Autowired
    protected RestTemplate restTemplateNoLoadBalanced;

    @Autowired
    protected RestTemplate restTemplate;


    public final Object getData(String url) {
        Query query = QueryFactory.create(url);
        QueryExecution qexec = QueryExecutionFactory.sparqlService(Constants.DBPEDIA_SPARQL_URL, query);

        ResultSet results = qexec.execSelect();
        ResultSetFormatter.out(System.out, results, query);
        qexec.close();

        return results;
    }

}