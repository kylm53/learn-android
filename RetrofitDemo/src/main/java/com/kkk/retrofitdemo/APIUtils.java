package com.kkk.retrofitdemo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by CTC on 2016/7/25.
 */
public class APIUtils {
    public static final String BASE_URL = "https://api.github.com/";
    static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static GitHubService service = retrofit.create(GitHubService.class);
}
