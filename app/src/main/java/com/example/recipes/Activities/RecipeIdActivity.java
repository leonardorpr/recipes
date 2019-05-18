package com.example.recipes.Activities;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recipes.DAO.RecipeDAO;
import com.example.recipes.Models.Recipe;
import com.example.recipes.R;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecipeIdActivity extends AppCompatActivity {
    @BindView(R.id.title_recipes_id)
    TextView nameRecipe;
    @BindView(R.id.ingredients_recipes_id)
    TextView ingredientsRecipe;
    @BindView(R.id.yield_recipes_id)
    TextView yieldRecipes;
    @BindView(R.id.method_prepare_recipes)
    TextView prepareModeRecipes;
    @BindView(R.id.tempPrepare_recipes_id)
    TextView tempPrepareRecipes;
    @BindView(R.id.btn_favorite)
    ImageButton btnFavorite;
    @BindView(R.id.img_recipe)
    ImageView imageRecipe;
    private long id;
    private Recipe recipe;
    private RecipeDAO recipeDAO;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_id);
        ButterKnife.bind(this);
        recipeDAO = new RecipeDAO(getBaseContext());

        if (getIntent().getExtras() != null) {
            this.id = getIntent().getExtras().getLong("id");
            this.recipe = recipeDAO.get(this.id);
        }


        this.nameRecipe.setText(this.recipe.getName());
        this.ingredientsRecipe.setText(this.recipe.getIngredients());
        this.prepareModeRecipes.setText(this.recipe.getMethodOfPreparation());
        this.yieldRecipes.setText(this.recipe.getYield() + " Por.");
        this.tempPrepareRecipes.setText(this.recipe.getTime() + " Min.");
        File imgFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + File.separator + "Receitas" + File.separator + recipe.getImageName());
        this.imageRecipe.setImageURI(Uri.fromFile(imgFile));
        setFav();
    }


    @OnClick(R.id.btn_back)
    public void onBackClicked() {
        super.onBackPressed();
    }

    @OnClick(R.id.btn_favorite)
    public void onFavoriteClicked() {
        if (this.recipe.getIsFavorite()) {
            this.recipe.setIsFavorite(false);
        } else {
            this.recipe.setIsFavorite(true);
        }
        Log.e(recipe.getIsFavorite().toString(), "erro");
        recipeDAO.update(this.recipe);
        setFav();
    }

    public void setFav(){
        if (this.recipe.getIsFavorite()){
            this.btnFavorite.setBackgroundResource(R.drawable.ic_favorite_true);
        }else{
            this.btnFavorite.setBackgroundResource(R.drawable.ic_favorite_default);
        }
    }
}

