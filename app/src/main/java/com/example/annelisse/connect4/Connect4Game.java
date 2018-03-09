package com.example.annelisse.connect4;

/**
 * Created by ariel on 3/9/18.
 */

public class Connect4Game {

    static final String PLAYER_1 = "player1";
    static final String PLAYER_2 = "player2";
    private final int fil = 6;
    private final int col = 7;
    private String currentPlayer;
    private String[] posiciones; //

    Connect4Game() {
        currentPlayer = PLAYER_1;
        posiciones = new String[fil * col];
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    void jugar(int pos) {
        if (pos < posiciones.length && posiciones[pos] == null) {
            posiciones[pos] = currentPlayer;
            currentPlayer = esTurnoPlayer1() ? PLAYER_2 : PLAYER_1;
        }
    }

    public boolean posicionLibre(int pos) {
        return !(pos > posiciones.length) && posiciones[pos] == null;
    }

    boolean esTurnoPlayer1() {
        return currentPlayer.equals(PLAYER_1);
    }

    public boolean juegoHaTerminado() {
        return siUnJugadorHaGanado() || elJuegoEstaTrancado();
    }

    private boolean elJuegoEstaTrancado() {
        return false;
    }

    private boolean siUnJugadorHaGanado() {
        return siHaGanadoHorizontal();
    }

    private boolean siHaGanadoHorizontal() {

        return false;
    }
}
