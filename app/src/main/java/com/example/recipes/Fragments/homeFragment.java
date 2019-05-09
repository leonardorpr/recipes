package com.example.recipes.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recipes.R;

public class homeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState){
        return inflater.inflate(R.layout.fragment_home, container,false);
    }

    public static homeFragment newInstance(){
        return new homeFragment();
    }
}
