package com.example.sjoerd.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toast toastMessage;
    Game game;
    int[] tiles = {R.id.tile00, R.id.tile01, R.id.tile02, R.id.tile10, R.id.tile11, R.id.tile12,
                   R.id.tile20, R.id.tile21, R.id.tile22};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check for rotations and restore board
        if(savedInstanceState!=null)
        {
            game = (Game) savedInstanceState.getSerializable("game");
            TileState[][] board = game.getBoard();
            restoreTiles(board);
        }

        // else create new game
        else
            game = new Game();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("game", game);
    }

    public void restoreTiles(TileState[][] board) {
        for (int i = 0; i < game.getBoardSize(); i++) {
            for (int j = 0; j < game.getBoardSize(); j++) {

                // find button by using i + j*3
                Button button = findViewById(tiles[i + j*3]);

                // set tile text according to save
                switch(board[i][j]) {
                    case CROSS:
                        button.setText("X");
                        break;
                    case CIRCLE:
                        button.setText("O");
                        break;
                    case BLANK:
                        button.setText("");
                }
            }
        }
    }

    public void tileClicked(View view) {

        int row = 0;
        int column = 0;

        // get id of clicked tile
        int id = view.getId();

        // get button from ID
        Button button = findViewById(id);

        // find row and column of button
        for(int i = 0; i < tiles.length; i ++) {
            if (tiles[i] == id) {
                row = i / 3;
                column = i % 3;
            }
        }

        TileState state = game.choose(row, column);

        switch(state) {
            case CROSS:
                button.setText("X");
                break;
            case CIRCLE:
                button.setText("O");
                break;
            case INVALID:
                break;
        }

        // check if game is won and update UI
        GameState won = game.won();

        if (won == GameState.DRAW) {
            placeToast("Draw!");
        }
        else if (won == GameState.PLAYER_ONE) {
            placeToast("Player 1 won!");
        }
        else if (won == GameState.PLAYER_TWO) {
            placeToast("Player 2 won!");
        }
    }

    // places a toast with a message
    private void placeToast(String message) {
        toastMessage = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toastMessage.show();
    }

    public void resetClicked(View view) {

        // remove toast
        if (toastMessage != null) {
            toastMessage.cancel();
            toastMessage = null;
        }

        // reset UI to blank
        for (int i = 0; i < tiles.length; i++) {
            Button button = findViewById(tiles[i]);
            button.setText("");
            game.setBlank();

            game = new Game();
        }
    }
}
