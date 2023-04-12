package com.example.gamebird;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.LinkedList;

public class Enemy extends Entity {

    private Bitmap bird;
    private LinkedList<Rect> list = new LinkedList<>();
    private Rect destination;
    private int h;
    private final int size = 200;

    public Enemy(int x, int y, Resources resources) {
        this.x = x;
        this.y = y;
        bird = BitmapFactory.decodeResource(resources, R.drawable.enemy);
        h = bird.getHeight();
        for (int i = 0; i < 4; i++) {
            list.add(new Rect(i * h, 0, (i + 1) * h, h));
        }
        destination = new Rect(x - size / 2, y - size / 2,x + size, y + size);
        setXY(x, y);
    }
    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bird, list.getFirst(), destination, new Paint());
        list.addLast(list.getFirst());
        list.removeFirst();
    }

    @Override
    public void move() {
        x -= 4;
        destination.left -= 4;
        destination.right -= 4;
    }

    public int getCenterX() {
        return destination.centerX();
    }

    public int getCenterY() {
        return destination.centerY();
    }

    @Override
    public int getRadius() {
        return size / 2;
    }
}
