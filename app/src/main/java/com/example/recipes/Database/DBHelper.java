package com.example.recipes.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.recipes.DAO.CategoryDAO;
import com.example.recipes.DAO.RecipeDAO;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "recipes_db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + CategoryDAO.TABLE + " ( " + CategoryDAO.ID + " integer primary key autoincrement, "  + CategoryDAO.NAME + " varchar(100) not null, " + CategoryDAO.DESCRIPTION + " varchar(100));");
        db.execSQL("create table " + RecipeDAO.TABLE + " ( " + RecipeDAO.ID + " integer primary key autoincrement, " + RecipeDAO.CATEGORY_ID + " integer not null, " + RecipeDAO.NAME + " varchar(100) not null, " + RecipeDAO.TIME + " integer, " + RecipeDAO.YIELD + " integer, " + RecipeDAO.INGREDIENTS + " varchar(1000), " + RecipeDAO.METHOD_OF_PREPARATION + " varchar(1000), " + RecipeDAO.IS_FAVORITE + " int default 0, " + RecipeDAO.IMAGE_NAME + " varchar(200), " + RecipeDAO.URL +  " varchar(2000) );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
