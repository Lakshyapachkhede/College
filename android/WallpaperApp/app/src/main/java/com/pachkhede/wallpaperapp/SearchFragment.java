package com.pachkhede.wallpaperapp;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;


public class SearchFragment extends Fragment {

    EditText etSearch;
    ImageButton searchBtn;

    public SearchFragment() {
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_search,
                container,
                false
        );

        etSearch = view.findViewById(R.id.et_search);
        searchBtn = view.findViewById(R.id.search_btn);

        searchBtn.setOnClickListener(v->{
            String query = etSearch.getText().toString();
            if(query.isEmpty())
            {
                etSearch.setError("Enter query");
                return;
            }
            getChildFragmentManager()
                    .beginTransaction()
                    .replace(
                            R.id.wallpaper_grid_container,
                            WallpaperGridFragment.newInstance(query, "popular", null)
                    )
                    .commit();
        });




        return view;
    }
}
