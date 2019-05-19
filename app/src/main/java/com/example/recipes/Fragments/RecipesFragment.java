package com.example.recipes.Fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.recipes.Activities.RecipesActivity;
import com.example.recipes.Adapters.RecipesAdapterItens;
import com.example.recipes.DAO.CategoryDAO;
import com.example.recipes.DAO.RecipeDAO;
import com.example.recipes.Filters.RecipeFilter;
import com.example.recipes.Models.Category;
import com.example.recipes.Models.Recipe;
import com.example.recipes.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RecipesFragment extends Fragment {
    @BindView(R.id.search)
    EditText search;
    private CategoryDAO categoryDAO;
    private ArrayList<Category> category;
    private Unbinder unbinder;
    private RecipesAdapterItens recipesAdapterItens;
    private RecipeFilter recipeFilter;


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
        setSearchListener();
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
            AlertDialog.Builder decision = new AlertDialog.Builder(getActivity());
            View decisionView = LayoutInflater.from(getActivity()).inflate(R.layout.decision_new_recipe, null);

            ImageView btnNewRecipe = decisionView.findViewById(R.id.btn_new_recipe);
            ImageView btnNewWeb = decisionView.findViewById(R.id.btn_new_recipe_web);
            ImageView btnCancel = decisionView.findViewById(R.id.btn_cancel);

            decision.setView(decisionView);
            final AlertDialog dialog = decision.create();
            dialog.show();

            btnNewRecipe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.hide();
                    Intent intent = new Intent(getActivity(), RecipesActivity.class);
                    intent.putExtra("isWeb", false);
                    intent.putExtra("id", Long.valueOf(-1));
                    startActivity(intent);
                }
            });

            btnNewWeb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.hide();
                    Intent intent = new Intent(getActivity(), RecipesActivity.class);
                    intent.putExtra("isWeb", true);
                    intent.putExtra("id", Long.valueOf(-1));
                    startActivity(intent);
                }
            });

            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.hide();
                }
            });
        }
    }

    public void RecicleViewRecipes(){
        RecyclerView recycleViewrecipes = getActivity().findViewById(R.id.recycleview_recipes);
        LinearLayoutManager linearLayoutManagerRecipes = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recycleViewrecipes.setLayoutManager(linearLayoutManagerRecipes);
        recycleViewrecipes.setAdapter(recipesAdapterItens);
        this.recipeFilter = new RecipeFilter(recipesAdapterItens, new ArrayList<Recipe>());
    }

    public void onResume(){
        super.onResume();

        getAllRecipes();
    }

    public void getAllRecipes(){
        RecipeDAO recipeDAO = new RecipeDAO(getContext());
        recipeFilter.attItens(recipeDAO.list());
        recipesAdapterItens.attItens(recipeDAO.list());
    }

    private void setSearchListener() {
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                recipeFilter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
