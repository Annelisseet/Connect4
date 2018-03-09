package com.example.annelisse.connect4;


class Connect4Contador {
    private int player1Ganadas = 0;
    private int player2Ganadas = 0;

    void player1Gano(){
        player1Ganadas++;
    }

    void player2Gano(){
        player2Ganadas++;
    }

    int getPlayer1Ganadas() {
        return player1Ganadas;
    }

    int getPlayer2Ganads() {
        return player2Ganadas;
    }
}
