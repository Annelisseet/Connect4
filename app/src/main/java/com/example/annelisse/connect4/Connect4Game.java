package com.example.annelisse.connect4;

class Connect4Game {

    private static final String PLAYER_1 = "player1";
    private static final String PLAYER_2 = "player2";
    private final int fil = 6;
    private final int col = 7;
    private String currentPlayer;
    private String[] posiciones; //

    Connect4Game() {
        currentPlayer = PLAYER_1;
        posiciones = new String[fil * col];
    }

    boolean currentPlayerEsPlayer1() {
        return currentPlayer.equals(PLAYER_1);
    }

    void jugar(int pos) {
        if (pos < posiciones.length && posiciones[pos] == null) {
            posiciones[pos] = currentPlayer;
            currentPlayer = esTurnoPlayer1() ? PLAYER_2 : PLAYER_1;
        }
    }

    boolean posicionLibre(int pos) {
        return !(pos > posiciones.length) && posiciones[pos] == null;
    }

    boolean esTurnoPlayer1() {
        return currentPlayer.equals(PLAYER_1);
    }

    boolean juegoHaTerminado() {
        return siUnJugadorHaGanado() || elJuegoEstaTrancado();
    }

    private boolean elJuegoEstaTrancado() {
        return false;
    }

    private boolean siUnJugadorHaGanado() {
        return siHaGanadoHorizontal(PLAYER_1)
                || siHaGanadoHorizontal(PLAYER_2)
                || haGanadoVertical(PLAYER_1)
                || haGanadoVertical(PLAYER_2);
    }

    private boolean siHaGanadoHorizontal(String player) {
        int cont = 0;
        for (int i = 0; i < fil; i++) {
            for (int j = 0; j < col && cont < 4; j++) {
                int pos = (i * col) + j;
                if (player.equals(posiciones[pos])) {
                    cont++;
                } else {
                    cont = 0;
                }
            }
            if (cont == 4) {
                return true;
            } else {
                cont = 0;
            }
        }
        return false;
    }

    private boolean haGanadoVertical(String player) {
        int cont = 0;
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < fil && cont < 4; j++) {
                int pos = ((i + fil ) * j ) + 1;
                if (player.equals(posiciones[pos])) {
                    cont++;
                } else {
                    cont = 0;
                }
            }
            if (cont == 4) {
                return true;
            } else {
                cont = 0;

            }
        }
        return false;
    }
}
