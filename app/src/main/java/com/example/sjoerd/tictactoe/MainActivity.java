package com.example.sjoerd.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Game game;
    int[] tiles = {R.id.tile00, R.id.tile01, R.id.tile02, R.id.tile10, R.id.tile11, R.id.tile12,
                   R.id.tile20, R.id.tile21, R.id.tile22};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create game
        game = new Game();
    }

    public void tileClicked(View view) {

        int row = 0;
        int column = 0;

        // get id of clicked tile
        int id = view.getId();

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
                // TODO update UI
                break;
            case CIRCLE:
                // TODO update UI
                break;
            case INVALID:
                break;
        }

        // check if game is won and update UI
        GameState won = game.won();

        if (won == GameState.DRAW) {
            // TODO show message Draw
        }
        else if (won == GameState.PLAYER_ONE) {
            // TODO show message player one wins
        }
        else if (won == GameState.PLAYER_TWO) {
            // TODO show message player two wins
        }
    }

    public void resetClicked() {

        // TODO reset UI
        for (int i = 0; i < tiles.length; i++) {
            // TODO clear tiles
        }

        game = new Game();
    }
}
