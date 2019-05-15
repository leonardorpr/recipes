package com.example.recipes.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.recipes.Activities.CategoryActivity;
import com.example.recipes.Activities.RecipeCategoryActivity;
import com.example.recipes.DAO.CategoryDAO;
import com.example.recipes.DAO.RecipeDAO;
import com.example.recipes.Models.Category;
import com.example.recipes.Models.Recipe;
import com.example.recipes.R;
import com.example.recipes.ViewHolders.CategoryViewHolderCard;

import java.util.ArrayList;

public class CategoryAdapterItens extends RecyclerView.Adapter<CategoryViewHolderCard> {

    private ArrayList<Category> categories;
    public CategoryDAO categoryDAO;
    private Context context;
    private RecipeDAO recipeDAO;

    public CategoryAdapterItens(Context context, ArrayList<Category> categories){
        this.context = context;
        this.recipeDAO = new RecipeDAO(context);
        this.categoryDAO = new CategoryDAO(context);
        this.categories = categories;
    }


    @NonNull
    @Override
    public CategoryViewHolderCard onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, viewGroup, false);
        return new CategoryViewHolderCard(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolderCard categoryViewHolderCard, final int i) {
        categoryViewHolderCard.name.setText(this.categories.get(i).getName());
        categoryViewHolderCard.description.setText(this.categories.get(i).getDescription());

        final ArrayList<Recipe> recipes = recipeDAO.getRecipeByCategoryId(this.categories.get(i).getId());

        categoryViewHolderCard.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RecipeCategoryActivity.class);
                intent.putExtra("id", categories.get(i).getId());
                context.startActivity(intent);
            }
        });

        categoryViewHolderCard.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recipes.isEmpty()){
                    categoryDAO.remove(categories.get(i).getId());
                    attItens(categoryDAO.list());
                }else{
                    Toast.makeText(context, "Você não pode apagar categorias que tenham receitas!", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });


        categoryViewHolderCard.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CategoryActivity.class);
                intent.putExtra("id", categories.get(i).getId());
                context.startActivity(intent);
            }
        });
    }

    public void attItens(ArrayList<Category> categories){
        this.categories = categories;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

}
