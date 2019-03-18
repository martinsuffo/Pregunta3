package com.example.tino.pregunta3.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.tino.pregunta3.config.AdminSQLiteOpenHelper;
import com.example.tino.pregunta3.model.Categoria;

import java.util.ArrayList;

public class CategoriaDAO {
    private static CategoriaDAO categoriaDAO = null;

    public ArrayList<Categoria> list(Context context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context);
        SQLiteDatabase db    = admin.getReadableDatabase();
        ArrayList<Categoria> list;
        String sql = "select name from sqlite_master where type = \"table\" and name != \"android_metadata\";";

        Cursor c = db.rawQuery(sql, null);
        list = new ArrayList<>();

        if(c.moveToFirst()){
            do {
                list.add(new Categoria(c.getString(0)));
            }while (c.moveToNext());
        }
        return list;
    }

    public int count(Context context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context);
        SQLiteDatabase db    = admin.getReadableDatabase();
        ArrayList<Categoria> list;
        String sql = "select count(*) from sqlite_master where type = \"table\" and name != \"android_metadata\";";

        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        int count = c.getInt(0);

        c.close();

        return count;
    }

    private CategoriaDAO(){}
    public static CategoriaDAO getInstance(){
        if(categoriaDAO == null)
            categoriaDAO = new CategoriaDAO();
        return categoriaDAO;
    }
}
