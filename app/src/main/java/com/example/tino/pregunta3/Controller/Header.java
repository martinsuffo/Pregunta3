package com.example.tino.pregunta3.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.tino.pregunta3.R;
import com.example.tino.pregunta3.dao.CategoriaDAO;

import java.util.ArrayList;

public class Header{
    private static Header header = null;

    public ArrayList<String> trofeos;
    private Context context;
    private Intent fin;
    private LayoutInflater inflater;
    private LinearLayout lltrofeos;
    private LinearLayout llVidas;
    Resources res;
    private int corazonID;
    public int meta;
    public int vidas;
    private CategoriaDAO categoriaDAO;

    public void actualizar(){
        RelativeLayout resHeader;
        for(int i=0;i<vidas;i++){
            resHeader = (RelativeLayout) inflater.inflate(R.layout.res_header_layout, null);
            ImageView vidaImg = (ImageView) resHeader.getChildAt(0);
            vidaImg.setImageResource(corazonID);

            llVidas.addView(resHeader);
        }
        if(trofeos != null){
            for(int i=0;i<trofeos.size();i++){
                resHeader = (RelativeLayout) inflater.inflate(R.layout.res_header_layout, null);
                ImageView trofeoImg = (ImageView) resHeader.getChildAt(0);

                int iTrofeo = res.getIdentifier(trofeos.get(i), "drawable", context.getPackageName());
                trofeoImg.setImageResource(iTrofeo);

                lltrofeos.addView(resHeader);
            }
        }
    }

    public void restarVida(){
        vidas--;
    }

    public void agregarTrofeo(String trofeo){
        boolean obtenido = false;
        if(trofeos == null)
            trofeos = new ArrayList<>();
        else
            for(int i=0;i<trofeos.size();i++){
                if(trofeos.get(i).equals(trofeo)) {
                    obtenido = true;
                }
            }
        if(!obtenido) {
            trofeos.add(trofeo);
        }

    }

    private Header(){
    }
    public static Header getInstance(){
        if(header == null)
            header = new Header();
        return header;
    }

    public void setContext(Context context){
        this.context = context;
        if(res == null) {
            res = context.getResources();
            corazonID = res.getIdentifier("vida", "drawable", context.getPackageName());

            categoriaDAO = CategoriaDAO.getInstance();
            meta = categoriaDAO.count(context);
        }
        inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        llVidas = (LinearLayout) ((Activity)context).findViewById(R.id.tvVidas);
        lltrofeos = (LinearLayout) ((Activity)context).findViewById(R.id.llTrofeos);
    }
    public void setVidas(int vidas) {
        this.vidas = vidas;
    }
}
