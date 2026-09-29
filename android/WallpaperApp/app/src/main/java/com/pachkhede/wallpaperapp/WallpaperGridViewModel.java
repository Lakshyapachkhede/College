package com.pachkhede.wallpaperapp;

import androidx.lifecycle.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WallpaperGridViewModel extends ViewModel {

    private final MutableLiveData<List<Wallpaper>> wallpapers =
            new MutableLiveData<>(new ArrayList<>());


    private final List<Wallpaper> allWallpapers = new ArrayList<>();

    private String query;
    private String sorting;
    private Integer category_id;

    private int currentPage = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private boolean initialized = false;

    private Call<WallpaperResponse> currentCall;

    public LiveData<List<Wallpaper>> getWallpapers() {
        return wallpapers;
    }


    public void init(String query, String sorting, Integer category_id) {
        if (initialized) return;
        initialized = true;
        this.query = query;
        this.sorting = sorting;
        this.category_id = category_id;
        loadNextPage();
    }

    public void loadNextPage() {
        if (isLoading || isLastPage) return;
        isLoading = true;

        NexWallApi api = RetrofitClient.getApi();
        currentCall = api.searchWallpaper(
                query,category_id, currentPage, WallpaperGridFragment.perPage, sorting);

        currentCall.enqueue(new Callback<WallpaperResponse>() {
            @Override
            public void onResponse(Call<WallpaperResponse> call,
                                   Response<WallpaperResponse> response) {
                isLoading = false;

                if (response.isSuccessful() && response.body() != null) {
                    List<Wallpaper> newItems = response.body().getData();

                    if (newItems == null || newItems.isEmpty()) {
                        isLastPage = true;
                        return;
                    }

                    allWallpapers.addAll(newItems);
                    currentPage++;

                    wallpapers.setValue(new ArrayList<>(allWallpapers));
                } else {

                }
            }

            @Override
            public void onFailure(Call<WallpaperResponse> call, Throwable t) {
                isLoading = false;
                if (!call.isCanceled()) {

                }
            }
        });
    }

    @Override
    protected void onCleared() {
        if (currentCall != null) currentCall.cancel();
    }
}
