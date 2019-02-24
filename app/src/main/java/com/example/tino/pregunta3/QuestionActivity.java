package com.example.tino.pregunta3;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.tino.pregunta3.model.Pregunta;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class QuestionActivity extends AppCompatActivity {
    private Button   button;
    private Intent   mainActivity;
    private TextView tvCategoria;
    private TextView tvPregunta;
    private RadioGroup rgRespuestas;
    private ArrayList<RadioButton> respuestas = new ArrayList<RadioButton>();
    private ArrayList<Pregunta>    preguntas  = new ArrayList<Pregunta>();
    private Random rand;
    private int    categoria;
    private String nombreCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2);
        button = (Button) findViewById(R.id.btnEnviar);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    mainActivity = new Intent(v.getContext(), MainActivity.class);
                    comprobar();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        categoria = getIntent().getIntExtra("categoria", 0);

        cargarPreguntas(categoria);
        ordenar();
        mostrarPregunta();
    }

    private void cargarPreguntas(int categoria){
        switch (categoria){
            case 0:
                preguntas.add(new Pregunta("¿Cuál es la rama mayoritaria del Islam?","Sunismo","Chiísmo",
                            "Jariyismo","Sufismo"));
                preguntas.add(new Pregunta(" ¿Cuál es la ciudad más antigua de América Latina?","Caral","La Paz",
                        "Valparaíso","Arequipa"));
                preguntas.add(new Pregunta("¿Quién pronunció la frase: \"Bebamos de la copa de la destrucción\"?","Gengis Kan",
                        "Mussolini","Berlusconi","Margaret Tatcher"));
                preguntas.add(new Pregunta("El Renacimiento marcó el inicio de la Edad...","Moderna","Antigua clásica",
                        "Contemporánea","Media"));
                preguntas.add(new Pregunta("¿Cuántos siglos duró el Siglo de Oro?","Dos","Uno","Tres",
                        "Medio"));

                nombreCategoria = "Historia";
                break;
            case 1:
                preguntas.add(new Pregunta("¿Cuánto es 4+2?","6","7","5","Uf no sé m3n"));
                preguntas.add(new Pregunta("¿Cuál de las siguientes enfermedades ataca al higado?","Hepatitis",
                        "Diabetes","Artrósis","Cifoescoliosis"));
                preguntas.add(new Pregunta("¿Cómo se consume la sustancia alucinógena natural llamada ayahuasca?","Ingerida",
                        "Vía tacto","Inyectada","Inhalada"));
                preguntas.add(new Pregunta(" ¿Cuál es la función principal del instestino grueso?","Absorción de agua",
                        "Absorción de nutrientes","Digestión mecánica","Digestión química"));
                preguntas.add(new Pregunta("¿Qué cambio de estado ocurre en la sublimación?","De sólido a gaseoso",
                        "De sólido a líquido","De líquido a gaseoso","De gaseoso a líquido"));

                nombreCategoria="Ciencia";
                break;
            case 2:
                preguntas.add(new Pregunta("¿Cuántas finales del mundo jugó la Selección Argentina de fútbol?","Cuatro",
                        "Tres","Seis","Cinco"));
                preguntas.add(new Pregunta(" ¿Cuántos mangos por lado tiene el futbolín?","Cuatro",
                        "Dos","Tres","Cinco"));
                preguntas.add(new Pregunta("¿Qué selección acumula mayor cantidad de expulsados en  mundiales de fútbol?","Argentina",
                        "Brasil","Italia","Camerún"));
                preguntas.add(new Pregunta("¿Quién inventó el arte marcial llamado Jeet Kune Do?","Bruce Lee",
                        "David Carradine","Chuck Norris","Ninguna de las anteriores"));
                preguntas.add(new Pregunta("¿Cuántos puntos vale un tiro libre encestado en baloncesto?","Uno","Dos",
                        "Tres","Depende"));

                nombreCategoria="Deporte";
                break;
        }
    }

    private void ordenar(){
        rand = new Random();
        int aux;
        int[] buttons = new int[]{R.id.rbPrimerRespuesta,R.id.rbSegundaRespuesta,R.id.rbTercerRespuesta,R.id.rbCuartaRespuesta};

        aux = rand.nextInt(4);
        respuestas.add((RadioButton) findViewById(buttons[aux]));
        buttons = removeElement(buttons, aux);

        aux = rand.nextInt(3);
        respuestas.add((RadioButton) findViewById(buttons[aux]));
        buttons = removeElement(buttons, aux);

        aux = rand.nextInt(2);
        respuestas.add((RadioButton) findViewById(buttons[aux]));
        buttons = removeElement(buttons, aux);

        respuestas.add((RadioButton) findViewById(buttons[0]));
    }

    private void mostrarPregunta() {

        Pregunta pregunta = preguntas.get(rand.nextInt(preguntas.size()));
        tvCategoria   = (TextView)    findViewById(R.id.labelCategoria);
        tvPregunta    = (TextView)    findViewById(R.id.labelPregunta);

        tvCategoria.setText(nombreCategoria);
        tvPregunta.setText(pregunta.getPregunta());

        respuestas.get(0).setText(pregunta.getrCorrecta());
        respuestas.get(1).setText(pregunta.getrIncorrecta1());
        respuestas.get(2).setText(pregunta.getrIncorrecta2());
        respuestas.get(3).setText(pregunta.getrIncorrecta3());

    }

    private void comprobar(){
        rgRespuestas = (RadioGroup) findViewById(R.id.rgRespuestas);
        RadioButton checked = (RadioButton) findViewById(rgRespuestas.getCheckedRadioButtonId());
        if(checked != null) {
            button.setOnClickListener((View v)->{});
            if (rgRespuestas.getCheckedRadioButtonId() == respuestas.get(0).getId()) {
                respuestas.get(0).setBackgroundColor(Color.GREEN);
                respuestas.get(0).setTypeface(Typeface.DEFAULT_BOLD);
            } else {
                respuestas.get(0).setBackgroundColor(Color.YELLOW);
                respuestas.get(0).setTypeface(Typeface.DEFAULT_BOLD);
                checked.setBackgroundColor(Color.RED);
                checked.setTypeface(Typeface.DEFAULT_BOLD);
            }

            Thread thread = new Thread(
                    () -> {
                        try {
                            Thread.sleep(3000);
                            startActivity(mainActivity);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
            );
            thread.start();
        }
    }

    private int[] removeElement(int[] array, int index){
        int[] nArray = new int[array.length-1];
        int added = 0;
        for(int i=0; i<array.length; i++){
            if(i != index) {
                nArray[added] = array[i];
                added++;
            }
        }
        return nArray;
    }
}
