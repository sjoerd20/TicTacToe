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
        movesPlayed = 0;
    }

    // TODO write choose function
    public TileState choose(int row, int column) {
        if (board[row][column] == TileState.BLANK) {
            if (playerOneTurn) {

                // update player turn
                playerOneTurn = false;

                // update board
                board[row][column] = TileState.CROSS;
                movesPlayed++;

                return TileState.CROSS;
            }
            else {

                // update player turn
                playerOneTurn = true;

                // update board
                board[row][column] = TileState.CIRCLE;
                movesPlayed++;

                return TileState.CIRCLE;
            }
        }
        else {
            return TileState.INVALID;
        }
    }

    public GameState won() {
        // TODO implement won function

        // check if player one has won
        if (playerOneTurn) {

            // check if three same signs are on rows or columns
            for (int i = 0; i < BOARD_SIZE; i++) {
                if (board[i][0] == TileState.CROSS && board[i][1] == TileState.CROSS && board[i][2] == TileState.CROSS) {
                    return GameState.PLAYER_ONE;
                }
                if (board[0][i] == TileState.CROSS && board[1][i] == TileState.CROSS && board[2][i] == TileState.CROSS) {
                    return GameState.PLAYER_ONE;
                }
            }

            // check diagonal
            if (board[0][0] == TileState.CROSS && board[1][1] == TileState.CROSS && board[2][2] == TileState.CROSS) {
                return GameState.PLAYER_ONE;
            }
            if (board[0][2] == TileState.CROSS && board[1][1] == TileState.CROSS && board[2][0] == TileState.CROSS) {
                return GameState.PLAYER_ONE;
            }
        }

        // check if player two has won
        else if (!playerOneTurn) {
            // check if three same signs are on rows or columns
            for (int i = 0; i < BOARD_SIZE; i++) {
                if (board[i][0] == TileState.CIRCLE && board[i][1] == TileState.CIRCLE && board[i][2] == TileState.CIRCLE) {
                    return GameState.PLAYER_TWO;
                }
                if (board[0][i] == TileState.CIRCLE && board[1][i] == TileState.CIRCLE && board[2][i] == TileState.CIRCLE) {
                    return GameState.PLAYER_TWO;
                }
            }

            // check diagonal
            if (board[0][0] == TileState.CIRCLE && board[1][1] == TileState.CIRCLE && board[2][2] == TileState.CIRCLE) {
                return GameState.PLAYER_TWO;
            }
            if (board[0][2] == TileState.CIRCLE && board[1][1] == TileState.CIRCLE && board[2][0] == TileState.CIRCLE) {
                return GameState.PLAYER_TWO;
            }
        }

        // return DRAW if all moves are played and no player has won
        if (movesPlayed == 9) {
            return GameState.DRAW;
        }

        return GameState.IN_PROGRESS;
    }
}
