package com.example.demoretrofit.interfaces;

import com.example.demoretrofit.models.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JPHapi {

    @GET("posts")
    Call<List<Posts>> getPosts();
}
