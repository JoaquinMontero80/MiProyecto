package com.jobeanda.miproyecto.ui.newsApi;

import java.util.List;

public class NewsApiResponse {
    private String status;
    private int totalResults;
    private List<com.jobeanda.miproyecto.ui.newsApi.NewsArticle> articles;
    private String code;
    private String message;

    public String getStatus() {
        return status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public List<com.jobeanda.miproyecto.ui.newsApi.NewsArticle> getArticles() {
        return articles;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}