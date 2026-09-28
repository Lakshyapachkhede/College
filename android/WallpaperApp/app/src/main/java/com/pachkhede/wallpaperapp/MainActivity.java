package com.pachkhede.wallpaperapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ViewPager2 viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

            } else if (id == R.id.nav_liked) {
                viewPager.setCurrentItem(2);
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
                                    .setSelectedItemId(R.id.nav_liked);
                        }
                    }
                }
        );

    }



}