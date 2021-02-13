package com.example.spacheship;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class GameView extends SurfaceView implements Runnable {

    volatile boolean playing;
    private Thread gameThread = null;
    private Player player;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private List<Star> stars = new ArrayList<>();
    private Enemy[] enemies;
    private int enemyCount = 3;
    private Boom boom;
    private Bullet bullet;
    AtomicInteger lives = new AtomicInteger(3);
    AtomicInteger score = new AtomicInteger(0);

    public GameView(Context context, int screenX, int screenY) {
        super(context);
        player = new Player(context, screenX, screenY);
        surfaceHolder = getHolder();
        paint = new Paint();
        int starNums = 100;
        for (int i = 0; i < starNums; i++) {
            Star s = new Star(screenX, screenY);
            stars.add(s);
        }
        enemies = new Enemy[enemyCount];
        for(int i=0; i<enemyCount; i++){
            enemies[i] = new Enemy(context, screenX, screenY);
        }
        boom = new Boom(context);
        bullet = new Bullet(screenX, player);
    }

    @Override
    public void run() {
        while (playing && lives.get() > 0) {
            update();
            draw();
            control();
        }
        if (lives.get() <= 0) {
            Intent intent = new Intent(this.getContext(), GameOverActivity.class);
            this.getContext().startActivity(intent);
        }
    }

    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException ignored) {

        }
    }

    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                player.stopBoosting();
                break;
            case MotionEvent.ACTION_DOWN:
                player.setBoosting();
                break;
        }
        return true;
    }

    private void update() {
        player.update();
        bullet.update(player);
        boom.setX(-250);
        boom.setY(-250);
        for (Star s : stars) {
            s.update(player.getSpeed());
        }
        for (int i=0; i<enemyCount; i++) {
            enemies[i].update(lives);
            if (Rect.intersects(player.getDetectCollision(), enemies[i].getDetectCollision())) {
                boom.setX(enemies[i].getX());
                boom.setY(enemies[i].getY());
                enemies[i].setY(-200);
                lives.decrementAndGet();
            }
            if (Rect.intersects(bullet.getDetectCollision(), enemies[i].getDetectCollision())) {
                boom.setX(enemies[i].getX());
                boom.setY(enemies[i].getY());
                enemies[i].setY(-200);
                bullet.disappear();
                score.incrementAndGet();
            }
        }
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLACK);
            paint.setColor(Color.WHITE);
            for (Star s : stars) {
                paint.setStrokeWidth(s.getStarWidth());
                canvas.drawPoint(s.getX(), s.getY(), paint);
            }
            canvas.drawBitmap(
                    player.getBitmap(),
                    player.getX(),
                    player.getY(),
                    paint
            );
            for (int i = 0; i < enemyCount; i++) {
                canvas.drawBitmap(
                        enemies[i].getBitmap(),
                        enemies[i].getX(),
                        enemies[i].getY(),
                        paint
                );
            }
            canvas.drawBitmap(
                    boom.getBitmap(),
                    boom.getX(),
                    boom.getY(),
                    paint
            );
            paint.setColor(Color.YELLOW);
            canvas.drawRect(bullet.getDetectCollision(), paint);
            paint.setColor(Color.RED);
            paint.setTextSize(70);
            paint.setTextAlign(Paint.Align.LEFT);
            canvas.drawText("Lives: " + lives, 0, paint.getTextSize(), paint);
            canvas.drawText("Score: " + score, 0, paint.getTextSize()*2, paint);
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void control() {
        try {
            gameThread.sleep(17);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
