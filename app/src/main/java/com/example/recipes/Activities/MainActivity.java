package com.example.recipes.Activities;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.example.recipes.Fragments.CategoriesFragment;
import com.example.recipes.Fragments.RecipesFragment;
import com.example.recipes.Fragments.homeFragment;
import com.example.recipes.R;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView navigationView;


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
            switch (item.getItemId()) {
                case R.id.navigation_home:{
                    Fragment homefragment = homeFragment.newInstance();
                    openFragment(homefragment);
                    break;
                }

                case R.id.navigation_recipes:{
                    Fragment recipesFragment = RecipesFragment.newInstance();
                    openFragment(recipesFragment);
                    break;
                }

                case R.id.navigation_category:{
                    Fragment categoryFragment = CategoriesFragment.newInstance();
                    openFragment(categoryFragment);
                    break;
                }
            }
            return false;
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setOnNavigationItemSelectedListener(this);

        Fragment homefragment = homeFragment.newInstance();
        openFragment(homefragment);
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
