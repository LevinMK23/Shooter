package com.acid.shooter.core;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.acid.shooter.R;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    final SurfaceHolder holder;
    Bitmap bmp, fireball, enemy;
    Bitmap [] enemies;
    GameThread thread;
    static float x, y;

    public GameView(Context context) {
        super(context);
        holder = getHolder();
        holder.addCallback(this);
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.fill);
        fireball = BitmapFactory.decodeResource(getResources(), R.drawable.fireball);
        fireball = Bitmap.createScaledBitmap(fireball, 100, 100, true);
        enemies = new Bitmap[3];
        //Bitmap bitmap = Bitmap.createBitmap(fireball, 0, 0, 100, 100, new Matrix(), false);
        enemies[0] = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(getResources(), R.drawable.enemy1),
                100, 100, true);
        enemies[1] = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(getResources(), R.drawable.enemy2),
                100, 100, true);
        enemies[2] = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(getResources(), R.drawable.enemy3),
                100, 100, true);
        thread = new GameThread(holder, bmp, fireball, enemies);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX(), y = event.getY();
        //Matrix matrix = new Matrix();
        //matrix.reset();
        //matrix.postRotate(30);
        //fireball = Bitmap.createBitmap(fireball, 0, 0, 200, 200, matrix, false);
        thread.addBullet(new Bullet(fireball, x, y));
        return true;
    }



    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        thread.setRunning(false);
        while (thread.isAlive()){
            try {
                Thread.sleep(100);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
