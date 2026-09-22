package com.pachkhede.wallpaperapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WallhavenApi {

    @GET("api/v1/search")
    Call<WallpaperResponse> searchWallpaper(
            @Query("q") String query,
            @Query("categories") String categories,
            @Query("purity") String purity,
            @Query("sorting") String sorting,
            @Query("page") int page
    );

}
