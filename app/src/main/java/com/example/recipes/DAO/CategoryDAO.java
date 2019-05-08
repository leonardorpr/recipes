package com.example.recipes.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.recipes.Database.DBHelper;
import com.example.recipes.Models.Category;

import java.util.ArrayList;

public class CategoryDAO {
    private DBHelper database;
    private SQLiteDatabase db;

    public static String TABLE = "categories";
    public static String NAME = "name";
    public static String DESCRIPTION = "description";
    public static String ID = "id";

    public CategoryDAO (Context context) {
        database = new DBHelper(context);
    }

    public Category getByName (String name) {

        String where = NAME + " = '" + name + "' ";
        db = database.getReadableDatabase();
        Cursor cursor = db.query(TABLE,new String[] { ID, NAME, DESCRIPTION }, where ,null,null,null,null);

        if (cursor.moveToFirst()) {
            Category category = new Category();
            category.setId(cursor.getLong(cursor.getColumnIndex(ID)));
            category.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            category.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));

            db.close();

            return category;
        }

        db.close();
        return null;
    }

    public ArrayList<Category> list () {
        db = database.getReadableDatabase();

        Cursor cursor = db.query(TABLE, new String[] { ID, NAME, DESCRIPTION }, null,null,null,null,null);
        ArrayList<Category> result = new ArrayList<>();

        if (cursor.moveToFirst()){
            do {
                Category category = new Category();
                category.setId(cursor.getLong(cursor.getColumnIndex(ID)));
                category.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                category.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));

                result.add(category);
            } while (cursor.moveToNext());
        }

        db.close();
        cursor.close();

        return result;
    }

    public long create (Category category) {
        ContentValues values = new ContentValues();
        db = database.getWritableDatabase();

        values.put(NAME, category.getName());
        values.put(DESCRIPTION, category.getDescription());

        long result = db.insert(TABLE, null, values);

        db.close();

        if (result == -1)
            return -1;
        else
            return result;
    }

    public int update (Category category) {
        ContentValues values = new ContentValues();
        db = database.getWritableDatabase();

        String where = ID + "=" + category.getId();

        values.put(NAME, category.getName());
        values.put(DESCRIPTION, category.getDescription());

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

    public Category get (Long id) {

        String where = ID + "=" + id;
        db = database.getReadableDatabase();
        Cursor cursor = db.query(TABLE,new String[] {ID, NAME, DESCRIPTION }, where,null,null,null,null);

        if(cursor.moveToFirst()) {
            Category category = new Category();
            category.setId(cursor.getLong(cursor.getColumnIndex(ID)));
            category.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            category.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));

            db.close();

            return category;
        }

        db.close();

        return null;
    }
}
