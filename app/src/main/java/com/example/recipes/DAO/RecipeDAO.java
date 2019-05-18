package com.example.recipes.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.recipes.Database.DBHelper;
import com.example.recipes.Models.Recipe;

import java.util.ArrayList;

public class RecipeDAO {
    private SQLiteDatabase db;
    private DBHelper database;

    public static String TABLE = "recipes";
    public static String ID = "id";
    public static String CATEGORY_ID = "category_id";
    public static String NAME = "name";
    public static String TIME = "time";
    public static String YIELD = "yield";
    public static String INGREDIENTS = "ingredients";
    public static String METHOD_OF_PREPARATION = "method_of_preparation";
    public static String IS_FAVORITE = "is_favorite";
    public static String IMAGE_NAME = "image_name";
    public static String URL = "url";

    public RecipeDAO (Context context) {
        database = new DBHelper(context);
    }

    public ArrayList<Recipe> listFavorites () {
        db = database.getReadableDatabase();

        String where = IS_FAVORITE + "=" + 1;
        Cursor cursor = db.query(TABLE, new String[] { ID, CATEGORY_ID, NAME, TIME, YIELD, INGREDIENTS, METHOD_OF_PREPARATION, IS_FAVORITE, IMAGE_NAME, URL }, where, null,null,null,null,null);
        ArrayList<Recipe> result = new ArrayList<>();

        if (cursor.moveToFirst()){
            do {
                Recipe recipe = new Recipe();
                recipe.setId(cursor.getLong(cursor.getColumnIndex(ID)));
                recipe.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                recipe.setCategoryId(cursor.getLong(cursor.getColumnIndex(CATEGORY_ID)));
                recipe.setTime(cursor.getInt(cursor.getColumnIndex(TIME)));
                recipe.setYield(cursor.getInt(cursor.getColumnIndex(YIELD)));
                recipe.setIngredients(cursor.getString(cursor.getColumnIndex(INGREDIENTS)));
                recipe.setMethodOfPreparation(cursor.getString(cursor.getColumnIndex(METHOD_OF_PREPARATION)));
                recipe.setIsFavorite(cursor.getInt(cursor.getColumnIndex(IS_FAVORITE)) == 1);
                recipe.setImageName(cursor.getString(cursor.getColumnIndex(IMAGE_NAME)));
                recipe.setUrl(cursor.getString(cursor.getColumnIndex(URL)));

                result.add(recipe);
            } while (cursor.moveToNext());
        }

        db.close();
        cursor.close();

        return result;
    }

    public ArrayList<Recipe> getRecipeByCategoryId(Long id) {
        db = database.getReadableDatabase();

        String where = CATEGORY_ID + "=" + id;
        Cursor cursor = db.query(TABLE, new String[] { ID, CATEGORY_ID, NAME, TIME, YIELD, INGREDIENTS, METHOD_OF_PREPARATION, IS_FAVORITE, IMAGE_NAME, URL }, where, null,null,null,null,null);
        ArrayList<Recipe> result = new ArrayList<>();

        if (cursor.moveToFirst()){
            do {
                Recipe recipe = new Recipe();
                recipe.setId(cursor.getLong(cursor.getColumnIndex(ID)));
                recipe.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                recipe.setCategoryId(cursor.getLong(cursor.getColumnIndex(CATEGORY_ID)));
                recipe.setTime(cursor.getInt(cursor.getColumnIndex(TIME)));
                recipe.setYield(cursor.getInt(cursor.getColumnIndex(YIELD)));
                recipe.setIngredients(cursor.getString(cursor.getColumnIndex(INGREDIENTS)));
                recipe.setMethodOfPreparation(cursor.getString(cursor.getColumnIndex(METHOD_OF_PREPARATION)));
                recipe.setIsFavorite(cursor.getInt(cursor.getColumnIndex(IS_FAVORITE)) == 1);
                recipe.setImageName(cursor.getString(cursor.getColumnIndex(IMAGE_NAME)));
                recipe.setUrl(cursor.getString(cursor.getColumnIndex(URL)));

                result.add(recipe);
            } while (cursor.moveToNext());
        }

        db.close();
        cursor.close();

        return result;
    }

    public ArrayList<Recipe> list () {
        db = database.getReadableDatabase();

        Cursor cursor = db.query(TABLE, new String[] { ID, CATEGORY_ID, NAME, TIME, YIELD, INGREDIENTS, METHOD_OF_PREPARATION, IS_FAVORITE, IMAGE_NAME, URL }, null,null,null,null,null);
        ArrayList<Recipe> result = new ArrayList<>();

        if (cursor.moveToFirst()){
            do {
                Recipe recipe = new Recipe();
                recipe.setId(cursor.getLong(cursor.getColumnIndex(ID)));
                recipe.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                recipe.setCategoryId(cursor.getLong(cursor.getColumnIndex(CATEGORY_ID)));
                recipe.setTime(cursor.getInt(cursor.getColumnIndex(TIME)));
                recipe.setYield(cursor.getInt(cursor.getColumnIndex(YIELD)));
                recipe.setIngredients(cursor.getString(cursor.getColumnIndex(INGREDIENTS)));
                recipe.setMethodOfPreparation(cursor.getString(cursor.getColumnIndex(METHOD_OF_PREPARATION)));
                recipe.setIsFavorite(cursor.getInt(cursor.getColumnIndex(IS_FAVORITE)) == 1);
                recipe.setImageName(cursor.getString(cursor.getColumnIndex(IMAGE_NAME)));
                recipe.setUrl(cursor.getString(cursor.getColumnIndex(URL)));

                result.add(recipe);
            } while (cursor.moveToNext());
        }

        db.close();
        cursor.close();

        return result;
    }

    public long create (Recipe recipe) {

        ContentValues values = new ContentValues();
        db = database.getWritableDatabase();

        values.put(CATEGORY_ID, recipe.getCategoryId());
        values.put(NAME, recipe.getName());
        values.put(TIME, recipe.getTime());
        values.put(YIELD, recipe.getYield());
        values.put(INGREDIENTS, recipe.getIngredients());
        values.put(METHOD_OF_PREPARATION, recipe.getMethodOfPreparation());
        values.put(IS_FAVORITE, recipe.getIsFavorite());
        values.put(IMAGE_NAME, recipe.getImageName());
        values.put(URL, recipe.getUrl());

        long result = db.insert(TABLE, null, values);

        db.close();

        return result;
    }

    public int update (Recipe recipe) {

        ContentValues values = new ContentValues();
        db = database.getWritableDatabase();

        String where = ID + "=" + recipe.getId();

        values.put(CATEGORY_ID, recipe.getCategoryId());
        values.put(NAME, recipe.getName());
        values.put(TIME, recipe.getTime());
        values.put(YIELD, recipe.getYield());
        values.put(INGREDIENTS, recipe.getIngredients());
        values.put(METHOD_OF_PREPARATION, recipe.getMethodOfPreparation());
        values.put(IS_FAVORITE, recipe.getIsFavorite());
        values.put(IMAGE_NAME, recipe.getImageName());
        values.put(URL, recipe.getUrl());

        int response = db.update(TABLE, values, where,null);

        db.close();

        return response;
    }

    public Boolean remove (Long id) {

        db = database.getWritableDatabase();
        String where = ID + "=" + id;

        Boolean response = db.delete(TABLE, where, null) > 0;
        db.close();

        return response;
    }

    public Recipe get (Long id) {
        String where = ID + "=" + id;
        db = database.getReadableDatabase();
        Cursor cursor = db.query(TABLE, new String[] { ID, CATEGORY_ID, NAME, TIME, YIELD, INGREDIENTS, METHOD_OF_PREPARATION, IS_FAVORITE, IMAGE_NAME, URL }, where, null,null,null,null,null);

        if (cursor.moveToFirst()) {
            Recipe recipe = new Recipe();
            recipe.setId(cursor.getLong(cursor.getColumnIndex(ID)));
            recipe.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            recipe.setCategoryId(cursor.getLong(cursor.getColumnIndex(CATEGORY_ID)));
            recipe.setTime(cursor.getInt(cursor.getColumnIndex(TIME)));
            recipe.setYield(cursor.getInt(cursor.getColumnIndex(YIELD)));
            recipe.setIngredients(cursor.getString(cursor.getColumnIndex(INGREDIENTS)));
            recipe.setMethodOfPreparation(cursor.getString(cursor.getColumnIndex(METHOD_OF_PREPARATION)));
            recipe.setIsFavorite(cursor.getInt(cursor.getColumnIndex(IS_FAVORITE)) == 1);
            recipe.setImageName(cursor.getString(cursor.getColumnIndex(IMAGE_NAME)));
            recipe.setUrl(cursor.getString(cursor.getColumnIndex(URL)));

            db.close();

            return recipe;
        }

        db.close();

        return null;
    }
}
