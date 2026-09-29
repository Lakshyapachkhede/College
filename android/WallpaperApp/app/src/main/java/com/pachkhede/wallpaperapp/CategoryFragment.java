package com.pachkhede.wallpaperapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CategoryFragment extends Fragment {

    private RecyclerView recyclerView;

    private CategoryAdapter adapter;

    private List<Category> categories = new ArrayList<>();

    private NexWallApi api;



    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView =
                view.findViewById(R.id.categories_recycler);

        recyclerView.setLayoutManager(
                new GridLayoutManager(requireContext(), 2)
        );

        adapter = new CategoryAdapter(
                categories,
                category -> {openCategory(category);}
        );

        recyclerView.setAdapter(adapter);

        api = RetrofitClient.getApi();

        loadCategories();
    }


    private void loadCategories() {
        Call currentCall = api.getCategories();

        currentCall.enqueue(
                new Callback<CategoryResponse>() {

                    @Override
                    public void onResponse(
                            Call<CategoryResponse> call,
                            Response<CategoryResponse> response) {

                        if (!isAdded()) {
                            return;
                        }

                        if (response.isSuccessful()
                                && response.body() != null) {

                            categories.clear();

                            categories.addAll(
                                    response.body().getData()
                            );

                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(
                            Call<CategoryResponse> call,
                            Throwable t) {

                        if (!isAdded()) {
                            return;
                        }
                    }
                }
        );
    }

    private void openCategory(Category category) {

        Intent intent = new Intent(
                requireContext(),
                CategoryWallpaperActivity.class
        );

        intent.putExtra(
                "category_id",
                category.getId()
        );

        intent.putExtra(
                "category_name",
                category.getName()
        );

        startActivity(intent);
    }


}