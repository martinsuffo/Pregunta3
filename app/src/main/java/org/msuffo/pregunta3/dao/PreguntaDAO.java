package org.msuffo.pregunta3.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.msuffo.pregunta3.config.AdminSQLiteOpenHelper;
import org.msuffo.pregunta3.model.Pregunta;

import java.util.ArrayList;

public class PreguntaDAO {
    private static PreguntaDAO preguntaDAO = null;

    public ArrayList<Pregunta> list(String categoria, Context context){
        ArrayList<Pregunta> list;
        Cursor c;
        String sql;

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context);
        SQLiteDatabase db = admin.getReadableDatabase();

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
