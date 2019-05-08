package com.example.recipes.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recipes.Activities.ReceitaActivity;
import com.example.recipes.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RecipesFragment extends Fragment {

    private Unbinder unbinder;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_receitas, container, false);

        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    public static RecipesFragment newInstance() {
        return new RecipesFragment();
    }

    @OnClick(R.id.btn_recipes_add)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), ReceitaActivity.class));
    }
}
