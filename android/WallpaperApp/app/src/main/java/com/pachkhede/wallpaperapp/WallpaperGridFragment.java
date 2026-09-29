package com.pachkhede.wallpaperapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
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
    private static final String ARG_CAT = "category";
    public static final int perPage = 24;

    RecyclerView recyclerView;
    private WallpaperAdapter adapter;
    private WallpaperGridViewModel viewModel;

    private String query;
    private String sorting;
    private Integer category_id;



    public WallpaperGridFragment() {

    }


    public static WallpaperGridFragment newInstance(String query, String sorting, Integer category) {


        WallpaperGridFragment fragment = new WallpaperGridFragment();
        Bundle args = new Bundle();
        args.putString(ARG_QUERY, query);
        args.putString(ARG_SORT, sorting);
        if (category != null) {
            args.putInt(ARG_CAT, category);
        }


        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.query = getArguments().getString(ARG_QUERY);
        this.sorting = getArguments().getString(ARG_SORT);
        category_id = getArguments().containsKey(ARG_CAT)
                ? getArguments().getInt(ARG_CAT)
                : null;

        String key = "grid_" + query + "_" + sorting;
        viewModel = new ViewModelProvider(requireActivity())
                .get(key, WallpaperGridViewModel.class);
        viewModel.init(query, sorting, category_id);

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
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));


        adapter = new WallpaperAdapter(new ArrayList<>(), wallpaper-> {
            Intent i = new Intent(requireContext(), WallpaperPreviewActivity.class);

            i.putExtra("wallpaper_url", wallpaper.getImage_url());
            i.putExtra("wallpaper_id", wallpaper.getId());

            startActivity(i);



        });

        recyclerView.setAdapter(adapter);

        setScrollListener();


        viewModel.getWallpapers().observe(getViewLifecycleOwner(),
                list -> adapter.addWallpapers(list));


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
                            viewModel.loadNextPage();
                        }
                    }
                }
        );
    }

}

