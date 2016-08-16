package com.example.clarkhinchcliff.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //0 = yellow 1 = red
    int activePlayer = 0;

    //2 means unplayed
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningConditions = {
            {0,1,2},
            {3,4,5},
            {6,7,8},
            {0,3,6},
            {1,4,7},
            {2,5,8},
            {0,4,8},
            {2,4,6}
    };

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2) {
            counter.setTranslationY(-1000f);
            gameState[tappedCounter] = activePlayer;

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
        } else {
            Toast.makeText(getApplicationContext(), "This spot is already taken", Toast.LENGTH_LONG).show();
        }

        for (int[] winningPosition : winningConditions ) {
            if (gameState[winningPosition[0]] == gameState[winningPosition[1]]
                    && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                    && gameState[winningPosition[0]] != 2) {

                LinearLayout winLayout = (LinearLayout)findViewById(R.id.playAgainLayout);
                TextView winnerTextView = (TextView)findViewById(R.id.winnerTextView);

                winLayout.setVisibility(View.VISIBLE);

                if (gameState[winningPosition[0]] == 0) {
                    winnerTextView.setText("Yellow wins");
                } else {
                    winnerTextView.setText("Red wins");
                }
            }
        }
    }

    public void playAgain(View view) {
        LinearLayout winLayout = (LinearLayout)findViewById(R.id.playAgainLayout);

        winLayout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }

        GridLayout gameGrid = (GridLayout)findViewById(R.id.gridLayout);

        for (int i = 0; i < gameGrid.getChildCount(); i++) {
            ((ImageView) gameGrid.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
