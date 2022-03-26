package com.lod.books.services;

import com.lod.books.model.Author;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService extends DbpediaService<Author> {

    @Override
    public List<Author> getDataList(String url) {
        JSONArray jsonArray = this.getData(url);
        return this.getListAuthorsFromJson(jsonArray);
    }

    @Override
    public Author getDataDetails(String url, String authorDBR) {
        JSONArray jsonArray = this.getData(url);
        return this.getAuthorDetailsFromJson(jsonArray, authorDBR);
    }

    private List<Author> getListAuthorsFromJson(JSONArray jsonArray) {
        List<Author> authors = jsonArray.stream().map((jsonAuthor) -> {
            Author author = new Author();
            return author;
        }).collect(Collectors.toList());
        return authors;
    }

    private Author getAuthorDetailsFromJson(JSONArray jsonArray, String authorDBR) {
        JSONObject jsonAuthor = (JSONObject) jsonArray.get(0);

        Author author = new Author();
        return author;
    }

}