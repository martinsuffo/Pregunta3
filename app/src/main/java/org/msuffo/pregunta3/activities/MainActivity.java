package org.msuffo.pregunta3.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import org.msuffo.pregunta3.R;
import org.msuffo.pregunta3.components.Header;

public class MainActivity extends Activity {

    private Header header;
    private Intent ruletaActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
    }

    public void elegirVidas(View view){
        Button button = findViewById(view.getId());
        int vidas = Integer.parseInt((String) button.getText());

        header = Header.getInstance();
        header.setVidas(vidas);
        header.trofeos = null;

        ruletaActivity = new Intent(view.getContext(), RuletaActivity.class);
        startActivity(ruletaActivity);

        (findViewById(R.id.btnVidas1)).setOnClickListener((View v)->{});
        (findViewById(R.id.btnVidas2)).setOnClickListener((View v)->{});
        (findViewById(R.id.btnVidas3)).setOnClickListener((View v)->{});
    }

    @Override
    public void onBackPressed()
    {}
}
