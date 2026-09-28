package com.pachkhede.wallpaperapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WallpaperGridFragment extends Fragment {
    private static final String ARG_QUERY = "query";
    private static final String ARG_SORT = "sorting";
    public static final int perPage = 24;

    List<Wallpaper> wallpapers;
    RecyclerView recyclerView;
    private WallpaperAdapter adapter;

    private int currentPage = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private String query;
    private String sorting;


    public WallpaperGridFragment() {

    }


    public static WallpaperGridFragment newInstance(String query, String sorting) {


        WallpaperGridFragment fragment = new WallpaperGridFragment();
        Bundle args = new Bundle();
        args.putString(ARG_QUERY, query);
        args.putString(ARG_SORT, sorting);


        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.query = getArguments().getString(ARG_QUERY);
        this.sorting = getArguments().getString(ARG_SORT);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_wallpaper_grid, container, false);
        recyclerView = view.findViewById(R.id.wallpaper_recycler_view);


        return view;
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        wallpapers = new ArrayList<>();
        adapter = new WallpaperAdapter(wallpapers, wallpaper-> {
            Intent i = new Intent(getContext(), WallpaperPreviewActivity.class);

            i.putExtra("wallpaper_url", wallpaper.getImage_url());
            i.putExtra("wallpaper_id", wallpaper.getId());

            startActivity(i);


        });

        recyclerView.setAdapter(adapter);

        setScrollListener();

        loadWallpapers();

    }

    private void setScrollListener() {

        recyclerView.addOnScrollListener(
                new RecyclerView.OnScrollListener() {

                    @Override
                    public void onScrolled(
                            @NonNull RecyclerView recyclerView,
                            int dx,
                            int dy) {

                        super.onScrolled(
                                recyclerView,
                                dx,
                                dy
                        );

                        GridLayoutManager layoutManager =
                                (GridLayoutManager)
                                        recyclerView.getLayoutManager();

                        if (layoutManager == null) {
                            return;
                        }

                        int totalItems =
                                layoutManager.getItemCount();

                        int lastVisibleItem =
                                layoutManager.findLastVisibleItemPosition();

                        if (lastVisibleItem >= totalItems - 4) {

                            loadWallpapers();
                        }
                    }
                }
        );
    }

    private void loadWallpapers() {
        if (isLoading || isLastPage) {
            return;
        }

        isLoading = true;

        NexWallApi api = RetrofitClient.getApi();

        api.searchWallpaper(
                this.query,
                this.currentPage,
                this.perPage,
                this.sorting
        ).enqueue(new Callback<WallpaperResponse>() {

            @Override
            public void onResponse(
                    Call<WallpaperResponse> call,
                    Response<WallpaperResponse> response) {
                isLoading = false;
                if (response.isSuccessful() && response.body() != null) {
                    List<Wallpaper> newWallpapers =
                            response.body().getData();
                    if (newWallpapers == null
                            || newWallpapers.isEmpty()) {

                        isLastPage = true;
                        return;

                    }
                    adapter.addWallpapers(newWallpapers);

                    currentPage++;

                    Toast.makeText(getContext(), "Load Complete", Toast.LENGTH_SHORT).show();

                } else {
                    Log.e("WALLHAVEN",
                            "Response error: " + response.code());
                    Toast.makeText(getContext(), "Response Error: " + response.code(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(
                    Call<WallpaperResponse> call,
                    Throwable t) {

                Log.e("WALLHAVEN",
                        "Network error", t);
                Toast.makeText(getContext(), "Network error", Toast.LENGTH_SHORT).show();

            }
        });
    }

}

