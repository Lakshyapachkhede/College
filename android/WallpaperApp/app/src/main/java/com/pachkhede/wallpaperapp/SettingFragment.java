package com.pachkhede.wallpaperapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import androidx.fragment.app.Fragment;


public class SettingFragment extends Fragment {

    private Switch switchlockWallpaper;
    private Switch switchhomeWallpaper;
    private Spinner spinnerInterval;
    private SharedPreferences preferences;


    private String[] intervals = {
            "10 seconds",
            "30 seconds",
            "1 minute",
            "5 minutes",
            "10 minutes",
            "30 minutes",
            "1 hour",
            "24 hours"
    };

    int[] intervalSeconds = {
            10,
            30,
            60,
            300,
            600,
            1800,
            3600,
            86400
    };

    public SettingFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        preferences = requireContext().getSharedPreferences(
                "wallpaper_settings",
                Context.MODE_PRIVATE
        );

        View view = inflater.inflate(
                R.layout.fragment_setting,
                container,
                false
        );

        switchlockWallpaper = view.findViewById(R.id.switchLock);
        switchhomeWallpaper = view.findViewById(R.id.switchHome);
        spinnerInterval = view.findViewById(R.id.spinnerInterval);



        setupIntervalSpinner();
        loadSettings();

        switchlockWallpaper.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {
                    preferences.edit()
                            .putBoolean("lock_wallpaper", isChecked)
                            .apply();

                }
        );

        switchhomeWallpaper.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {
                    preferences.edit()
                            .putBoolean("home_wallpaper", isChecked)
                            .apply();
                }
        );

        spinnerInterval.setOnItemSelectedListener(
                new android.widget.AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(
                            android.widget.AdapterView<?> parent,
                            View view,
                            int position,
                            long id) {

                        int selectedInterval = intervalSeconds[position];

                        preferences.edit()
                                .putInt("interval", selectedInterval)
                                .apply();
                    }

                    @Override
                    public void onNothingSelected(
                            android.widget.AdapterView<?> parent) {
                    }
                }
        );

        return view;
    }

    private void setupIntervalSpinner() {

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        requireContext(),
                        android.R.layout.simple_spinner_item,
                        intervals
                );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spinnerInterval.setAdapter(adapter);
    }

    private void loadSettings() {

        boolean homeWallpaper = preferences.getBoolean("home_wallpaper", false);
        boolean lockWallpaper = preferences.getBoolean("lock_wallpaper", false);
        int savedInterval = preferences.getInt("interval", 60);

        switchhomeWallpaper.setChecked(homeWallpaper);
        switchlockWallpaper.setChecked(lockWallpaper);

        for (int i = 0; i < intervalSeconds.length; i++) {
            if (intervalSeconds[i] == savedInterval) {
                spinnerInterval.setSelection(i);
                break;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        loadSettings();
    }

}

