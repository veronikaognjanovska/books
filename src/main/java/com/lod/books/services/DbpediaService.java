package com.lod.books.services;

import com.lod.books.model.Book;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DbpediaService {
    @Autowired
    protected RestTemplate restTemplateNoLoadBalanced;

    @Autowired
    protected RestTemplate restTemplate;

    public final List<Book> getDataList(String url) {
        JSONArray jsonArray = this.getData(url);
        return this.getListBooksFromJson(jsonArray);
    }

    public final Book getDataDetails(String url, String bookDBR) {
        JSONArray jsonArray = this.getData(url);
        return this.getBookDetailsFromJson(jsonArray, bookDBR);
    }

    private final JSONArray getData(String url) {
        JSONObject json = this.getJsonDataFromEndpoint(url);
        JSONArray jsonArray = this.getResultsRows(json);
        return jsonArray;
    }

    private final JSONObject getJsonDataFromEndpoint(String url) {
        JSONObject json = null;
        try {
            HttpClient client = HttpClients.custom().build();
            HttpUriRequest request = RequestBuilder.get()
                    .setUri(url)
                    .setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                    .build();
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            JSONParser parser = new JSONParser();
            json = (JSONObject) parser.parse(responseString);
            System.out.println(json.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return json;
    }

    private JSONArray getResultsRows(JSONObject json) {
        JSONObject results = (JSONObject) json.get("results");
        JSONArray bindings = (JSONArray) results.get("bindings");
        return bindings;
    }

    private List<Book> getListBooksFromJson(JSONArray jsonArray) {
        List<Book> books = jsonArray.stream().map((jsonBook) -> {
            String bookDBR = this.getFromJson((JSONObject) jsonBook, "book").split("/")[4];
            String label = this.getFromJson((JSONObject) jsonBook, "label");
            String name = this.getFromJson((JSONObject) jsonBook, "name");
            String author = this.getFromJson((JSONObject) jsonBook, "author");
            String numberOfPages = this.getFromJson((JSONObject) jsonBook, "numberOfPages");
            String pages = this.getFromJson((JSONObject) jsonBook, "pages");
            Book book = new Book(bookDBR, (!label.isEmpty()) ? label : name, author, (!numberOfPages.isEmpty()) ? numberOfPages : pages);
            return book;
        }).collect(Collectors.toList());
        return books;
    }

    private Book getBookDetailsFromJson(JSONArray jsonArray, String bookDBR) {
        JSONObject jsonBook = (JSONObject) jsonArray.get(0);
        String label = this.getFromJson(jsonBook, "l");
        String name = this.getFromJson(jsonBook, "n");
        String abstractDescription = this.getFromJson(jsonBook, "ab");
        String authorName = this.getFromJson(jsonBook, "a");
        String authorDBR = this.getFromJson(jsonBook, "aL");
        if (authorDBR != null && !authorDBR.isEmpty()) authorDBR = authorDBR.split("/")[4];
        String literaryGenre = this.getFromJson(jsonBook, "lG");
        String genre = this.getFromJson(jsonBook, "g");
        String numberOfPages = this.getFromJson(jsonBook, "nP");
        String pages = this.getFromJson(jsonBook, "p");
        String publisher = this.getFromJson(jsonBook, "pr");
        String published = this.getFromJson(jsonBook, "pd");
        String thumbnail = this.getFromJson(jsonBook, "t");
        String language = this.getFromJson(jsonBook, "lg");

        Book book = new Book(bookDBR, (!label.isEmpty()) ? label : name, abstractDescription,
                authorDBR, authorName, (!literaryGenre.isEmpty()) ? literaryGenre : genre,
                (!numberOfPages.isEmpty()) ? numberOfPages : pages, publisher,
                published, thumbnail, language);
        return book;
    }

    private String getFromJson(JSONObject json, String value) {
        JSONObject object = (JSONObject) json.get(value);
        if (object != null) return (String) object.get("value");
        return "";
    }

}