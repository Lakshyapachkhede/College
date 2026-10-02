package com.pachkhede.wallpaperapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class LikedWallpaperAdapter extends RecyclerView.Adapter<WallpaperAdapter.WallpaperHolder> {

    List<WallpaperEntity> wallpapers;
    private OnWallpaperClickListener listener;

    public  interface OnWallpaperClickListener {
        void onWallpaperClick(WallpaperEntity wallpaper);
    }

    public LikedWallpaperAdapter(
            List<WallpaperEntity> wallpapers,
            OnWallpaperClickListener listener) {

        this.wallpapers = wallpapers;
        this.listener = listener;
    }

    @NonNull
    @Override
    public WallpaperAdapter.WallpaperHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wallpaper_recycler_item, parent,false);
        return new WallpaperAdapter.WallpaperHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WallpaperAdapter.WallpaperHolder holder, int position) {
        WallpaperEntity w = wallpapers.get(position);
        Glide.with(holder.itemView.getContext())
                .load(w.getLocal_path())
                .into(holder.image);


        holder.itemView.setOnClickListener(v ->
                listener.onWallpaperClick(w));

    }

    @Override
    public int getItemCount() {
        return wallpapers.size();
    }
}
