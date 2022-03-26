package com.lod.books.services;

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

@Service
public abstract class DbpediaService<T> {
    @Autowired
    protected RestTemplate restTemplateNoLoadBalanced;

    @Autowired
    protected RestTemplate restTemplate;

    public abstract List<T> getDataList(String url);

    public abstract T getDataDetails(String url, String bookDBR);

    protected final JSONArray getData(String url) {
        JSONObject json = this.getJsonDataFromEndpoint(url);
        JSONArray jsonArray = this.getResultsRows(json);
        return jsonArray;
    }

    protected String getFromJson(JSONObject json, String value) {
        JSONObject object = (JSONObject) json.get(value);
        if (object != null) return (String) object.get("value");
        return "";
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

}