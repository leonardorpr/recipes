package com.example.recipes.Activities;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipes.Adapters.SpinnerCategoryAdapterItens;
import com.example.recipes.DAO.CategoryDAO;
import com.example.recipes.DAO.RecipeDAO;
import com.example.recipes.Models.Category;
import com.example.recipes.Models.Recipe;
import com.example.recipes.R;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class RecipesActivity extends AppCompatActivity {
    Unbinder unbinder;
    @BindView(R.id.title_recipes) TextView titleRecipe;
    @BindView(R.id.recipeName) EditText recipeName;
    @BindView(R.id.ingredients) EditText ingredients;
    @BindView(R.id.tempPrepare) EditText  tempPrepare;
    @BindView(R.id.yield) EditText yield;
    @BindView(R.id.prepareMode) EditText prepareMode;
    @BindView(R.id.spinner_category) Spinner spinnerCategory;
    private Recipe recipe;
    private RecipeDAO recipeDAO;
    private CategoryDAO categoryDAO;
    private Category selectedCategory;
    private ArrayList<Category> categories;
    private SpinnerCategoryAdapterItens categoryAdapterItens;
    private long id = -1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receitas_form);
        unbinder = ButterKnife.bind(this);
        this.recipe = new Recipe();
        this.recipeDAO = new RecipeDAO(this);
        this.categoryDAO = new CategoryDAO(this);
        categories = categoryDAO.list();

        if(getIntent().getExtras() != null){
            this.id = getIntent().getExtras().getLong("id");
        }


        spinnerCategory = findViewById(R.id.spinner_category);
        categoryAdapterItens = new SpinnerCategoryAdapterItens(this, categories);
        spinnerCategory.setAdapter(categoryAdapterItens);

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCategory = (Category) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if(this.id != -1){
            Recipe recipe = recipeDAO.get(this.id);

            if(recipe == null){
                return;
            }

            Category category = categoryDAO.get(recipe.getCategoryId());
            this.titleRecipe.setText("Editar Receita");
            this.spinnerCategory.setSelection(categoryAdapterItens.getPosition(category));
            this.recipeName.setText(recipe.getName());
            this.ingredients.setText(recipe.getIngredients());
            this.tempPrepare.setText(String.valueOf(recipe.getTime()));
            this.prepareMode.setText(recipe.getMethodOfPreparation());
            this.yield.setText(String.valueOf(recipe.getYield()));
        }
    }


    @OnClick(R.id.btn_back)
    public void onBtnBackClicked() {
        super.onBackPressed();
    }

    @OnClick(R.id.btn_save_recipes)
    public void onBtnSaveClicked() {
        String name = recipeName.getText().toString();
        String ingredientsRecipe = ingredients.getText().toString();
        String temp = tempPrepare.getText().toString();
        int tempPrepareRecipes = Integer.parseInt(temp);
        String prepareModeRecipes = prepareMode.getText().toString();
        String yild = yield.getText().toString();
        int yield = Integer.parseInt(yild);
        Category category = selectedCategory;


        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(ingredientsRecipe) || TextUtils.isEmpty(temp) || TextUtils.isEmpty(prepareModeRecipes) || TextUtils.isEmpty(yild)) {
            Toast.makeText(this, "Preencha TODOS os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        recipe.setName(name);
        recipe.setIngredients(ingredientsRecipe);
        recipe.setTime(tempPrepareRecipes);
        recipe.setMethodOfPreparation(prepareModeRecipes);
        recipe.setYield(yield);
        recipe.setCategoryId(category.getId());
        recipe.setCategory(category);

        if (this.id == -1) {
            Toast.makeText(this, "Receita Adicionada com Sucesso!", Toast.LENGTH_SHORT).show();
            recipeDAO.create(recipe);
        } else {
            Toast.makeText(this, "Receita Editada com Sucesso!", Toast.LENGTH_SHORT).show();
            recipe.setId(this.id);
            recipeDAO.update(recipe);
        }
        super.onBackPressed();
    }
}
