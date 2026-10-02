package com.pachkhede.wallpaperapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import androidx.viewpager2.widget.ViewPager2;
import android.Manifest;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ViewPager2 viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS},
                        100
                );
            }
        }

        Intent intent = new Intent(this, WallpaperService.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent);
        } else {
            startService(intent);
        }

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        viewPager = findViewById(R.id.main_view_pager);
        MainPagerAdapter adapter =
                new MainPagerAdapter(this);

        viewPager.setAdapter(adapter);


        bottomNavigationView.setOnItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.nav_home) {
                viewPager.setCurrentItem(0);

            } else if (id == R.id.nav_categories) {
                viewPager.setCurrentItem(1);

            }else if (id == R.id.nav_search) {
                viewPager.setCurrentItem(2);

            } else if (id == R.id.nav_liked) {
                viewPager.setCurrentItem(3);
            } else if(id == R.id.nav_settings)
            {
                viewPager.setCurrentItem(4);
            }


            return true;
        });


        viewPager.registerOnPageChangeCallback(
                new ViewPager2.OnPageChangeCallback() {

                    @Override
                    public void onPageSelected(int position) {

                        if (position == 0) {
                            bottomNavigationView
                                    .setSelectedItemId(R.id.nav_home);

                        } else if (position == 1) {
                            bottomNavigationView
                                    .setSelectedItemId(R.id.nav_categories);

                        } else if (position == 2) {
                            bottomNavigationView
                                    .setSelectedItemId(R.id.nav_search);
                        }else if (position == 3) {
                            bottomNavigationView
                                    .setSelectedItemId(R.id.nav_liked);
                        }else if (position == 4) {
                            bottomNavigationView
                                    .setSelectedItemId(R.id.nav_settings);
                        }
                    }
                }
        );

    }



}