package com.pachkhede.wallpaperapp;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WallpaperGridViewModel extends AndroidViewModel {

    private final MutableLiveData<List<Wallpaper>> wallpapers =
            new MutableLiveData<>(new ArrayList<>());


    private final List<Wallpaper> allWallpapers = new ArrayList<>();

    private String query;
    private String sorting;
    private Integer category_id;

    private int currentPage = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private boolean initialized = false;

    private Call<WallpaperResponse> currentCall;

    private WallpaperDao wallpaperDao;

    public WallpaperGridViewModel(@NonNull Application application) {
        super(application);

        AppDatabase db = Room.databaseBuilder(
                application,
                AppDatabase.class,
                "wallpaper_database"
        ).build();

        wallpaperDao = db.wallpaperDao();

    }

    public LiveData<List<Wallpaper>> getWallpapers() {
        return wallpapers;
    }


    public void init(String query, String sorting, Integer category_id) {
        if (initialized) return;
        initialized = true;
        this.query = query;
        this.sorting = sorting;
        this.category_id = category_id;
        loadNextPage();
    }

    public void loadNextPage() {
        if (isLoading || isLastPage) return;
        isLoading = true;

        NexWallApi api = RetrofitClient.getApi();
        currentCall = api.searchWallpaper(
                query,category_id, currentPage, WallpaperGridFragment.perPage, sorting);

        currentCall.enqueue(new Callback<WallpaperResponse>() {
            @Override
            public void onResponse(Call<WallpaperResponse> call,
                                   Response<WallpaperResponse> response) {
                isLoading = false;

                if (response.isSuccessful() && response.body() != null) {
                    List<Wallpaper> newItems = response.body().getData();

                    if (newItems == null || newItems.isEmpty()) {
                        isLastPage = true;
                        return;
                    }

                    Log.d("TEMO", "id" + String.valueOf(newItems.get(0).getId()) + newItems.get(0).getImage_url());

                    allWallpapers.addAll(newItems);
                    currentPage++;

                    wallpapers.setValue(new ArrayList<>(allWallpapers));

                    for (Wallpaper wallpaper : newItems) {
                        saveWallpapersToDatabase(newItems);
                    }

                }
            }

            @Override
            public void onFailure(Call<WallpaperResponse> call, Throwable t) {
                isLoading = false;
                if (!call.isCanceled()) {

                }
            }
        });
    }

    @Override
    protected void onCleared() {
        if (currentCall != null) currentCall.cancel();
    }


    private void downloadWallpaper(Wallpaper wallpaper)
    {
        File wallpapersDir = new File(getApplication().getFilesDir(),
                "wallpapers");

        if (!wallpapersDir.exists()) {
            wallpapersDir.mkdirs();
        }

        File file = new File(wallpapersDir, wallpaper.getId() + ".jpg");
        try {
            File downloadedFile = Glide.with(getApplication())
                    .asFile()
                    .load(wallpaper.getImage_url())
                    .submit()
                    .get();

            FileOutputStream outputStream =
                    new FileOutputStream(file);

            FileInputStream inputStream =
                    new FileInputStream(downloadedFile);

            byte[] buffer = new byte[8192];

            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            inputStream.close();
            outputStream.close();

            WallpaperEntity entity =
                    new WallpaperEntity(
                            wallpaper.getId(),
                            file.getAbsolutePath(),
                            System.currentTimeMillis(),
                            false
                    );

            wallpaperDao.insert(entity);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveWallpapersToDatabase(List<Wallpaper> newWallpapers) {


        new Thread(new Runnable() {
            @Override
            public void run() {

                List<Wallpaper> wallpapersToAdd = new ArrayList<>();

                List<WallpaperEntity> allWallpaper = wallpaperDao.getAllWallpapers();

                Set<Integer> existingIds = new HashSet<>();

                for (WallpaperEntity entity : allWallpaper) {
                    existingIds.add(entity.getId());
                }


                for (Wallpaper wallpaper : newWallpapers) {
                    if (!existingIds.contains(wallpaper.getId())) {
                        wallpapersToAdd.add(wallpaper);
                    }
                }


                for (Wallpaper wallpaper : wallpapersToAdd)
                {
                    downloadWallpaper(wallpaper);
                }
            }
        }).start();

    }


}
