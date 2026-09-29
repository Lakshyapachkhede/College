package com.pachkhede.wallpaperapp;

public class Category {
    private int id;
    private String name;
    private String cover_image_url;
    private int wallpaper_count;

    public int getId() {
        return id;
    }

    public int getWallpaperCount() {
        return wallpaper_count;
    }

    public String getCoverImageUrl() {
        return cover_image_url;
    }

    public String getName() {
        return name;
    }
}
