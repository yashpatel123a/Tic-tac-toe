package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    //0:zero 1:cross 2:empty

    int [] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    //positions
    //+---+---+---+
    //| 0 | 1 | 2 |
    //| 3 | 4 | 5 |
    //| 6 | 7 | 8 |
    //+---+---+---+

    int [][] winningPositions = {   {0, 1, 2}, 
                                    {3, 4, 5},
                                    {6, 7, 8},
                                    {0, 3, 6},
                                    {1, 4, 7},
                                    {2, 5, 8},
                                    {0, 4, 8},
                                    {2, 4, 6} };
    //Decide the player turn
    int activePlayer=0;
    boolean gameActive=true; //Game status
    int moves=0;

    public void popup(View view){

        //get which one of the 9 box is clicked
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 2 && gameActive) {

            //set that box symbol for that player
            gameState[tappedCounter] = activePlayer;

            //for animation
            counter.setScaleX(0f);
            counter.setScaleY(0f);
            Log.i("Tag",counter.getTag().toString());
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.zero1);
                activePlayer = 1;
                moves++;
            } else {
                counter.setImageResource(R.drawable.cross1);
                moves++;
                activePlayer = 0;
            }

            counter.animate().scaleX(1f).scaleY(1f).rotationBy(360).setDuration(200);

            for (int [] winningPosition : winningPositions) {
                //checking fr winning condition
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    gameActive=false;

                    String winner="";

                    if(activePlayer==1)
                        winner="Zero";
                    else
                        winner="Cross";

                    Button playAgainButton=(Button)findViewById(R.id.playAgainButton);
                    TextView winnerTextView=(TextView)findViewById(R.id.winnerTextView);

                    winnerTextView.setText(winner + " is a Winner!");

                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                }
            }
        }

        //condition for the tie
        if(moves==9 && gameActive){

            Button playAgainButton=(Button)findViewById(R.id.playAgainButton);
            TextView winnerTextView=(TextView)findViewById(R.id.winnerTextView);

            winnerTextView.setText(" Tie!");

            playAgainButton.setVisibility(View.VISIBLE);
            winnerTextView.setVisibility(View.VISIBLE);
        }
    }
    public void playAgain(View view){

        Button playAgainButton=(Button)findViewById(R.id.playAgainButton);
        TextView winnerTextView=(TextView)findViewById(R.id.winnerTextView);

        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        //reset the game
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for(int i=0;i<gameState.length;i++)
            gameState[i]=2;

        activePlayer=0;
        gameActive=true;
        moves=0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
