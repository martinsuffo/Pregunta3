package com.example.tino.pregunta3;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tino.pregunta3.dao.CategoriaDAO;
import com.example.tino.pregunta3.model.Categoria;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Intent questionActivity;
    private TextView lbHistoria;
    private TextView lbCiencia;
    private TextView lbDeporte;
    CategoriaDAO categoriaDAO;

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

        ArrayList<Categoria> categorias;
        Random r = new Random();
        String categoria;
        int    id;
        Resources res = getResources();
        TextView  label;

        categoriaDAO = CategoriaDAO.getInstance();

        categorias = categoriaDAO.list(this);

        categoria = categorias.get(r.nextInt(categorias.size())).getNombre();
        id = res.getIdentifier("label" + categoria, "id", this.getPackageName());
        label  = (TextView) findViewById(id);

        label.setTextSize(22);
        label.setTypeface(Typeface.DEFAULT_BOLD);

        questionActivity.putExtra("categoria", categoria);

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
