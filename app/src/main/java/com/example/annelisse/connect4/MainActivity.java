package com.example.annelisse.connect4;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
    Connect4Contador contador;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        juego = new Connect4Game();
        contador = new Connect4Contador();
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        gridView = findViewById(R.id.myGridViewComponent);
        gridView.setOnItemClickListener(this);
        setAdapter(gridView);
        findViewById(R.id.botonReiniciar).setOnClickListener((v) -> {
            juego = new Connect4Game();
            setAdapter(gridView);
            gridView.setEnabled(true);
        });

    }

    private void setAdapter(GridView gridView) {
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
            setBackground(position, btn);
        }
        if (juego.juegoHaTerminado()) {
            terminarJuego();
        }

    }

    private void setBackground(int position, TextView btn) {
        int background = juego.esTurnoPlayer1() ? R.drawable.round_button_yellow : R.drawable.round_button_red;
        btn.setBackgroundResource(background);
        juego.jugar(position);
    }

    private void terminarJuego() {
        gridView.setEnabled(false);
        actualizarContador();
        mensajeGanador();
        ((TextView) findViewById(R.id.contadorPlayer1)).setText(contador.getPlayer1Ganadas() + "");
        ((TextView) findViewById(R.id.contadorPlayer2)).setText(contador.getPlayer2Ganads() + "");
    }

    private void actualizarContador() {
        if (juego.currentPlayerEsPlayer1()) {
            contador.player2Gano();
        } else {
            contador.player1Gano();
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
