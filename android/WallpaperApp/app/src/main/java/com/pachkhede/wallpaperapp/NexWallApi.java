package com.pachkhede.wallpaperapp;

import android.content.Intent;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NexWallApi {

    @GET("api/developer/v1/wallpapers")
    Call<WallpaperResponse> searchWallpaper(
            @Query("search") String query,
            @Query("category_id") Integer category_id,
            @Query("page") int page,
            @Query("per_page") int perPage,
            @Query("sort") String sort

    );

    @GET("api/developer/v1/categories")
    Call<CategoryResponse> getCategories();



}
