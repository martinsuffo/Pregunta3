package org.msuffo.pregunta3.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.msuffo.pregunta3.R;
import org.msuffo.pregunta3.components.Header;
import org.msuffo.pregunta3.dao.PreguntaDAO;
import org.msuffo.pregunta3.model.Pregunta;

import java.util.ArrayList;
import java.util.Random;

public class QuestionActivity extends Activity {
    private ArrayList<Button> respuestas;
    private Header header;
    private Intent actSiguiente;
    private Random rand;
    private String categoria;
    private TextView tvCategoria;
    private TextView tvPregunta;

    private PreguntaDAO preguntaDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_question);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.header);

        header = Header.getInstance();
        header.setContext(this);
        header.actualizar();

        preguntaDAO = PreguntaDAO.getInstance();
        ordenar();
        mostrarPregunta();
    }

    private void ordenar(){
        rand = new Random();
        int aux;
        respuestas = new ArrayList<>();
        int[] buttons = new int[]{R.id.bPrimerRespuesta,R.id.bSegundaRespuesta,R.id.bTercerRespuesta,R.id.bCuartaRespuesta};

        aux = rand.nextInt(4);
        respuestas.add((Button) findViewById(buttons[aux]));
        buttons = removeElement(buttons, aux);

        aux = rand.nextInt(3);
        respuestas.add((Button) findViewById(buttons[aux]));
        buttons = removeElement(buttons, aux);

        aux = rand.nextInt(2);
        respuestas.add((Button) findViewById(buttons[aux]));
        buttons = removeElement(buttons, aux);

        respuestas.add((Button) findViewById(buttons[0]));
    }

    private void mostrarPregunta() {
        Pregunta pregunta;
        ArrayList<Pregunta> preguntas;

        categoria = getIntent().getExtras().getString("categoria", "");
        preguntas = preguntaDAO.list(categoria, this);

        pregunta = preguntas.get(rand.nextInt(preguntas.size()));

        tvCategoria   = (TextView)    findViewById(R.id.labelCategoria);
        tvPregunta    = (TextView)    findViewById(R.id.labelPregunta);
        ImageView ivCategoria = (ImageView) findViewById(R.id.ivCategoria);

        Resources res = this.getResources();

        ivCategoria.setImageResource(res.getIdentifier(categoria, "drawable", this.getPackageName()));
        tvCategoria.setText(categoria.substring(0,1).toUpperCase() + categoria.substring(1));
        tvPregunta.setText(pregunta.getPregunta());

        respuestas.get(0).setText(pregunta.getrCorrecta());
        respuestas.get(1).setText(pregunta.getrIncorrecta1());
        respuestas.get(2).setText(pregunta.getrIncorrecta2());
        respuestas.get(3).setText(pregunta.getrIncorrecta3());
    }

    public void comprobar(View v){
        for(int i=0;i<respuestas.size();i++){
            (findViewById(respuestas.get(i).getId())).setOnClickListener((View vv)->{});
        }
        actSiguiente = new Intent(this, RuletaActivity.class);
        if(v.getId() == respuestas.get(0).getId()) {
            ((Button) respuestas.get(0)).setBackgroundResource(R.drawable.correcto);
            ((Button) respuestas.get(0)).setTypeface(Typeface.DEFAULT_BOLD);
            header.agregarTrofeo(categoria);
            if (header.trofeos.size() == header.meta) {
                actSiguiente = new Intent(this, GanasteActivity.class);
            }
        }
        else {
            ((Button) v).setBackgroundResource(R.drawable.incorrecto);
            ((Button) respuestas.get(0)).setBackgroundResource(R.drawable.correcto2);
            ((Button) respuestas.get(0)).setTypeface(Typeface.DEFAULT_BOLD);
            header.restarVida();
            if (header.vidas < 0) {
                actSiguiente = new Intent(this, PerdisteActivity.class);
            }
        }

        Thread thread = new Thread(
                () -> {
                    try {
                        Thread.sleep(2000);
                        startActivity(actSiguiente);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
        thread.start();
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

    @Override
    public void onBackPressed()
    {

    }
}
