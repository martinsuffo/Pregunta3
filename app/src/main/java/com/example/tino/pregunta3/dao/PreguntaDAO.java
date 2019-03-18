package com.example.tino.pregunta3.dao;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.tino.pregunta3.config.AdminSQLiteOpenHelper;
import com.example.tino.pregunta3.model.Pregunta;

import java.util.ArrayList;

public class PreguntaDAO {
    private static PreguntaDAO preguntaDAO = null;

    public ArrayList<Pregunta> list(String categoria, Context context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context);
        SQLiteDatabase        db    = admin.getReadableDatabase();
        ArrayList<Pregunta> list;
        Cursor c;
        String sql ="";


        sql = "SELECT * FROM "+ categoria +";";

        c = db.rawQuery(sql, null);


        list = new ArrayList<>();
        if(c.moveToFirst()){
            do {
                list.add(new Pregunta(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4)));
            }while (c.moveToNext());
        }

        return list;
    }

    private void instert(Pregunta pregunta, String categoria){
    }

    private PreguntaDAO(){}
    public static PreguntaDAO getInstance(){
        if(preguntaDAO == null)
            preguntaDAO = new PreguntaDAO();
        return preguntaDAO;
    }
}
