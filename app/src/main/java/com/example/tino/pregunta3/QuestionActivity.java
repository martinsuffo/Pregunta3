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

import com.example.tino.pregunta3.dao.PreguntaDAO;
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
    private Random rand;
    private String categoria;
    PreguntaDAO preguntaDAO;

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

        preguntaDAO = PreguntaDAO.getInstance();
        ordenar();
        mostrarPregunta();
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
        Pregunta pregunta;
        ArrayList<Pregunta> preguntas;

        categoria = getIntent().getExtras().getString("categoria", "");
        preguntas = preguntaDAO.list(categoria, this);

        Log.i("preguntas size ",""+preguntas.size());

        pregunta = preguntas.get(rand.nextInt(preguntas.size()));

        tvCategoria   = (TextView)    findViewById(R.id.labelCategoria);
        tvPregunta    = (TextView)    findViewById(R.id.labelPregunta);

        tvCategoria.setText(categoria);
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
