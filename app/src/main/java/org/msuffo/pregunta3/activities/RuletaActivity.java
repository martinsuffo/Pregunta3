package org.msuffo.pregunta3.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.msuffo.pregunta3.R;
import org.msuffo.pregunta3.components.Header;
import org.msuffo.pregunta3.dao.CategoriaDAO;
import org.msuffo.pregunta3.model.Categoria;

import java.util.ArrayList;
import java.util.Random;

public class RuletaActivity extends Activity {

    private ArrayList<TextView> TVCategorias;
    private ArrayList<Categoria> categorias;
    private Button button;
    private CategoriaDAO categoriaDAO;
    private Intent questionActivity;
    private LinearLayout layoutRuleta;
    private Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_ruleta);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.header);

        Header header = Header.getInstance();
        header.setContext(this);
        header.actualizar();

        button = (Button) findViewById(R.id.btnGirar);
        layoutRuleta = (LinearLayout) findViewById(R.id.ruleta);

        insertarCategorias();

        questionActivity = new Intent(this, QuestionActivity.class);

        button.setOnClickListener( (View v) -> {
                try {
                    button.setOnClickListener((View vv)->{});
                    girar();
                } catch (Exception e) {
                     e.printStackTrace();
                }
            }
        );
    }

    private void insertarCategorias(){
        categoriaDAO = CategoriaDAO.getInstance();
        categorias = categoriaDAO.list(this);

        TVCategorias = new ArrayList<>();
        LinearLayout categoriaRow = new LinearLayout(this);

        ViewGroup.LayoutParams params       = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ViewGroup.LayoutParams rightMargin  = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ViewGroup.LayoutParams bottomMargin = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        ((LinearLayout.LayoutParams) rightMargin).rightMargin   = (int) this.getResources().getDisplayMetrics().density * 40;
        ((LinearLayout.LayoutParams) bottomMargin).bottomMargin = (int) this.getResources().getDisplayMetrics().density * 40;

        int mCols;
        res = getResources();

        mCols = 1;

        if(categorias.size() > 2 && categorias.size() <5)
            mCols = 2;
        else if (categorias.size() > 4)
            mCols = 3;

        for(int i=0; i<categorias.size(); i++) {
            LinearLayout categoria = (LinearLayout) getLayoutInflater().inflate(R.layout.categoria_layout, null);

            ImageView categoriaImg = (ImageView) categoria.getChildAt(0);
            TextView  categoriaTxt = (TextView)  categoria.getChildAt(1);
            TVCategorias.add(categoriaTxt);

            String   nCategoria = categorias.get(i).getNombre().substring(0,1).toUpperCase() + categorias.get(i).getNombre().substring(1);
            int iCategoria = res.getIdentifier(categorias.get(i).getNombre(), "drawable", this.getPackageName());
            categoriaImg.setImageResource(iCategoria);
            categoriaTxt.setText(nCategoria);

            categoriaRow.addView(categoria);

            if((i+1) % mCols == 0 || i == categorias.size()-1) {
                if(categorias.size()-1 > i)
                    categoria.setLayoutParams(bottomMargin);
                categoriaRow.setLayoutParams(params);
                layoutRuleta.addView(categoriaRow);
                categoriaRow = new LinearLayout(this);
            }
            else{
                if(categorias.size()-1 != i)
                    categoria.setLayoutParams(rightMargin);
            }

        }

    }

    public void girar(){
        Random r = new Random();
        String categoria;
        int    idCategoria;
        res = getResources();
        TextView  label;

        idCategoria = r.nextInt(categorias.size());
        categoria   = categorias.get(idCategoria).getNombre();

        label  = TVCategorias.get(idCategoria);
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

    @Override
    public void onBackPressed()
    {}

}
