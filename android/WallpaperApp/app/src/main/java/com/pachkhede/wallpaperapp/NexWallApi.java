package com.pachkhede.wallpaperapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NexWallApi {

    @GET("api/developer/v1/wallpapers")
    Call<WallpaperResponse> searchWallpaper(
            @Query("search") String query,
            @Query("page") int page,
            @Query("per_page") int perPage,
            @Query("sort") String sort

    );

}
