package com.example.annelisse.connect4;

class Connect4Game {

    private static final String PLAYER_1 = "player1";
    private static final String PLAYER_2 = "player2";
    private final int totalFilas = 6;
    private final int totalCol = 7;
    private String currentPlayer;
    private String[] posiciones; //

    Connect4Game() {
        currentPlayer = PLAYER_1;
        posiciones = new String[totalFilas * totalCol];
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
                || siHaGanadoVertical(PLAYER_1)
                || siHaGanadoVertical(PLAYER_2)
                || siHaGanadoDiagonal(PLAYER_1)
                || siHaGanadoDiagonal(PLAYER_2);
    }

    private boolean siHaGanadoHorizontal(String player) {
        int cont = 0;
        for (int i = 0; i < totalFilas; i++) {
            for (int j = 0; j < totalCol && cont < 4; j++) {
                int pos = (i * totalCol) + j;
                if (siPosicionHaSidoJugadaPor(pos, player)) {
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

    private boolean siHaGanadoVertical(String player) {
        int cont = 0;
        for (int i = 0; i < totalCol; i++) {
            for (int j = 0; j < totalFilas && cont < 4; j++) {
                int pos = (j * totalCol) + i;
                if (siPosicionHaSidoJugadaPor(pos, player)) {
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

    private boolean siPosicionHaSidoJugadaPor(int pos, String player) {
        return pos < posiciones.length && player.equals(posiciones[pos]);
    }


    private boolean siHaGanadoDiagonal(String player) {
        return siHaGanadoDiagonalIzquierdaDerecha(player)
                || siHaGanadoDiagonalDerechaIzquierda(player);
    }


    private boolean siHaGanadoDiagonalIzquierdaDerecha(String player) {
        return siHaGanadoDiagonalIzquierdaDerechaArriba(player)
                || siHaGanadoDiagonalIzquierdaDerechaAbajo(player);
    }

    private boolean siHaGanadoDiagonalIzquierdaDerechaArriba(String player) {
        int cont = 0;
        for (int i = 0; i < 3; i++) {
            for (int col = 0; col < 4; col++) {
                int pos = col + (i * totalCol);
                for (int fil = i; fil < totalFilas && (fil + (col-i) <= totalFilas) && cont < 4; fil++) {
                    if (siPosicionHaSidoJugadaPor(pos, player)) {
                        cont++;
                    } else {
                        cont = 0;
                    }
                    pos += totalCol + 1;
                }
                if (cont == 4) {
                    return true;
                } else {
                    cont = 0;

                }
            }

        }
        return false;
    }

    private boolean siHaGanadoDiagonalIzquierdaDerechaAbajo(String player) {
        int cont = 0;
        for (int i = 0; i < 3; i++) {
            for (int col = 0; col < 4; col++) {
                int pos = 35 + col - (totalCol * i);
                for (int fil = i; fil < totalFilas && (fil + (col-i) <= totalFilas) && cont < 4; fil++) {
                    if (siPosicionHaSidoJugadaPor(pos, player)) {
                        cont++;
                    } else {
                        cont = 0;
                    }
                    pos -= totalFilas;
                }
                if (cont == 4) {
                    return true;
                } else {
                    cont = 0;

                }
            }

        }
        return false;
    }

    private boolean siHaGanadoDiagonalDerechaIzquierda(String player) {
        return siHaGanadoDiagonalDerechaIzquierdaArriba(player)
                || siHaGanadoDiagonalDerechaIzquierdaAbajo(player);
    }

    private boolean siHaGanadoDiagonalDerechaIzquierdaArriba(String player) {
        int cont = 0;
        for (int i = 0; i < 3; i++) {
            for (int col = 0; col < 4; col++) {
                int pos = (totalFilas - col) + totalCol * i;
                for (int fil = i; fil < totalFilas && (fil + (col-i) <= totalFilas) && cont < 4; fil++) {
                    if (siPosicionHaSidoJugadaPor(pos, player)) {
                        cont++;
                    } else {
                        cont = 0;
                    }
                    pos += totalFilas;
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

    private boolean siHaGanadoDiagonalDerechaIzquierdaAbajo(String player) {
        int cont = 0;
        for (int i = 0; i < 3; i++) {
            for (int col = 0; col < 4; col++) {
                int pos = ((totalFilas * totalCol - 1) - col) - (totalCol * i);
                for (int fil = i; fil < totalFilas && (fil + (col-i) <= totalFilas) && cont < 4; fil++) {
                    if (siPosicionHaSidoJugadaPor(pos, player)) {
                        cont++;
                    } else {
                        cont = 0;
                    }
                    pos -= totalCol + 1;
                }
                if (cont == 4) {
                    return true;
                } else {
                    cont = 0;
                }
            }
        }
        return false;
    }
}
