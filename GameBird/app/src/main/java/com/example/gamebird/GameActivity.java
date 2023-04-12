package com.example.gamebird;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    GameField gameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameField = findViewById(R.id.gameField);
        TextView tvScore = findViewById(R.id.tvScore);
        new CountDownTimer(Integer.MAX_VALUE, 200) {

            @Override
            public void onTick(long l) {
                tvScore.setText(String.valueOf(gameField.getScore()));
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                finish();
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameField.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameField.resume();
    }
}