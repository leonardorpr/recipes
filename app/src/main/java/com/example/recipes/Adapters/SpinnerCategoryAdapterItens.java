package com.example.recipes.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.recipes.Models.Category;
import com.example.recipes.R;

import java.util.ArrayList;

public class SpinnerCategoryAdapterItens extends ArrayAdapter<Category> {

    public SpinnerCategoryAdapterItens(Context context, ArrayList<Category> categories) {
        super(context, 0, categories);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        return itemView(position, view, parent);
    }

    private View itemView(int position, View view, ViewGroup parent){
        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.spinner_category_item, parent, false);
        }

        TextView nameCategory = view.findViewById(R.id.spinner_category_name);
        Category item = getItem(position);

        if(item != null){
            nameCategory.setText(item.getName());
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        return itemView(position, view, parent);
    }
}
