package com.example.raghuram.tictactoe;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends ActionBarActivity {

    private int gamestate;
    int[][] game =new int[5][5];
    int[][] map =new int[5][5];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gamestate = 0;
        map[0][0]=R.id.r1;
        map[0][1]=R.id.r2;
        map[0][2]=R.id.r3;
        map[1][0]=R.id.r4;
        map[1][1]=R.id.r5;
        map[1][2]=R.id.r6;
        map[2][0]=R.id.r7;
        map[2][1]=R.id.r8;
        map[2][2]=R.id.r9;
    }

    public void startgame(View view){
        int[] x=new int[2];
        int[] y=new int[2];
        x[0]=0;x[1]=2;y[0]=0;y[1]=2;
        Random random=new Random();
        int xval=x[random.nextInt(2)];
        int yval=y[random.nextInt(2)];
        for(int i=0;i<=2;i++)
            for(int j=0;j<=2;j++) {
                game[i][j] = 0;
                ((Button)findViewById(map[i][j])).setTextSize(30);
                ((Button)findViewById(map[i][j])).setText("");
            }
        gamestate=1;
        int choice=Integer.parseInt(view.getTag().toString());
        if(choice==1) {
            game[xval][yval] = 1;
            ((Button)findViewById(map[xval][yval])).setText("O");
        }
    }
    public void func(View view){
        if(gamestate==0) return;
        int choice=Integer.parseInt(view.getTag().toString());
        int row=choice/10,col=choice%10;
        row--;col--;
        if(game[row][col]!=0) return;
        game[row][col]=2;
        ((Button)findViewById(map[row][col])).setText("X");
        if(checkcomplete()){

            gamestate=0;
            Toast.makeText(this,"GAME DRAW :)",Toast.LENGTH_SHORT).show();
            return;
        }
        if(checkwin()) return;
        if(blockwin()) return;
        if(fork()) return;
        if(blockfork()) return;
        if(centerfree()) return;
        if(markcorner()) return;
        if(checkempty()) return;
        if(checkside()) return;

    }

    private void move(int x,int y){
        game[x][y]=1;
        ((Button)findViewById(map[x][y])).setText("O");
        if(checkcomplete()){

            Toast.makeText(this,"GAME DRAW :)",Toast.LENGTH_SHORT).show();
            gamestate=0;
            return;
        }
    }

    private void win(){

        Log.d("Came:","Came to win");
        gamestate=0;
        Toast.makeText(this,"I WON",Toast.LENGTH_SHORT).show();
    }
    private boolean checkcomplete(){
        int state=0;
        for(int i=0;i<=2;i++)
            for(int j=0;j<=2;j++)
                if(game[i][j]==0)
                    state=1;
        if(state==0) return true;
        else return false;
    }

    private boolean checkwin(){
        int mine=0,blocked=0,x=0,y=0;
        for(int i=0;i<=2;i++){
            mine=0;blocked=0;
            for(int j=0;j<=2;j++){
                if(game[i][j]==1) mine++;
                if(game[i][j]==2) blocked++;
                if(game[i][j]==0) {x=i;y=j;}
            }
            if(mine==2&&blocked==0){
                move(x,y);
                win();
                return true;
            }
            mine=blocked=0;
            for(int j=0;j<=2;j++){
                if(game[j][i]==1) mine++;
                if(game[j][i]==2) blocked++;
                if(game[j][i]==0) {x=j;y=i;}
            }
            if(mine==2&&blocked==0){
                move(x,y);
                win();
                return true;
            }
        }
        mine=blocked=0;
        for(int i=0;i<=2;i++){
            if(game[i][i]==1) mine++;
            if(game[i][i]==2) blocked++;
            if(game[i][i]==0) {x=i;y=i;}
        }
        if(mine==2&&blocked==0){
            move(x,y);
            win();
            return true;
        }
        mine=blocked=0;
        for(int i=0,j=2;i<=2;i++,j--){
            if(game[i][j]==1) mine++;
            if(game[i][j]==2) blocked++;
            if(game[i][j]==0) {x=i;y=j;}
        }
        if(mine==2&&blocked==0){
            move(x,y);
            win();
            return true;
        }
        return false;
    }


    private boolean blockwin(){
        int mine=0,blocked=0,x=0,y=0;
        for(int i=0;i<=2;i++){
            mine=0;blocked=0;
            for(int j=0;j<=2;j++){
                if(game[i][j]==2) mine++;
                if(game[i][j]==1) blocked++;
                if(game[i][j]==0) {x=i;y=j;}
            }
            if(mine==2&&blocked==0){
                move(x, y);
                return true;
            }
            mine=blocked=0;
            for(int j=0;j<=2;j++){
                if(game[j][i]==2) mine++;
                if(game[j][i]==1) blocked++;
                if(game[j][i]==0) {x=j;y=i;}
            }
            if(mine==2&&blocked==0){
                move(x,y);
                return true;
            }
        }
        mine=blocked=0;
        for(int i=0;i<=2;i++){
            if(game[i][i]==2) mine++;
            if(game[i][i]==1) blocked++;
            if(game[i][i]==0) {x=i;y=i;}
        }
        if(mine==2&&blocked==0){
            move(x,y);
            return true;
        }
        mine=blocked=0;
        for(int i=0,j=2;i<=2;i++,j--){
            if(game[i][j]==2) mine++;
            if(game[i][j]==1) blocked++;
            if(game[i][j]==0) {x=i;y=j;}
        }
        if(mine==2&&blocked==0){
            move(x,y);
            return true;
        }
        return false;
    }

    private boolean fork(){
        int unblocked;
        for(int i=0;i<=2;i++)
            for(int j=0;j<=2;j++)
                if(game[i][j]==0) {
                    unblocked=0;
                    if((game[i][(j+1)%3]==0&&game[i][(j+2)%3]==1)||(game[i][(j+1)%3]==1&&game[i][(j+2)%3]==0))
                        unblocked++;
                    if((game[(i+1)%3][j]==0&&game[(i+2)%3][j]==1)||(game[(i+1)%3][j]==1&&game[(i+2)%3][j]==0))
                        unblocked++;
                    if(i==j)
                        if((game[(i+1)%3][(j+1)%3]==0&&game[(i+2)%3][(j+2)%3]==1)||(game[(i+1)%3][(j+1)%3]==1&&game[(i+2)%3][(j+2)%3]==0))
                            unblocked++;
                    if(i+j==2)
                        if((game[(i-1+3)%3][(j+1)%3]==0&&game[(i-2+3)%3][(j+2)%3]==1)||(game[(i-1+3)%3][(j+1)%3]==1&&game[(i-2+3)%3][(j+2)%3]==0))
                            unblocked++;
                    if(unblocked>=2){
                        move(i,j);
                        return true;
                    }
                }
        return false;
    }

    private boolean checkfork(int x,int y){
        int i=x,j=y;
        int unblocked=0;
        if((game[i][(j+1)%3]==0&&game[i][(j+2)%3]==1)||(game[i][(j+1)%3]==1&&game[i][(j+2)%3]==0))
            unblocked++;
        if((game[(i+1)%3][j]==0&&game[(i+2)%3][j]==1)||(game[(i+1)%3][j]==1&&game[(i+2)%3][j]==0))
            unblocked++;
        if(i==j)
            if((game[(i+1)%3][(j+1)%3]==0&&game[(i+2)%3][(j+2)%3]==1)||(game[(i+1)%3][(j+1)%3]==1&&game[(i+2)%3][(j+2)%3]==0))
                unblocked++;
        if(i+j==2)
            if((game[(i-1+3)%3][(j+1)%3]==0&&game[(i-2+3)%3][(j+2)%3]==1)||(game[(i-1+3)%3][(j+1)%3]==1&&game[(i-2+3)%3][(j+2)%3]==0))
                unblocked++;
        if(unblocked>=2)
            return false;
        else
            return true;

    }

    private boolean blockfork() {
        int unblocked;
        int[][] fork = new int[5][5];
        int count = 0;
        for (int i = 0; i <= 2; i++)
            for (int j = 0; j <= 2; j++)
                fork[i][j] = 0;
        for (int i = 0; i <= 2; i++)
            for (int j = 0; j <= 2; j++)
                if (game[i][j] == 0) {
                    unblocked = 0;
                    if ((game[i][(j + 1) % 3] == 0 && game[i][(j + 2) % 3] == 2) || (game[i][(j + 1) % 3] == 2 && game[i][(j + 2) % 3] == 0))
                        unblocked++;
                    if ((game[(i + 1) % 3][j] == 0 && game[(i + 2) % 3][j] == 2) || (game[(i + 1) % 3][j] == 2 && game[(i + 2) % 3][j] == 0))
                        unblocked++;
                    if (i == j)
                        if ((game[(i + 1) % 3][(j + 1) % 3] == 0 && game[(i + 2) % 3][(j + 2) % 3] == 2) || (game[(i + 1) % 3][(j + 1) % 3] == 2 && game[(i + 2) % 3][(j + 2) % 3] == 0))
                            unblocked++;
                    if (i + j == 2)
                        if ((game[(i - 1 + 3) % 3][(j + 1) % 3] == 0 && game[(i - 2 + 3) % 3][(j + 2) % 3] == 2) || (game[(i - 1 + 3) % 3][(j + 1) % 3] == 2 && game[(i - 2 + 3) % 3][(j + 2) % 3] == 0))
                            unblocked++;
                    if (unblocked >= 2) {
                        fork[i][j] = 1;
                        count++;
                    }
                }
        if (count == 0)
            return false;

        for (int i = 0; i <= 2; i++)
            for (int j = 0; j <= 2; j++)
                if (game[i][j] == 0) {
                    if (game[i][(j + 1) % 3] == 0 && game[i][(j + 2) % 3] == 1)
                        if (fork[i][(j + 1) % 3] == 0) {
                            move(i, j);
                            return true;
                        }
                    if (game[i][(j + 1) % 3] == 1 && game[i][(j + 2) % 3] == 0)
                        if (fork[i][(j + 2) % 3] == 0) {
                            move(i, j);
                            return true;
                        }
                    if (game[(i + 1) % 3][j] == 0 && game[(i + 2) % 3][j] == 1)
                        if (fork[(i + 1) % 3][j] == 0) {
                            move(i, j);
                            return true;
                        }
                    if (game[(i + 1) % 3][j] == 1 && game[(i + 2) % 3][j] == 0)
                        if (fork[(i + 2) % 3][j] == 0) {
                            move(i, j);
                            return true;
                        }
                    if (i == j) {
                        if (game[(i + 1) % 3][(j + 1) % 3] == 0 && game[(i + 2) % 3][(j + 2) % 3] == 1)
                            if (fork[(i + 1) % 3][(j + 1) % 3] == 0) {
                                move(i, j);
                                return true;
                            }
                        if (game[(i + 1) % 3][(j + 1) % 3] == 1 && game[(i + 2) % 3][(j + 2) % 3] == 0)
                            if (fork[(i + 2) % 3][(j + 2) % 3] == 0) {
                                move(i, j);
                                return true;
                            }
                    }
                    if (i + j == 2) {
                        if (game[(i - 1 + 3) % 3][(j + 1) % 3] == 0 && game[(i - 2 + 3) % 3][(j + 2) % 3] == 1)
                            if (fork[(i - 1 + 3) % 3][(j + 1) % 3] == 0) {
                                move(i, j);
                                return true;
                            }
                        if (game[(i - 1 + 3) % 3][(j + 1) % 3] == 1 && game[(i - 2 + 3) % 3][(j + 2) % 3] == 0)
                            if (fork[(i - 2 + 3) % 3][(j + 2) % 3] == 0) {
                                move(i, j);
                                return true;
                            }
                    }
                }
        return false;
    }

    private boolean centerfree(){
        if(game[1][1]==0){
            move(1,1);
            return true;
        }
        return false;
    }

    private boolean markcorner(){
        if(game[0][0]==2&&game[2][2]==0){
            move(2,2);
            return true;
        }
        if(game[2][2]==2&&game[0][0]==0){
            move(0,0);
            return true;
        }
        if(game[0][2]==2&&game[2][0]==0){
            move(2,0);
            return true;
        }
        if(game[2][0]==2&&game[0][2]==0){
            move(0,2);
            return true;
        }
        return false;
    }

    private boolean checkempty(){
        if(game[0][0]==0){
            move(0,0);
            return true;
        }
        if(game[2][2]==0){
            move(2,2);
            return true;
        }
        if(game[0][2]==0){
            move(0,2);
            return true;
        }
        if(game[2][0]==0){
            move(2,0);
            return true;
        }
        return false;
    }

    private boolean checkside(){
        if(game[0][1]==0){
            move(0,1);
            return true;
        }
        if(game[1][0]==0){
            move(1,0);
            return true;
        }
        if(game[2][1]==0){
            move(2,1);
            return true;
        }
        if(game[1][2]==0){
            move(1,2);
            return true;
        }
        return false;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
