package com.example.recipes.Adapters;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.recipes.Activities.RecipeIdActivity;
import com.example.recipes.Activities.RecipesActivity;
import com.example.recipes.DAO.RecipeDAO;
import com.example.recipes.Models.Recipe;
import com.example.recipes.R;
import com.example.recipes.ViewHolders.RecipesViewHolder;

import java.util.ArrayList;

public class RecipesAdapterItens extends RecyclerView.Adapter<RecipesViewHolder> {

    private ArrayList<Recipe> recipes;
    public RecipeDAO recipeDAO;
    private Context context;

    public RecipesAdapterItens(Context context, ArrayList<Recipe> recipes){
        this.context = context;
        this.recipeDAO = new RecipeDAO(context);
        this.recipes = recipes;
    }


    @NonNull
    @Override
    public RecipesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recipes, viewGroup, false);
        return new RecipesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipesViewHolder recipesViewHolder, final int i) {
        recipesViewHolder.name.setText(this.recipes.get(i).getName());

        recipesViewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipeDAO.remove(recipes.get(i).getId());
                attItens(recipeDAO.list());
            }
        });

        recipesViewHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RecipesActivity.class);
                intent.putExtra("id", recipes.get(i).getId());
                context.startActivity(intent);
            }
        });

        recipesViewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RecipeIdActivity.class);
                intent.putExtra("id", recipes.get(i).getId());
                context.startActivity(intent);
            }
        });
    }

    public void attItens(ArrayList<Recipe> recipes){
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }
}
