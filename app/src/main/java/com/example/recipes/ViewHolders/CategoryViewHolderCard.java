package com.example.recipes.ViewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.recipes.R;

public class CategoryViewHolderCard extends RecyclerView.ViewHolder {
    public TextView name;
    public TextView description;
    public View card;
    public ImageButton btnDelete;
    public ImageButton btnEdit;

    public CategoryViewHolderCard(@NonNull View itemView) {
        super(itemView);

        card = itemView.findViewById(R.id.category_item_card);
        name = itemView.findViewById(R.id.recipe_name);
        btnDelete = itemView.findViewById(R.id.btn_delete_category);
        btnEdit = itemView.findViewById(R.id.btn_edit_category);
    }
}

