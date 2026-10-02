package com.pachkhede.wallpaperapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WallpaperDao {

    @Insert
    public void insert(WallpaperEntity wallpaper);

    @Query("SELECT * FROM wallpapers ORDER BY RANDOM() LIMIT 1")
    WallpaperEntity getRandomWallpaper();

    @Query("SELECT * FROM wallpapers ORDER BY timestamp DESC")
    List<WallpaperEntity> getAllWallpapers();

    @Query("SELECT * FROM wallpapers WHERE id = :id")
    WallpaperEntity getWallpaper(int id);

    @Query("SELECT * FROM wallpapers WHERE liked = 1 ORDER BY timestamp DESC")
    List<WallpaperEntity> getLikedWallpapers();

    @Query("UPDATE wallpapers SET liked = :liked WHERE id = :id")
    void setLiked(int id, boolean liked);

    @Query("SELECT EXISTS(SELECT 1 FROM wallpapers WHERE id = :id AND liked = 1)")
    boolean isLiked(int id);

}
