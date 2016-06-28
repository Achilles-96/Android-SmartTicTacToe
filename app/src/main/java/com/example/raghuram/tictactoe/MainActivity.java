package com.example.raghuram.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startNormalGame(View view) {
        Intent intent = new Intent(this, NormalGame.class);
        startActivity(intent);
    }

    public void startUltimateGame(View view) {
        Intent intent = new Intent(this, PlayerOptions.class);
        startActivity(intent);
    }

}
