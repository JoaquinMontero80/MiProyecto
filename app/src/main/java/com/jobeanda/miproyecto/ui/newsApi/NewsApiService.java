package com.jobeanda.miproyecto.ui.newsApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiService {

    @GET("top-headlines")
    Call<NewsApiResponse> getTopHeadlines(
            @Query("country") String country,
            @Query("category") String category,
            @Query("q") String query,
            @Query("pageSize") Integer pageSize,
            @Query("page") Integer page,
            @Query("apiKey") String apiKey
    );

    @GET("everything")
    Call<NewsApiResponse> getEverything(
            @Query("q") String query,
            @Query("searchIn") String searchIn,
            @Query("sources") String sources,
            @Query("domains") String domains,
            @Query("excludeDomains") String excludeDomains,
            @Query("from") String from,
            @Query("to") String to,
            @Query("language") String language,
            @Query("sortBy") String sortBy,
            @Query("pageSize") Integer pageSize,
            @Query("page") Integer page,
            @Query("apiKey") String apiKey
    );
}
