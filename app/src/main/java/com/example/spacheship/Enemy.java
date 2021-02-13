package com.example.spacheship;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Enemy {

    private Bitmap bitmap;

    private int x;

    public void setY(int y) {
        this.y = y;
    }

    private int y;
    private int speed = 1;
    private int maxX;

    public int getMinX() {
        return minX;
    }

    private int minX;
    private int maxY;
    private int minY;

    private Rect detectCollision;

    public Enemy(Context context, int screenX, int screenY) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy);

        maxX = screenX;
        maxY = screenY - bitmap.getHeight();
        minX = 0;
        minY = 0;

        Random generator = new Random();
        speed = generator.nextInt(6) + 5;
        x = screenX;
        y = generator.nextInt(maxY);
        detectCollision = new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());
    }

    public void update(AtomicInteger lives) {
        x -= speed;
        if (x < minX - bitmap.getWidth()) {
            Random generator = new Random();
            speed = generator.nextInt(6) + 5;
            x = maxX;
            y = generator.nextInt(maxY);
            lives.decrementAndGet();
        }
        if (y < minY) {
            Random generator = new Random();
            speed = generator.nextInt(6) + 5;
            x = maxX;
            y = generator.nextInt(maxY);
        }
        detectCollision.left = x;
        detectCollision.top = y;
        detectCollision.right = x + bitmap.getWidth();
        detectCollision.bottom = y + bitmap.getHeight();
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public Rect getDetectCollision() {
        return detectCollision;
    }

    public void setX(int x) {
        this.x = x;
    }
}
