package com.example.raghuram.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PlayerOptions extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_options);
    }

    public void startSinglePlayer(View view) {
        Intent intent = new Intent(this, UltimateGame.class);
        intent.putExtra("Game mode",0);
        startActivity(intent);
    }

    public void startMultiPlayer(View view) {
        Intent intent = new Intent(this, UltimateGame.class);
        intent.putExtra("Game mode",1);
        startActivity(intent);
    }
}
