package com.example.gamebird;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Random;

public class GameField extends View {
    protected Bird bird;
    protected Enemy enemy;
    protected Context context;
    private int score;

    private Random random = new Random();

    public int getScore() {
        return score;
    }

    class GameTimer extends CountDownTimer {

        public GameTimer(long countDownInterval) {
            super(Integer.MAX_VALUE, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            createEnemyIfNull(1000, random.nextInt(800) + 100);
            if (enemy.getX() < -200) {
                enemy = new Enemy(1000, random.nextInt(800) + 100, getResources());
            }
            if (bird.intersect(enemy)) {
                enemy = new Enemy(1000, random.nextInt(800) + 100, getResources());
                score++;
            }
            bird.move();
            enemy.move();
            invalidate();
        }

        @Override
        public void onFinish() {

        }
    }

    public GameField(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        GameTimer timer = new GameTimer(20);
        timer.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        bird.draw(canvas);
        createEnemyIfNull(1000, random.nextInt(800) + 100);
        enemy.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        bird.setXY((int) event.getX(), (int) event.getY());
        return true;
    }

    public void pause() {
        SharedPreferences sPref = context.getSharedPreferences("BIRD", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putInt("BirdX", bird.getCenterX());
        editor.putInt("BirdY", bird.getCenterY());
        editor.commit();
    }

    public void resume() {
        SharedPreferences sPref = context.getSharedPreferences("BIRD", Context.MODE_PRIVATE);
        int x = sPref.getInt("BirdX", 100);
        int y = sPref.getInt("BirdY", 100);
        bird = new Bird(x, y, getResources());
    }

    private void createEnemyIfNull(int x, int y) {
        if (enemy == null) {
            enemy = new Enemy(x, y, getResources());
        }
    }

}
