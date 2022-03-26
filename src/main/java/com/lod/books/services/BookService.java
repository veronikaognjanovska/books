package com.lod.books.services;

import com.lod.books.model.Book;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService extends DbpediaService<Book> {

    @Override
    public List<Book> getDataList(String url) {
        JSONArray jsonArray = this.getData(url);
        return this.getListBooksFromJson(jsonArray);
    }

    @Override
    public Book getDataDetails(String url, String bookDBR) {
        JSONArray jsonArray = this.getData(url);
        return this.getBookDetailsFromJson(jsonArray, bookDBR);
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
        String numberOfPages = this.getFromJson(jsonBook, "nP");
        String pages = this.getFromJson(jsonBook, "p");
        String publisher = this.getFromJson(jsonBook, "pr");
        String published = this.getFromJson(jsonBook, "pd");
        String thumbnail = this.getFromJson(jsonBook, "t");
        String language = this.getFromJson(jsonBook, "lg");

        Book book = new Book(bookDBR, (!label.isEmpty()) ? label : name, abstractDescription,
                authorDBR, authorName,
                (!numberOfPages.isEmpty()) ? numberOfPages : pages, publisher,
<<<<<<< Updated upstream
                published, thumbnail, language);

        List<String> literaryGenres = jsonArray.stream().map((jb) -> {
            String[] literaryGenreArray = this.getFromJson((JSONObject) jb, "lG").split("/");
            String literaryGenre = literaryGenreArray[literaryGenreArray.length - 1];
            if (!literaryGenre.isEmpty()) return literaryGenre.replaceAll("_", " ");
            String[] genreArray = this.getFromJson((JSONObject) jb, "g").split("/");
            return genreArray[genreArray.length - 1].replaceAll("_", " ");
        }).distinct().collect(Collectors.toList());

        book.setLiteraryGenre(literaryGenres);

        return book;
    }

}