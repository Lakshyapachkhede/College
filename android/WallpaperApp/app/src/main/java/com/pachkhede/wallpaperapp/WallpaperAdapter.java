package com.pachkhede.wallpaperapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class WallpaperAdapter extends RecyclerView.Adapter<WallpaperAdapter.WallpaperHolder> {

    private List<Wallpaper> wallpapers;

    private OnWallpaperClickListener listener;

    public  interface OnWallpaperClickListener {
        void onWallpaperClick(Wallpaper wallpaper);
    }

    public WallpaperAdapter(List<Wallpaper> wallpapers, OnWallpaperClickListener listener)
    {
        this.wallpapers = wallpapers;
        this.listener = listener;
    }

    @NonNull
    @Override
    public WallpaperHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wallpaper_recycler_item, parent,false);
        return new WallpaperHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull WallpaperHolder holder, int position) {
        Wallpaper w = wallpapers.get(position);
        Glide.with(holder.itemView.getContext())
                .load(w.getThumbnail_url())
                .into(holder.image);


        holder.itemView.setOnClickListener(v ->
                listener.onWallpaperClick(w));


    }

    @Override
    public int getItemCount() {
        return wallpapers.size();
    }

    static class WallpaperHolder extends RecyclerView.ViewHolder{
        ImageView image;
        public WallpaperHolder (View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.wallpaper_item_image);
        }
    }

    public void addWallpapers(List<Wallpaper> newWallpapers)
    {
        int startPosition = wallpapers.size();
        wallpapers.addAll(newWallpapers);

        notifyItemRangeInserted(
                startPosition,
                wallpapers.size()
        );

    }


}
