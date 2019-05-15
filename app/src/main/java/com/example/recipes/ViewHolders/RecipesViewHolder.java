package com.example.recipes.ViewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.recipes.R;


public class RecipesViewHolder extends RecyclerView.ViewHolder {
    public TextView name;
    public View card;
    public ImageButton btnEdit;
    public ImageButton btnDelete;

    public RecipesViewHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.recipe_name);
        card = itemView.findViewById(R.id.recipe_card);
        btnEdit = itemView.findViewById(R.id.btn_edit_recipes);
        btnDelete = itemView.findViewById(R.id.btn_delete_recipes);
    }
}
