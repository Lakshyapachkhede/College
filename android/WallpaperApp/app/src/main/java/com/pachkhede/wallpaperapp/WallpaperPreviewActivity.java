package com.pachkhede.wallpaperapp;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class WallpaperPreviewActivity extends AppCompatActivity {
    ImageView previewImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());

        setContentView(R.layout.activity_wallpaper_preview);


        previewImage = findViewById(R.id.preview_image);

        String wallpaperUrl =
                getIntent().getStringExtra("wallpaper_url");

        Glide.with(this)
                .load(wallpaperUrl)
                .into(previewImage);

    }
}