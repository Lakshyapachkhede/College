package com.pachkhede.wallpaperapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "wallpapers")
public class WallpaperEntity {

    @PrimaryKey
    private int id;

    private String local_path;

    private long timestamp;
    private boolean liked;

    public WallpaperEntity(int id, String local_path, long timestamp, boolean liked) {
        this.id = id;
        this.local_path = local_path;
        this.timestamp = timestamp;
        this.liked = liked;
    }

    public int getId() {
        return id;
    }

    public String getLocal_path() {
        return local_path;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public boolean getLiked()
    {
        return liked;
    }
}
