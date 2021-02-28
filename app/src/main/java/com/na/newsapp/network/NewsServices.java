package com.na.newsapp.network;

import com.na.newsapp.data.model.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsServices {

    @GET("v2/everything?q=tesla&from=2021-01-28&sortBy=publishedAt&apiKey=2e9d7b7cea7942cab71de1492f3cf323")
    Call<NewsResponse> getAllNews();
}
