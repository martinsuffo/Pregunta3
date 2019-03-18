package com.example.tino.pregunta3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class Ganaste extends Activity {

    private Intent mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_ganaste);
    }

    public void reiniciar(View v){
        mainActivity = new Intent(this, MainActivity.class);
        startActivity(mainActivity);
    }
}
