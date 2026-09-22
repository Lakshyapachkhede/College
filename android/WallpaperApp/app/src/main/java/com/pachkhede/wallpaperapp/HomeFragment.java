package com.pachkhede.wallpaperapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {




    public HomeFragment() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        WallhavenApi api = RetrofitClient.getApi();

        api.searchWallpaper(
                "nature",
                "111",
                "100",
                "hot",
                1
        ).enqueue(new Callback<WallpaperResponse>() {

            @Override
            public void onResponse(
                    Call<WallpaperResponse> call,
                    Response<WallpaperResponse> response) {

                if (response.isSuccessful() && response.body() != null) {

                    List<Wallpaper> wallpapers =
                            response.body().getData();

                    Log.d("WALLHAVEN",
                            "Wallpapers: " + wallpapers.size());
                    String text = "WALLHAVEN";
                    for (Wallpaper wallpaper : wallpapers) {
                        text += wallpaper.getPath() + "\n";
                    }

                    TextView t = view.findViewById(R.id.temp);
                    t.setText(text);

                } else {

                    Log.e("WALLHAVEN",
                            "Response error: " + response.code());
                }
            }

            @Override
            public void onFailure(
                    Call<WallpaperResponse> call,
                    Throwable t) {

                Log.e("WALLHAVEN",
                        "Network error", t);
            }
        });
    }
}