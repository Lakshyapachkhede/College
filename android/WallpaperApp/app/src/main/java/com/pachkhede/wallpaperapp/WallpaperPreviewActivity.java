package com.pachkhede.wallpaperapp;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.room.Room;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class WallpaperPreviewActivity extends AppCompatActivity {

    String wallpaperUrl;
    int wallpaperId;
    ImageView previewImage;
    ImageButton downloadBtn, likeBtn;
    Button wallBtn;
    private SharedPreferences preferences;
    WallpaperDao wallpaperDao;
    boolean isLiked;
    boolean isPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());

        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "wallpaper_database").build();
        wallpaperDao = db.wallpaperDao();

        setContentView(R.layout.activity_wallpaper_preview);

        preferences = getSharedPreferences(
                "wallpaper_settings",
                Context.MODE_PRIVATE
        );

        previewImage = findViewById(R.id.preview_image);
        downloadBtn = findViewById(R.id.download_btn);
        likeBtn = findViewById(R.id.like_btn);
        wallBtn = findViewById(R.id.set_wallpaper_btn);

        wallpaperUrl = getIntent().getStringExtra("wallpaper_url");
        wallpaperId = getIntent().getIntExtra("wallpaper_id", -1);
        isPath = getIntent().getBooleanExtra("is_path", false);

        Glide.with(this)
                .load(wallpaperUrl)
                .into(previewImage);

        wallBtn.setOnClickListener(v -> {
            showWallpaperDialog();
        });

        downloadBtn.setOnClickListener(v -> {
            downloadWallpaper();
        });

        setUpLike();


    }

    private void setUpLike(){
        new Thread(() -> {

            boolean liked = wallpaperDao.isLiked(wallpaperId);

            runOnUiThread(() -> {

                isLiked = liked;

                if (isLiked) {
                    likeBtn.setImageResource(R.drawable.heart);
                } else {
                    likeBtn.setImageResource(R.drawable.heart_outline);
                }

            });

        }).start();


        likeBtn.setOnClickListener(v -> {

            isLiked = !isLiked;

            if (isLiked) {
                likeBtn.setImageResource(R.drawable.heart);
            } else {
                likeBtn.setImageResource(R.drawable.heart_outline);
            }

            new Thread(() -> {
                wallpaperDao.setLiked(wallpaperId, isLiked);
            }).start();

        });
    }

    private void showWallpaperDialog() {

        String[] options = {
                "Home screen",
                "Lock screen",
                "Both"
        };

        new AlertDialog.Builder(this)
                .setTitle("Set wallpaper")
                .setItems(options, (dialog, which) -> {

                    if (which == 0) {
                        setAsWallpaper(WallpaperManager.FLAG_SYSTEM);
                        preferences.edit()
                                .putBoolean("home_wallpaper", false)
                                .apply();
                    }
                    else if (which == 1) {

                        setAsWallpaper(WallpaperManager.FLAG_LOCK);
                        preferences.edit()
                                .putBoolean("lock_wallpaper", false)
                                .apply();
                    }
                    else {
                        setAsWallpaper(
                                WallpaperManager.FLAG_SYSTEM |
                                        WallpaperManager.FLAG_LOCK

                        );
                        preferences.edit()
                                .putBoolean("home_wallpaper", false)
                                .apply();
                        preferences.edit()
                                .putBoolean("lock_wallpaper", false)
                                .apply();
                    }

                })
                .show();
    }

    private void setAsWallpaper(int flag) {

        new Thread(() -> {

            try {

                Bitmap bitmap = Glide.with(this)
                        .asBitmap()
                        .load(wallpaperUrl)
                        .submit()
                        .get();

                WallpaperManager wallpaperManager =
                        WallpaperManager.getInstance(
                                WallpaperPreviewActivity.this
                        );

                wallpaperManager.setBitmap(
                        bitmap,
                        null,
                        true,
                        flag
                );

                runOnUiThread(() -> {

                    Toast.makeText(
                            WallpaperPreviewActivity.this,
                            "Wallpaper set",
                            Toast.LENGTH_SHORT
                    ).show();

                });

            } catch (Exception e) {

                e.printStackTrace();

                runOnUiThread(() -> {

                    Toast.makeText(
                            WallpaperPreviewActivity.this,
                            "Failed to set wallpaper",
                            Toast.LENGTH_SHORT
                    ).show();

                });
            }

        }).start();
    }


    private void downloadWallpaper() {

        new Thread(() -> {

            try {
                File downloadedFile;
                if(isPath)
                {
                    downloadedFile = new File(wallpaperUrl);
                }
                else
                {
                    downloadedFile = Glide.with(getApplicationContext())
                            .asFile()
                            .load(wallpaperUrl)
                            .submit()
                            .get();
                }


                String fileName =
                        "wallpaper_" + System.currentTimeMillis() + ".jpg";

                ContentValues values = new ContentValues();

                values.put(
                        MediaStore.Downloads.DISPLAY_NAME,
                        fileName
                );

                values.put(
                        MediaStore.Downloads.MIME_TYPE,
                        "image/jpeg"
                );

                values.put(
                        MediaStore.Downloads.RELATIVE_PATH,
                        Environment.DIRECTORY_DOWNLOADS + "/wallpapers"
                );

                Uri uri = getContentResolver().insert(
                        MediaStore.Downloads.EXTERNAL_CONTENT_URI,
                        values
                );

                if (uri == null) {
                    throw new Exception("Could not create download file");
                }

                InputStream inputStream =
                        new FileInputStream(downloadedFile);

                OutputStream outputStream =
                        getContentResolver().openOutputStream(uri);

                byte[] buffer = new byte[8192];

                int bytesRead;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                inputStream.close();
                outputStream.close();

                runOnUiThread(() -> {

                    Toast.makeText(
                            WallpaperPreviewActivity.this,
                            "Wallpaper Downloaded",
                            Toast.LENGTH_SHORT
                    ).show();

                });

            } catch (Exception e) {

                e.printStackTrace();

                runOnUiThread(() -> {

                    Toast.makeText(
                            WallpaperPreviewActivity.this,
                            "Download failed: " + e.getMessage(),
                            Toast.LENGTH_LONG
                    ).show();

                    Log.d("DOWNLOAD", "Download failed: " + e.getMessage());
                });
            }

        }).start();
    }


}