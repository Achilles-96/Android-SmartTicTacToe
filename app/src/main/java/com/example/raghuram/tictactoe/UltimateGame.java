package com.example.raghuram.tictactoe;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class UltimateGame extends ActionBarActivity implements View.OnClickListener {

    private int[] map_of_open_moves = new int[82];
    private int turn = 1;
    private static final int BLOCKED = 0;
    private static final int OPEN = 1;
    private static final int PLAYER1 = 2;
    private static final int PLAYER2 = 3;
    private int game_state = 1;
    private int markers[] = new int[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultimate_game);
        for (int i = 1; i <= 81; i++) {
            setOpen(i);
        }
        for (int i = 1; i <= 81; i++) {
            int id = getResources().getIdentifier("button" + i, "id", getPackageName());
            View view = findViewById(id);
            view.setAlpha(1);
            view.setOnClickListener(this);
        }
        for(int i = 1;i<=9;i++)
            markers[i] = -1;
    }

    public void onClick(View view) {
        String IdAsString = view.getResources().getResourceName(view.getId());
        int id = Integer.parseInt(IdAsString.substring(40));
        if (map_of_open_moves[id] != OPEN || game_state == 0 || markers[((id-1)/9)+1] != -1) {
            return;
        }
        if (turn == 1) {
            ((Button) view).setText("X");
            turn = 2;
            map_of_open_moves[id] = PLAYER1;
        } else {
            ((Button) view).setText("0");
            map_of_open_moves[id] = PLAYER2;
            turn = 1;
        }
        int boardStatus = checkWin();
        Log.d("board status",boardStatus+"");
        if(boardStatus != -1){
            game_state = 0;
            return;
        }
        updateMap(id);
        updateAlphas();
    }

    private int checkWin() {

        int start, end;
        for(int i = 1;i<=9;i++){
            start = i*9 - 8;
            end = start + 9 - 1;
            markers[i] = checkBlock(start, end);
            Log.d("Marker" + i, markers[i]+"");
        }
        start = 1;
        end = 9;
        for(int i=start;i<end;i+=3)
            if(markers[i] == PLAYER1 &&
                    markers[i + 1] == PLAYER1 &&
                    markers[i + 2] == PLAYER1
                    )
                return PLAYER1;
        for(int i=start;i<end;i+=3)
            if(markers[i] == PLAYER2 &&
                    markers[i + 1] == PLAYER2 &&
                    markers[i + 2] == PLAYER2
                    )
                return PLAYER2;
        for (int i = start; i <= start + 2; i++)
            if (markers[i] == PLAYER1 &&
                    markers[i+3] == PLAYER1 &&
                    markers[i+6] == PLAYER1)
                return PLAYER1;
        for (int i = start; i <= start + 2; i++)
            if (markers[i] == PLAYER2 &&
                    markers[i+3] == PLAYER2 &&
                    markers[i+6] == PLAYER2)
                return PLAYER2;
        if(markers[start] == PLAYER1 &&
                markers[start + 4] == PLAYER1 &&
                markers[start + 8] == PLAYER1)
            return PLAYER1;
        if(markers[start] == PLAYER2 &&
                markers[start + 4] == PLAYER2 &&
                markers[start + 8] == PLAYER2)
            return PLAYER2;
        if(markers[start + 2] == PLAYER1 &&
                markers[start + 4] == PLAYER1 &&
                markers[start + 6] == PLAYER1)
            return PLAYER1;
        if(markers[start + 2] == PLAYER2 &&
                markers[start + 4] == PLAYER2 &&
                markers[start + 6] == PLAYER2)
            return PLAYER2;
        return -1;
    }

    private int checkBlock(int start, int end) {
        for(int i=start;i<end;i+=3)
            if(map_of_open_moves[i] == PLAYER1 &&
                    map_of_open_moves[i + 1] == PLAYER1 &&
                    map_of_open_moves[i + 2] == PLAYER1
                    )
                return PLAYER1;
        for(int i=start;i<end;i+=3)
            if(map_of_open_moves[i] == PLAYER2 &&
                    map_of_open_moves[i + 1] == PLAYER2 &&
                    map_of_open_moves[i + 2] == PLAYER2
                    )
                return PLAYER2;
        for (int i = start; i <= start + 2; i++)
            if (map_of_open_moves[i] == PLAYER1 &&
                    map_of_open_moves[i+3] == PLAYER1 &&
                    map_of_open_moves[i+6] == PLAYER1)
                return PLAYER1;
        for (int i = start; i <= start + 2; i++)
            if (map_of_open_moves[i] == PLAYER2 &&
                    map_of_open_moves[i+3] == PLAYER2 &&
                    map_of_open_moves[i+6] == PLAYER2)
                return PLAYER2;
        if(map_of_open_moves[start] == PLAYER1 &&
                map_of_open_moves[start + 4] == PLAYER1 &&
                map_of_open_moves[start + 8] == PLAYER1)
            return PLAYER1;
        if(map_of_open_moves[start] == PLAYER2 &&
                map_of_open_moves[start + 4] == PLAYER2 && 
                map_of_open_moves[start + 8] == PLAYER2)
            return PLAYER2;
        if(map_of_open_moves[start + 2] == PLAYER1 &&
                map_of_open_moves[start + 4] == PLAYER1 &&
                map_of_open_moves[start + 6] == PLAYER1)
            return PLAYER1;
        if(map_of_open_moves[start + 2] == PLAYER2 &&
                map_of_open_moves[start + 4] == PLAYER2 &&
                map_of_open_moves[start + 6] == PLAYER2)
            return PLAYER2;
        return -1;
    }

    private void setOpen(int id) {
        if (map_of_open_moves[id] != PLAYER1 && map_of_open_moves[id] != PLAYER2)
            map_of_open_moves[id] = OPEN;
    }

    private void updateMap(int id) {
        for (int i = 1; i <= 81; i++) {
            if (map_of_open_moves[i] == OPEN)
                map_of_open_moves[i] = BLOCKED;
        }
        switch (id % 9) {
            case 1:
                if(markers[2]!=-1 && markers[4]!=-1)
                    openAll();
                else
                    for (int i = 10, j = 28; i <= 18; i++, j++) {
                        if(markers[2]==-1)
                            setOpen(i);
                        if(markers[4]==-1)
                            setOpen(j);
                    }
                break;
            case 2:
                if(markers[1]!=-1 && markers[3]!=-1)
                    openAll();
                else
                    for (int i = 1, j = 19; i <= 9; i++, j++) {
                        if(markers[1]==-1)
                            setOpen(i);
                        if(markers[3]==-1)
                            setOpen(j);
                    }
                break;
            case 3:
                if(markers[2]!=-1 && markers[6]!=-1)
                    openAll();
                else
                    for (int i = 10, j = 46; i <= 18; i++, j++) {
                        if(markers[2]==-1)
                            setOpen(i);
                        if(markers[6]==-1)
                            setOpen(j);
                    }
                break;
            case 4:
                if(markers[1]!=-1 && markers[7]!=-1)
                    openAll();
                else
                    for (int i = 1, j = 55; i <= 9; i++, j++) {
                        if(markers[1]==-1)
                            setOpen(i);
                        if(markers[7]==-1)
                            setOpen(j);
                    }
                break;
            case 5:
                if(markers[5]!=-1)
                    openAll();
                else
                    for (int i = 37; i <= 45; i++) {
                        setOpen(i);
                    }
                break;
            case 6:
                if(markers[3]!=-1 && markers[9]!=-1)
                    openAll();
                else
                    for (int i = 19, j = 73; i <= 27; i++, j++) {
                        if(markers[3]==-1)
                            setOpen(i);
                        if(markers[9]==-1)
                            setOpen(j);
                    }
                break;
            case 7:
                if(markers[4]!=-1 && markers[8]!=-1)
                    openAll();
                else
                    for (int i = 28, j = 64; i <= 36; i++, j++) {
                        if(markers[4]==-1)
                            setOpen(i);
                        if(markers[8]==-1)
                            setOpen(j);
                    }
                break;
            case 8:
                if(markers[7]!=-1 && markers[9]!=-1)
                    openAll();
                else
                    for (int i = 55, j = 73; i <= 63; i++, j++) {
                        if(markers[7]==-1)
                            setOpen(i);
                        if(markers[9]==-1)
                            setOpen(j);
                    }
                break;
            case 0:
                if(markers[6]!=-1 && markers[8]!=-1)
                    openAll();
                else
                    for (int i = 46, j = 64; i <= 54; i++, j++) {
                        if(markers[6]==-1)
                            setOpen(i);
                        if(markers[8]==-1)
                            setOpen(j);
                    }
                break;
        }
    }

    private void openAll(){
        for(int i=1;i<=81;i++){
            int markerid = ((i-1)/9)+1;
            if(markers[markerid]==-1)
                setOpen(i);
        }
    }

    private void updateAlphas() {
        for (int i = 1; i <= 81; i++) {
            int id = getResources().getIdentifier("button" + i, "id", getPackageName());
            View view = findViewById(id);
            switch (map_of_open_moves[i]) {
                case BLOCKED:
                    view.setAlpha((float) 0.7);
                    break;
                case OPEN:
                    view.setAlpha((float) 1);
                    break;
                case PLAYER1:
                case PLAYER2:
                    view.setAlpha((float) 0.4);
                    break;
            }
        }
    }
}
