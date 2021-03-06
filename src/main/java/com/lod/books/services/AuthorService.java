package com.lod.books.services;

import com.lod.books.constants.Constants;
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
        Author author = this.getAuthorDetailsFromJson(jsonArray, authorDBR);
        String urlWIKI = Constants.getAuthorDetailsURL_WIKI(author.getAuthorWIKI());
        JSONArray jsonArrayWIKI = this.getData(urlWIKI);
        author = this.getAuthorDetailsFromJsonWIKI(jsonArrayWIKI, author);
        return author;
    }

    private List<Author> getListAuthorsFromJson(JSONArray jsonArray) {
        List<Author> authors = jsonArray.stream().map((jsonAuthor) -> {
            String authorDBR = this.getFromJson((JSONObject) jsonAuthor, "author").split("/")[4];
            String label = this.getFromJson((JSONObject) jsonAuthor, "label");
            String[] occupationArray = this.getFromJson((JSONObject) jsonAuthor, "occupation").split("/");
            String occupation = occupationArray[occupationArray.length - 1];
            String nw = this.getFromJson((JSONObject) jsonAuthor, "nw");
            Author author = new Author(authorDBR, label, occupation, nw);
            return author;
        }).collect(Collectors.toList());
        return authors;
    }

    private Author getAuthorDetailsFromJson(JSONArray jsonArray, String authorDBR) {
        JSONObject jsonAuthor = (JSONObject) jsonArray.get(0);
        String name = this.getFromJson(jsonAuthor, "l");
        String[] wikiArray = this.getFromJson(jsonAuthor, "s").split("/");
        String wiki = wikiArray[wikiArray.length - 1];
        String abstractDescription = this.getFromJson(jsonAuthor, "ab");
        String birthDate = this.getFromJson(jsonAuthor, "bd");
        String birthPlace = this.getFromJson(jsonAuthor, "bp");
        String deathDate = this.getFromJson(jsonAuthor, "dd");
        String deathPlace = this.getFromJson(jsonAuthor, "dp");
        String thumbnail = this.getFromJson(jsonAuthor, "t");
        String[] occupationArray = this.getFromJson(jsonAuthor, "o").split("/");
        String occupation = occupationArray[occupationArray.length - 1];
        String nw = this.getFromJson(jsonAuthor, "nw");
        String[] nationalityArray = this.getFromJson(jsonAuthor, "n").split("/");
        String nationality = nationalityArray[nationalityArray.length - 1];
        String activeYearsStartYear = this.getFromJson(jsonAuthor, "ay");

        Author author = new Author(authorDBR, wiki, name, abstractDescription,
                birthDate, birthPlace, deathDate, deathPlace,
                thumbnail, occupation, nationality, activeYearsStartYear, nw);

        List<String> isAuthorOf = jsonArray.stream().map((ja) ->
                this.getFromJson((JSONObject) ja, "wl")
        ).distinct().collect(Collectors.toList());

        author.setIsAuthorOf(isAuthorOf);

        return author;
    }

    private Author getAuthorDetailsFromJsonWIKI(JSONArray jsonArray, Author author) {
        JSONObject jsonAuthor = (JSONObject) jsonArray.get(0);
        String genre = this.getFromJson(jsonAuthor, "g");
        List<String> occupationArray = jsonArray.stream().map((jb) ->
                this.getFromJson((JSONObject) jb, "o")
        ).distinct().collect(Collectors.toList());

        author.setGenre(genre);
        author.setOccupationArray(occupationArray);

        return author;
    }

}