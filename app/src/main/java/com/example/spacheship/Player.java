package com.example.spacheship;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Player {

    private Bitmap bitmap;
    private int x;
    private int y;
    private int speed = 0;
    private boolean boosting;
    private final int GRAVITY = -20;
    private int maxY;
    private int minY;
    private final int MIN_SPEED = 1;
    private final int MAX_SPEED = 50;

    private Rect detectCollision;

    public Player(Context context, int screenX, int screenY) {
        x = 75;
        y = 50;
        speed = 1;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.player);
        maxY = screenY - bitmap.getHeight();
        minY = 0;
        boosting = false;
        detectCollision = new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());
    }

    public void setBoosting() {
        boosting = true;
    }

    public void stopBoosting() {
        boosting = false;
    }

    public void update() {
        if (boosting) {
            speed += 10;
        } else {
            speed -= 20;
        }
        if (speed > MAX_SPEED) {
            speed = MAX_SPEED;
        }
        if (speed < MIN_SPEED) {
            speed = MIN_SPEED;
        }
        y -= speed + GRAVITY;
        if (y < minY) {
            y = minY;
        }
        if (y > maxY) {
            y = maxY;
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
}
