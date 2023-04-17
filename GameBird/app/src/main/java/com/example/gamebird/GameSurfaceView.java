package com.example.gamebird;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.Random;

public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private SurfaceHolder mSurfaceHolder;
    private boolean mIsDrawing;
    private Canvas mCanvas;
    protected Bird bird;
    protected Enemy enemy;
    protected Context context;
    private int score;
    private long prevTime;

    private Random random = new Random();

    public GameSurfaceView(Context context) {
        super(context);
        initView(context);
    }

    public GameSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public GameSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        mIsDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        mIsDrawing = false;
    }

    @Override
    public void run() {
        while (mIsDrawing) {
            long now = System.currentTimeMillis();
            long elapsedTime = now - prevTime;
            if (elapsedTime > 30) {
                prevTime = now;
                draw();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        bird.setXY((int) event.getX(), (int) event.getY());
        return true;
    }

    private void initView(Context context) {
        this.context = context;
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);

        setZOrderOnTop(true);
        mSurfaceHolder.setFormat(PixelFormat.TRANSPARENT);

        setFocusable(true);
        setKeepScreenOn(true);
        setFocusableInTouchMode(true);

//        int x = 100;
//        int y = 100;
//        bird = new Bird(x, y, getResources());

        GameTimer timer = new GameTimer(20);
        timer.start();
        prevTime = System.currentTimeMillis();
    }

    private void draw() {
        try {
            mCanvas = mSurfaceHolder.lockCanvas();
            synchronized (mSurfaceHolder) {
                mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                bird.draw(mCanvas);
                createEnemyIfNull(1000, random.nextInt(800) + 100);
                enemy.draw(mCanvas);
            }
        } catch (Exception e) {
        } finally {
            if (mCanvas != null) {
                mSurfaceHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }

    public int getScore() {
        return score;
    }

    private void createEnemyIfNull(int x, int y) {
        if (enemy == null) {
            enemy = new Enemy(x, y, getResources());
        }
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
        }

        @Override
        public void onFinish() {

        }
    }
}
