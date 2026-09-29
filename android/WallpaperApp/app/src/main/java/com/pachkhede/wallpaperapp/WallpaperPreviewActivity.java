package com.pachkhede.wallpaperapp;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

public class WallpaperPreviewActivity extends AppCompatActivity {
    String wallpaperUrl;
    ImageView previewImage;
    ImageButton downloadBtn, likeBtn;
    Button wallBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());

        setContentView(R.layout.activity_wallpaper_preview);


        previewImage = findViewById(R.id.preview_image);
        downloadBtn = findViewById(R.id.download_btn);
        likeBtn = findViewById(R.id.like_btn);
        wallBtn = findViewById(R.id.set_wallpaper_btn);

        wallpaperUrl =
                getIntent().getStringExtra("wallpaper_url");

        Glide.with(this)
                .load(wallpaperUrl)
                .into(previewImage);


        wallBtn.setOnClickListener(v ->{
            setAsWallpaper();

        });

    }


    private void setAsWallpaper() {

        Glide.with(this)
                .asBitmap()
                .load(wallpaperUrl)
                .into(new CustomTarget<Bitmap>() {

                    @Override
                    public void onResourceReady(
                            Bitmap bitmap,
                            Transition<? super Bitmap> transition) {

                        try {

                            WallpaperManager wallpaperManager =
                                    WallpaperManager.getInstance(
                                            WallpaperPreviewActivity.this
                                    );

                            wallpaperManager.setBitmap(
                                    bitmap,
                                    null,
                                    true,
                                    WallpaperManager.FLAG_SYSTEM
                            );

                            Toast.makeText(
                                    WallpaperPreviewActivity.this,
                                    "Wallpaper set",
                                    Toast.LENGTH_SHORT
                            ).show();

                        } catch (Exception e) {

                            Toast.makeText(
                                    WallpaperPreviewActivity.this,
                                    "Failed to set wallpaper",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    }

                    @Override
                    public void onLoadCleared(
                            Drawable placeholder) {
                    }
                });
    }



}
