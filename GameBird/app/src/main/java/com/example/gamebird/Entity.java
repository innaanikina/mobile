package com.example.gamebird;

import android.graphics.Canvas;

public abstract class Entity {
    protected int x, y;
    public abstract void draw(Canvas canvas);
    public abstract void move();

    public abstract int getCenterX();
    public abstract int getCenterY();
    public abstract int getRadius();
    public boolean intersect(Entity other) {
        return getDistance(getCenterX(), getCenterY(), other.getCenterX(),
                other.getCenterY()) < getRadius() + other.getRadius();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static int getDistance(int fromX, int fromY, int toX, int toY) {
        return (int) Math.sqrt(Math.pow(fromX - toX, 2) + Math.pow(fromY - toY, 2));
    }
}
