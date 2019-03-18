package org.msuffo.pregunta3.config;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import org.msuffo.pregunta3.model.Pregunta;

import java.util.ArrayList;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    private ArrayList<Pregunta> historia,deporte,ciencia;

    public AdminSQLiteOpenHelper(Context context) {
        super(context, "preguntas", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table historia(pregunta text UNIQUE NOT NULL, rcorrecta text NOT NULL, rincorrecta1 text NOT NULL," +
                "rincorrecta2 text NOT NULL, rincorrecta3 text NOT NULL);");
        db.execSQL("create table ciencia(pregunta text UNIQUE NOT NULL, rcorrecta text NOT NULL, rincorrecta1 text NOT NULL," +
                "rincorrecta2 text NOT NULL, rincorrecta3 text NOT NULL);");
        db.execSQL("create table deporte(pregunta text UNIQUE NOT NULL, rcorrecta text NOT NULL, rincorrecta1 text NOT NULL," +
                "rincorrecta2 text NOT NULL, rincorrecta3 text NOT NULL);");

        cargarBD(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int version1, int version2) {
        db.execSQL("drop table if exists historia");
        db.execSQL("create table historia(pregunta text UNIQUE NOT NULL, rcorrecta text NOT NULL, rincorrecta1 text NOT NULL," +
                "rincorrecta2 text NOT NULL, rincorrecta3 text NOT NULL)");

        db.execSQL("drop table if exists ciencia");
        db.execSQL("create table ciencia(pregunta text UNIQUE NOT NULL, rcorrecta text NOT NULL, rincorrecta1 text NOT NULL," +
                "rincorrecta2 text NOT NULL, rincorrecta3 text NOT NULL)");

        db.execSQL("drop table if exists deporte");
        db.execSQL("create table deporte(pregunta text UNIQUE NOT NULL, rcorrecta text NOT NULL, rincorrecta1 text NOT NULL," +
                "rincorrecta2 text NOT NULL, rincorrecta3 text NOT NULL)");
    }

    private void cargarBD(SQLiteDatabase bd){
        cargarArray();
        String sql;

        for(int i=0; i<historia.size();i++){
            sql = "INSERT INTO historia VALUES('"+ historia.get(i).getPregunta() +"','"+ historia.get(i).getrCorrecta()
                    + "','"+ historia.get(i).getrIncorrecta1() +"','"+ historia.get(i).getrIncorrecta2() +"','"
                    + historia.get(i).getrIncorrecta3() +"');";
            bd.execSQL(sql);
        }
        for(int i=0; i<ciencia.size();i++){
            sql = "INSERT INTO ciencia VALUES('"+ ciencia.get(i).getPregunta() +"','"+ ciencia.get(i).getrCorrecta()
                    + "','"+ ciencia.get(i).getrIncorrecta1() +"','"+ ciencia.get(i).getrIncorrecta2() +"','"
                    + ciencia.get(i).getrIncorrecta3() +"');";
            bd.execSQL(sql);
        }
        for(int i=0; i<deporte.size();i++){
            sql = "INSERT INTO deporte VALUES('"+ deporte.get(i).getPregunta() +"','"+ deporte.get(i).getrCorrecta()
                    + "','"+ deporte.get(i).getrIncorrecta1() +"','"+ deporte.get(i).getrIncorrecta2() +"','"
                    + deporte.get(i).getrIncorrecta3() +"');";
            bd.execSQL(sql);
        }
    }

    private void cargarArray() {

        historia = new ArrayList<>();
        historia.add(new Pregunta("¿Cuál es la rama mayoritaria del Islam?","Sunismo","Chiísmo", "Jariyismo","Sufismo"));
        historia.add(new Pregunta(" ¿Cuál es la ciudad más antigua de América Latina?","Caral","La Paz", "Valparaíso","Arequipa"));
        historia.add(new Pregunta("¿Quién pronunció la frase: \"Bebamos de la copa de la destrucción\"?","Gengis Kan", "Mussolini","Berlusconi","Margaret Tatcher"));
        historia.add(new Pregunta("El Renacimiento marcó el inicio de la Edad...","Moderna","Antigua clásica", "Contemporánea","Media"));
        historia.add(new Pregunta("¿Cuántos siglos duró el Siglo de Oro?","Dos","Uno","Tres", "Medio"));

        ciencia = new ArrayList<>();
        ciencia.add(new Pregunta("¿Cuánto es 4+2?","6","7","5","Uf no sé m3n"));
        ciencia.add(new Pregunta("¿Cuál de las siguientes enfermedades ataca al higado?","Hepatitis", "Diabetes","Artrósis","Cifoescoliosis"));
        ciencia.add(new Pregunta("¿Cómo se consume la sustancia alucinógena natural llamada ayahuasca?","Ingerida", "Vía tacto","Inyectada","Inhalada"));
        ciencia.add(new Pregunta(" ¿Cuál es la función principal del instestino grueso?","Absorción de agua", "Absorción de nutrientes","Digestión mecánica","Digestión química"));
        ciencia.add(new Pregunta("¿Qué cambio de estado ocurre en la sublimación?","De sólido a gaseoso", "De sólido a líquido","De líquido a gaseoso","De gaseoso a líquido"));

        deporte = new ArrayList<>();
        deporte.add(new Pregunta("¿Cuántas finales del mundo jugó la Selección Argentina de fútbol?","Cuatro", "Tres","Seis","Cinco"));
        deporte.add(new Pregunta(" ¿Cuántos mangos por lado tiene el futbolín?","Cuatro", "Dos","Tres","Cinco"));
        deporte.add(new Pregunta("¿Qué selección acumula mayor cantidad de expulsados en  mundiales de fútbol?","Argentina", "Brasil","Italia","Camerún"));
        deporte.add(new Pregunta("¿Quién inventó el arte marcial llamado Jeet Kune Do?","Bruce Lee", "David Carradine","Chuck Norris","Ninguna de las anteriores"));
        deporte.add(new Pregunta("¿Cuántos puntos vale un tiro libre encestado en baloncesto?","Uno","Dos","Tres","Depende"));
    }
}
