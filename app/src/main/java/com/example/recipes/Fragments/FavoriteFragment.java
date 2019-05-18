package com.example.recipes.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recipes.Adapters.RecipesAdapterItens;
import com.example.recipes.DAO.RecipeDAO;
import com.example.recipes.Models.Recipe;
import com.example.recipes.R;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FavoriteFragment extends Fragment {
    RecipesAdapterItens recipesAdapterItens;
    RecipeDAO recipeDAO;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.recipesAdapterItens = new RecipesAdapterItens(getContext(), new ArrayList<Recipe>());
        RecicleViewRecipes();
    }

    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
    }

    public void RecicleViewRecipes(){
        RecyclerView recycleViewrecipes = getActivity().findViewById(R.id.recycleview_recipes);
        LinearLayoutManager linearLayoutManagerRecipes = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recycleViewrecipes.setLayoutManager(linearLayoutManagerRecipes);
        recycleViewrecipes.setAdapter(recipesAdapterItens);
    }

    public void getAllFavorite(){
        recipeDAO = new RecipeDAO(getContext());
        recipesAdapterItens.attItens(recipeDAO.listFavorites());
    }

    public void onResume(){
        super.onResume();

        getAllFavorite();
    }
}
