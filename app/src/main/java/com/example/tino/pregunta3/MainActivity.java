package com.example.tino.pregunta3;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Intent questionActivity;
    private TextView lbHistoria;
    private TextView lbCiencia;
    private TextView lbDeporte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.btnGirar);

        button.setOnClickListener( (View v) -> {
                try {
                    questionActivity = new Intent(v.getContext(), QuestionActivity.class);
                    girar();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        );
    }
    public void girar() throws InterruptedException {
        button.setOnClickListener((View v)->{});

        Random r = new Random();
        int categoria;
        int vueltas;
        int velocidad;

        categoria = r.nextInt(3);
        velocidad = (1000 / 3);

        questionActivity.putExtra("categoria", categoria);

            switch (categoria){
                case 0:
                    lbHistoria = (TextView) findViewById(R.id.labelHistoria);
                    lbHistoria.setTextSize(22);
                    lbHistoria.setTypeface(Typeface.DEFAULT_BOLD);
                    break;
                case 1:
                    lbCiencia  = (TextView) findViewById(R.id.labelCiencia);
                    lbCiencia.setTextSize(22);
                    lbCiencia.setTypeface(Typeface.DEFAULT_BOLD);
                    break;
                case 2:
                    lbDeporte  = (TextView) findViewById(R.id.labelDeporte);
                    lbDeporte.setTextSize(22);
                    lbDeporte.setTypeface(Typeface.DEFAULT_BOLD);
                    break;
            }

        Thread thread = new Thread(
                () -> {
                    try {
                        Thread.sleep(3000);
                        startActivity(questionActivity);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
        );
        thread.start();
    }

}
