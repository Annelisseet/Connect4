package com.example.annelisse.connect4;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ArrayAdapter<String> adapter;
    Connect4Game juego;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        juego = new Connect4Game();
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        findViewById(R.id.botonReiniciar).setOnClickListener((v) ->
                startActivity(new Intent(this, MainActivity.class))
        );

        GridView gridView = findViewById(R.id.myGridViewComponent);
        gridView.setOnItemClickListener(this);

        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 42; i++) {
            stringList.add(" ");
        }
        adapter = new ArrayAdapter<>(this, R.layout.connect_text_view, stringList);
        gridView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView btn = (TextView) adapter.getView(position, view, parent);
        if (juego.posicionLibre(position)) {
            int background = juego.esTurnoPlayer1() ? R.drawable.round_button_yellow : R.drawable.round_button_red;
            btn.setBackgroundResource(background);
            juego.jugar(position);
        }
        if (juego.juegoHaTerminado()) {
            mensajeGanador();
        }

    }

    public void mensajeGanador() {
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

// 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage("Ha ganado el juego")
                .setTitle("Juego terminado");

// 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
