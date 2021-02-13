package com.example.spacheship;

import android.graphics.Rect;

public class Bullet {

    private int x;
    private int y;
    private int speed;

    private int maxX;
    private int minX;

    private int bulletWidth;
    private int bulletHeight;

    private Rect detectCollision;

    public Bullet(int screenX, Player player) {
        maxX = screenX;
        minX = 0;
        speed = 70;
        bulletWidth = 30;
        bulletHeight = 30;
        x = player.getX() + player.getBitmap().getWidth();
        y = player.getY() + player.getBitmap().getHeight()/2 - 15;
        detectCollision = new Rect(x, y, bulletWidth, bulletHeight);
    }

    public void update(Player player) {
        x += speed;
        if (x > maxX) {
            x = player.getX() + player.getBitmap().getWidth();
            y = player.getY() + player.getBitmap().getHeight()/2 - 15;
        }
        detectCollision.left = x;
        detectCollision.top = y;
        detectCollision.right = x + getBulletWidth();
        detectCollision.bottom = y + getBulletWidth();
    }

    public void disappear() {
        this.x = maxX;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMinX() {
        return minX;
    }

    public void setMinX(int minX) {
        this.minX = minX;
    }

    public int getBulletWidth() {
        return bulletWidth;
    }

    public void setBulletWidth(int bulletWidth) {
        this.bulletWidth = bulletWidth;
    }

    public int getBulletHeight() {
        return bulletHeight;
    }

    public void setBulletHeight(int bulletHeight) {
        this.bulletHeight = bulletHeight;
    }

    public Rect getDetectCollision() {
        return detectCollision;
    }

    public void setDetectCollision(Rect detectCollision) {
        this.detectCollision = detectCollision;
    }
}
