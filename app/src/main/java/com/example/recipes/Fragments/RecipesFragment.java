package com.example.recipes.Fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recipes.Activities.RecipesActivity;
import com.example.recipes.Adapters.RecipesAdapterItens;
import com.example.recipes.DAO.CategoryDAO;
import com.example.recipes.DAO.RecipeDAO;
import com.example.recipes.Models.Category;
import com.example.recipes.Models.Recipe;
import com.example.recipes.R;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RecipesFragment extends Fragment {
    private CategoryDAO categoryDAO;
    private ArrayList<Category> category;
    private Unbinder unbinder;
    private RecipesAdapterItens recipesAdapterItens;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_receitas, container, false);

        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    public static RecipesFragment newInstance() {
        return new RecipesFragment();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.recipesAdapterItens = new RecipesAdapterItens(getContext(), new ArrayList<Recipe>());
        RecicleViewRecipes();
    }

    @OnClick(R.id.btn_recipes_add)
    public void onViewClicked() {
        categoryDAO = new CategoryDAO(getContext());
        category = categoryDAO.list();

        if(category.isEmpty()){
            new AlertDialog.Builder(getContext()).setTitle("Opss, melhor não..").
                    setMessage("Crie uma Categoria Primeiro!").show();

            return;
        }else {
            startActivity(new Intent(getActivity(), RecipesActivity.class));
        }
    }

    public void RecicleViewRecipes(){
        RecyclerView recycleViewrecipes = getActivity().findViewById(R.id.recycleview_recipes);
        LinearLayoutManager linearLayoutManagerRecipes = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recycleViewrecipes.setLayoutManager(linearLayoutManagerRecipes);
        recycleViewrecipes.setAdapter(recipesAdapterItens);
    }

    public void onResume(){
        super.onResume();

        getAllRecipes();
    }

    public void getAllRecipes(){
        RecipeDAO recipeDAO = new RecipeDAO(getContext());
        recipesAdapterItens.attItens(recipeDAO.list());
    }
}
