package com.example.gamebird;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.LinkedList;

public class Bird extends Entity {
    private Bitmap bird;
    private LinkedList<Rect> list = new LinkedList<>();
    private Rect destination;
    private int h;
    private final int size = 200;

    public Bird(int x, int y, Resources resources) {
        bird = BitmapFactory.decodeResource(resources, R.drawable.twitter);
        h = bird.getHeight();
        for (int i = 0; i < 4; i++) {
            list.add(new Rect(i * h, 0, (i + 1) * h, h));
        }
        destination = new Rect(x - size / 2, y - size / 2, x + size, y + size);
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
        if (Math.abs(destination.centerX() - x) >= 3) {
            if (destination.centerX() > x) {
                destination.left -= 5;
                destination.right -= 5;
            } else if (destination.centerX() < x) {
                destination.left += 5;
                destination.right += 5;
            }
        }

        if (Math.abs(destination.centerY() - y) >= 3) {
            if (destination.centerY() > y) {
                destination.top -= 5;
                destination.bottom -= 5;
            } else if (destination.centerY() < y) {
                destination.top += 5;
                destination.bottom += 5;
            }
        }
    }

    public int getCenterX() {
        return destination.centerX();
    }

    public int getCenterY() {
        return destination.centerY();
    }

    public int getRadius() {
        return size / 2;
    }
}
