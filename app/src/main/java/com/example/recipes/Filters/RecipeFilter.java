package com.example.recipes.Filters;

import android.widget.Filter;

import org.apache.commons.lang3.StringUtils;

import com.example.recipes.Adapters.RecipesAdapterItens;
import com.example.recipes.Models.Recipe;

import java.util.ArrayList;

public class RecipeFilter extends Filter {
    private RecipesAdapterItens adapter;
    private ArrayList<Recipe> recipes;

    public RecipeFilter(RecipesAdapterItens adapter, ArrayList<Recipe> recipes) {
        super();

        this.adapter = adapter;
        this.recipes = recipes;
    }

    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        FilterResults filterResults = new FilterResults();

        if (charSequence == null || charSequence.length() == 0) {
            filterResults.values = this.recipes;
            filterResults.count = this.recipes.size();
        } else {
            ArrayList<Recipe> filters = new ArrayList<>();

            for(Recipe recipe: recipes) {
                if(StringUtils.stripAccents(recipe.getName()).toUpperCase().contains(StringUtils.stripAccents(charSequence.toString()).toUpperCase())) {
                    filters.add(recipe);
                }
            }

            filterResults.values = filters;
            filterResults.count = filters.size();
        }

        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

    }

    @Override
    public CharSequence convertResultToString(Object resultValue) {
        return super.convertResultToString(resultValue);
    }
}
