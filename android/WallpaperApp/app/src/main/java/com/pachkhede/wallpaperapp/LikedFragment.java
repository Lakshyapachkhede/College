package com.pachkhede.wallpaperapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class LikedFragment extends Fragment {

    RecyclerView recyclerView;

    LikedWallpaperAdapter adapter;

    List<WallpaperEntity> wallpapers = new ArrayList<>();

    AppDatabase db;
    WallpaperDao wallpaperDao;


    public LikedFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_liked, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recyclerView = view.findViewById(
                R.id.liked_recycler_view
        );

        recyclerView.setLayoutManager(
                new GridLayoutManager(requireContext(), 2)
        );

        db = Room.databaseBuilder(
                requireContext().getApplicationContext(),
                AppDatabase.class,
                "wallpaper_database"
        ).build();

        wallpaperDao = db.wallpaperDao();

        adapter = new LikedWallpaperAdapter(
                wallpapers,
                wallpaper -> {
                    Intent i = new Intent(requireContext(), WallpaperPreviewActivity.class);

                    i.putExtra("wallpaper_url", wallpaper.getLocal_path());
                    i.putExtra("wallpaper_id", wallpaper.getId());
                    i.putExtra("is_path", true);

                    startActivity(i);

                }
        );

        recyclerView.setAdapter(adapter);

        loadLikedWallpapers();
    }

    private void loadLikedWallpapers() {

        new Thread(() -> {

            List<WallpaperEntity> likedWallpapers =
                    wallpaperDao.getLikedWallpapers();

            requireActivity().runOnUiThread(() -> {

                wallpapers.clear();
                wallpapers.addAll(likedWallpapers);

                adapter.notifyDataSetChanged();

            });

        }).start();
    }


    @Override
    public void onResume() {
        super.onResume();

        loadLikedWallpapers();
    }


}