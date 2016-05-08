package com.example.raghuram.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

public class MainActivity extends ActionBarActivity {

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
        Intent intent = new Intent(this, UltimateGame.class);
        startActivity(intent);
    }

}
