package com.pachkhede.wallpaperapp;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {WallpaperEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract WallpaperDao wallpaperDao();

    
}
