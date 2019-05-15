package com.example.recipes.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipes.Adapters.CategoryAdapterItens;
import com.example.recipes.DAO.CategoryDAO;
import com.example.recipes.Models.Category;
import com.example.recipes.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CategoryActivity extends AppCompatActivity {
    Unbinder unbinder;
    @BindView(R.id.title_category) TextView title_category;
    @BindView(R.id.name_category) EditText nameCategory;
    @BindView(R.id.description_category) EditText descriptionCategory;
    public CategoryAdapterItens categoryAdapterItens;
    private long id = -1;
    private CategoryDAO categoryDAO;
    private ArrayList<Category> categories;
    private Category category;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_category_form);
        ButterKnife.bind(this);
        this.categoryDAO = new CategoryDAO(this);
        this.category = new Category();

        if(getIntent().getExtras() != null){
            this.id = getIntent().getExtras().getLong("id");
        }

        if(this.id != -1){
            category = categoryDAO.get(this.id);
            this.title_category.setText("Editar Categoria");
            this.nameCategory.setText(category.getName());
            this.descriptionCategory.setText(category.getDescription());
        }
    }

    @OnClick(R.id.btn_back_category)
    public void onBtnBackClicked() {
        super.onBackPressed();
    }

    @OnClick(R.id.btn_save_category)
    public void onBtnSaveClicked() {
       String name = nameCategory.getText().toString();
       String description = descriptionCategory.getText().toString();

       if(TextUtils.isEmpty(name) || TextUtils.isEmpty(description)){
            Toast.makeText(this,"Preencha TODOS os campos!", Toast.LENGTH_SHORT).show();
            return;
       }

       category.setName(name);
       category.setDescription(description);

       if(this.id == -1){
           if(GetName(category.getName()) == null) {
               Toast.makeText(this, "Categoria Adicionada!", Toast.LENGTH_SHORT).show();
               categoryDAO.create(category);
           }else{
               Toast.makeText(this,"Categoria já EXISTE!", Toast.LENGTH_SHORT).show();
               return;
           }
       }else if (GetName(category.getName()) == null){
           Toast.makeText(this,"Categoria Editada com Sucesso", Toast.LENGTH_SHORT).show();
           category.setId(this.id);
           categoryDAO.update(category);
       }
       super.onBackPressed();
    }

    private Category GetName(String name) {
        CategoryDAO categoryDAO = new CategoryDAO(getBaseContext());
        return categoryDAO.getByName(name);
    }
}