package com.pachkhede.wallpaperapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CategoryWallpaperActivity extends AppCompatActivity {
    TextView categoryName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_wallpaper);
        int categoryId =
                getIntent().getIntExtra("category_id", -1);

        categoryName = findViewById(R.id.category_name);
        String name =  getIntent().getStringExtra("category_name");
        categoryName.setText(name);

        if (savedInstanceState == null) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(
                            R.id.category_container,
                            WallpaperGridFragment.newInstance(
                                    "",
                                    "popular",
                                    categoryId
                            )
                    )
                    .commit();
        }
    }
}