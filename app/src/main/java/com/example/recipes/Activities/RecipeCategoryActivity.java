package com.example.recipes.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.recipes.Adapters.RecipesAdapterItens;
import com.example.recipes.DAO.CategoryDAO;
import com.example.recipes.DAO.RecipeDAO;
import com.example.recipes.Models.Category;
import com.example.recipes.Models.Recipe;
import com.example.recipes.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecipeCategoryActivity extends AppCompatActivity {
    @BindView(R.id.title_recipes_category) TextView title;
    @BindView(R.id.descripiton_recipes_category) TextView description;
    private long id;
    private CategoryDAO categoryDAO;
    private RecipeDAO recipeDAO;
    private ArrayList<Recipe> recipes;
    private Category category;
    private RecipesAdapterItens recipesAdapterItens;

    public void onCreate(Bundle savedInstanced) {
        super.onCreate(savedInstanced);
        setContentView(R.layout.activity_recipe_category);
        ButterKnife.bind(this);

        this.categoryDAO = new CategoryDAO(this);

        if (getIntent().getExtras() != null) {
            this.id = getIntent().getExtras().getLong("id");
            this.category = categoryDAO.get(this.id);
        }

        this.title.setText(this.category.getName());
        this.description.setText(this.category.getDescription());
        this.recipesAdapterItens = new RecipesAdapterItens(this, new ArrayList<Recipe>());

        RecicleViewRecipes();
    }

    public void RecicleViewRecipes() {
        RecyclerView recycleViewrecipes = findViewById(R.id.recycleview_recipes_category);
        LinearLayoutManager linearLayoutManagerRecipes = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recycleViewrecipes.setLayoutManager(linearLayoutManagerRecipes);
        recycleViewrecipes.setAdapter(recipesAdapterItens);
    }

    public void onResume() {
        super.onResume();

        getAllRecipes();
    }

    public void getAllRecipes() {
        RecipeDAO recipeDAO = new RecipeDAO(this);
        recipesAdapterItens.attItens(recipeDAO.getRecipeByCategoryId(this.id));
    }


    @OnClick(R.id.btn_back)
    public void onViewClicked() {
        super.onBackPressed();
    }
}
