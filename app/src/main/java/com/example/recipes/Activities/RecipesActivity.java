package com.example.recipes.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
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

public class RecipesActivity extends AppCompatActivity {
    @BindView(R.id.title_recipes)
    TextView titleRecipe;
    @BindView(R.id.recipeName)
    EditText recipeName;
    @BindView(R.id.ingredients)
    EditText ingredients;
    @BindView(R.id.tempPrepare)
    EditText tempPrepare;
    @BindView(R.id.yield)
    EditText yield;
    @BindView(R.id.prepareMode)
    EditText prepareMode;
    @BindView(R.id.spinner_category)
    Spinner spinnerCategory;
    private Recipe recipe;
    private RecipeDAO recipeDAO;
    private CategoryDAO categoryDAO;
    private Category selectedCategory;
    private ArrayList<Category> categories;
    private SpinnerCategoryAdapterItens categoryAdapterItens;
    private Category category;
    private long id = -1;
    private final int SELECT_PICTURE = 1;
    private String selectdImagePath;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receitas_form);
        ButterKnife.bind(this);
        this.recipe = new Recipe();
        this.recipeDAO = new RecipeDAO(this);
        this.categoryDAO = new CategoryDAO(this);
        categories = categoryDAO.list();

        if (getIntent().getExtras() != null) {
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

        this.getPermission();

        if (this.id != -1) {
            Recipe recipe = recipeDAO.get(this.id);
            if (recipe == null) {
                return;
            }

            category = categoryDAO.get(recipe.getCategoryId());
            this.spinnerCategory.setSelection(categoryAdapterItens.getPosition(category));
            this.titleRecipe.setText("Editar Receita");
            this.recipeName.setText(recipe.getName());
            this.ingredients.setText(recipe.getIngredients());
            this.tempPrepare.setText(String.valueOf(recipe.getTime()));
            this.prepareMode.setText(recipe.getMethodOfPreparation());
            this.yield.setText(String.valueOf(recipe.getYield()));
        }
    }

    @OnClick(R.id.btn_save_recipes)
    public void onBtnSaveClicked() {
        String name = recipeName.getText().toString();
        String ingredientsRecipe = ingredients.getText().toString();
        String tempPrepareRecipes = tempPrepare.getText().toString();
        String prepareModeRecipes = prepareMode.getText().toString();
        String yieldRecipes = yield.getText().toString();
        Category category = selectedCategory;


        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(ingredientsRecipe) || TextUtils.isEmpty(tempPrepareRecipes) || TextUtils.isEmpty(prepareModeRecipes) || TextUtils.isEmpty(yieldRecipes)) {
            Toast.makeText(this, "Preencha TODOS os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        int yeildInt = Integer.parseInt(yieldRecipes);
        int tempInt = Integer.parseInt(tempPrepareRecipes);
        recipe.setName(name);
        recipe.setIngredients(ingredientsRecipe);
        recipe.setTime(tempInt);
        recipe.setMethodOfPreparation(prepareModeRecipes);
        recipe.setYield(yeildInt);
        recipe.setCategoryId(category.getId());
        recipe.setCategory(category);

        if (this.id == -1) {
            Toast.makeText(this, "Receita Adicionada!", Toast.LENGTH_SHORT).show();
            recipeDAO.create(recipe);
        } else {
            Toast.makeText(this, "Receita Editada!", Toast.LENGTH_SHORT).show();
            recipe.setId(this.id);
            recipeDAO.update(recipe);
        }
        super.onBackPressed();
    }

    public void getPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
    }

    @OnClick(R.id.btn_back)
    public void onBtnBackClicked() {
        super.onBackPressed();
    }

    @OnClick(R.id.open_galery)
    public void onGaleryClicked() {
        Intent intent = new Intent(Intent.ACTION_PICK,  MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == SELECT_PICTURE){
            switch (requestCode){
                case SELECT_PICTURE:{
                    if (data != null){
                        Uri selectImage = data.getData();
                        selectdImagePath = getPath(selectImage);
                    }
                }
            }
        }
    }

    public String getPath(Uri uri){
        if (uri == null){
            Toast.makeText(getBaseContext(),"Nenhuma Imagem", Toast.LENGTH_SHORT).show();
            return null;
        }
        String[] file = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, file, null, null, null);
        if (cursor != null){
            int column = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();

            return cursor.getString(column);
        }
        return uri.getPath();
    }
}
