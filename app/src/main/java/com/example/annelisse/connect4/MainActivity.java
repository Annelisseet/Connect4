package com.example.annelisse.connect4;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView textView = (TextView) adapter.getView(position, view, parent);
        if (juego.posicionLibre(position)) {
            setBackground(position, textView);
            juego.jugar(position);
        }
        if (juego.juegoHaTerminado()) {
            terminarJuego();
        }

    }

    private void setAdapter(GridView gridView) {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 42; i++) {
            stringList.add("");
        }
        adapter = new ArrayAdapter<>(this, R.layout.connect_text_view, stringList);
        gridView.setAdapter(adapter);
    }

    private void setBackground(int position, TextView btn) {
        int background = juego.esTurnoPlayer1() ? R.drawable.round_button_yellow : R.drawable.round_button_red;
        btn.setBackgroundResource(background);
    }

    private void terminarJuego() {
        gridView.setEnabled(false);
        actualizarContador();
        mensajeJuegoTerminado();
    }

    private void actualizarContador() {
        if (juego.elJuegoEstaTrancado()) {
            return;
        }
        if (juego.currentPlayerEsPlayer1()) {
            contador.player2Gano();
        } else {
            contador.player1Gano();
        }
        ((TextView) findViewById(R.id.contadorPlayer1))
                .setText(getString(R.string.blank, contador.getPlayer1Ganadas()));
        ((TextView) findViewById(R.id.contadorPlayer2))
                .setText(getString(R.string.blank, contador.getPlayer2Ganads()));
    }

    public void mensajeJuegoTerminado() {
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setTitle(R.string.juego_terminado)
                .setPositiveButton(R.string.ok, (dialog, which) -> {
                });


        if (juego.elJuegoEstaTrancado()) {
            builder.setMessage(R.string.juego_trancado);
        } else {
            LayoutInflater inflater = this.getLayoutInflater();
            final ViewGroup nullParent = null;
            View dialogView = inflater.inflate(R.layout.juego_terminado, nullParent);
            builder.setView(dialogView);
            // 3. Get the AlertDialog from create()
            TextView textViewPlayer = dialogView.findViewById(R.id.juego_terminado_player);
            if (juego.currentPlayerEsPlayer1()) {
                textViewPlayer.setBackgroundResource(R.drawable.round_button_red);
            } else {
                textViewPlayer.setBackgroundResource(R.drawable.round_button_yellow);
            }
        }

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
