package com.example.recipes.Fragments;

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


import com.example.recipes.Activities.CategoryActivity;
import com.example.recipes.Adapters.CategoryAdapterItens;
import com.example.recipes.DAO.CategoryDAO;
import com.example.recipes.Models.Category;
import com.example.recipes.R;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CategoriesFragment extends Fragment {
    Unbinder unbinder;
    private CategoryAdapterItens categoryAdapterItens;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.categoryAdapterItens = new CategoryAdapterItens(getContext(), new ArrayList<Category>());
        RecicleViewCategory();
    }

    public static CategoriesFragment newInstance() {
        return new CategoriesFragment();
    }

    @OnClick(R.id.btn_add_category)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), CategoryActivity.class));
    }

    public void RecicleViewCategory(){
        RecyclerView recycleViewCategorias = getActivity().findViewById(R.id.recycleview_category);
        LinearLayoutManager linearLayoutManagerCategorias = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        recycleViewCategorias.setLayoutManager(linearLayoutManagerCategorias);
        recycleViewCategorias.setAdapter(categoryAdapterItens);
    }

    public void getAllCategory(){
        CategoryDAO categoryDAO = new CategoryDAO(getContext());
        this.categoryAdapterItens.attItens(categoryDAO.list());
    }

    @Override
    public void onResume() {
        super.onResume();
        getAllCategory();
    }
}