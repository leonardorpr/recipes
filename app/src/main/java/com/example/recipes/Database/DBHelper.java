package com.example.recipes.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.recipes.DAO.CategoryDAO;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "recipes_db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + CategoryDAO.TABLE + " ( " + CategoryDAO.ID + " integer primary key autoincrement, "  + CategoryDAO.NAME + " varchar(100) not null, " + CategoryDAO.DESCRIPTION + " varchar(100));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
