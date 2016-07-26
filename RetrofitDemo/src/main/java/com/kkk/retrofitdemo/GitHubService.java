package com.kkk.retrofitdemo;

import com.kkk.retrofitdemo.bean.Repo;
import com.kkk.retrofitdemo.bean.SearchRepoResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface GitHubService {
    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);

    @GET("search/repositories")
    Observable<SearchRepoResult> searchRepos(@Query("q") String keyword,
                                             @Query("sort") String sort,
                                             @Query("order") String order);
}