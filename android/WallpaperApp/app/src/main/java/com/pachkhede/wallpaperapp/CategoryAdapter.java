package com.pachkhede.wallpaperapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CategoryAdapter
        extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {

    private List<Category> categories;

    private OnCategoryClickListener listener;

    public interface OnCategoryClickListener {
        void onCategoryClick(Category category);
    }

    public CategoryAdapter(
            List<Category> categories,
            OnCategoryClickListener listener) {

        this.categories = categories;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.category_recycler_item,
                        parent,
                        false
                );

        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull CategoryHolder holder,
            int position) {

        Category category = categories.get(position);

        holder.name.setText(category.getName());

        holder.count.setText(
                category.getWallpaperCount() + " wallpapers"
        );

        Glide.with(holder.itemView.getContext())
                .load(category.getCoverImageUrl())
                .into(holder.image);

        holder.itemView.setOnClickListener(v ->
                listener.onCategoryClick(category)
        );
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    static class CategoryHolder
            extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;
        TextView count;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.category_image);
            name = itemView.findViewById(R.id.category_name);
            count = itemView.findViewById(R.id.category_count);
        }
    }
}
