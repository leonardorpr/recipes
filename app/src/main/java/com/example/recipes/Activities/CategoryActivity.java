package com.example.recipes.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.recipes.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoryActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_category_form);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.btn_Back)
    public void onBtnBackClicked() {
        super.onBackPressed();
    }

    @OnClick(R.id.btn_Save)
    public void onBtnSaveClicked() {

    }

}
