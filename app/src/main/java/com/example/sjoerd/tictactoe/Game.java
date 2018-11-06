package com.example.sjoerd.tictactoe;

public class Game {

    final private int BOARD_SIZE = 3;
    private TileState[][] board;

    private Boolean playerOneTurn; // true if player one turn, false if player two turn
    private int movesPlayed;
    private Boolean gameOver;

    // constructor
    public Game() {
        board = new TileState[BOARD_SIZE][BOARD_SIZE];
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = TileState.BLANK;
            }
        }

        playerOneTurn = true;
        gameOver = false;
    }

    // TODO write choose function
    public TileState choose(int row, int column) {
        return TileState.INVALID;
    }

    public GameState won() {
        // TODO implement won function
    }

}
