package com.acid.shooter.core;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.SynchronousQueue;

public class GameThread extends Thread{

    long difficult = 0, counter = 0;
    SurfaceHolder holder;
    Bitmap bmp, fireBall, enemy, hero;
    Bitmap [] eBmp;
    boolean running;
    ArrayList<Bullet> bullets;
    ArrayList<Enemy> enemies;
    long start;

    public void addBullet(Bullet bullet){
        bullets.add(bullet);
    }

    public GameThread(SurfaceHolder holder, Bitmap bmp, Bitmap fireBall,
                      Bitmap [] enemy, Bitmap hero) {
        this.holder = holder;
        start = System.currentTimeMillis();
        this.bmp = bmp;
        running = false;
        this.hero = hero;
        bullets = new ArrayList<>();
        enemies = new ArrayList<>();
        this.fireBall = fireBall;
        this.eBmp = enemy;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        while (running){
            synchronized (holder){
                long cnt = System.currentTimeMillis();
                if (cnt - start > (100 - difficult > 10 ? 100 - difficult : 10)){
                    enemies.add(new Enemy(eBmp));
                    start = cnt;
                    counter++;
                }
                if(counter > 50){
                    counter = 0;
                    difficult += 5;
                }
                Canvas canvas = holder.lockCanvas();
                canvas.drawBitmap(bmp, 0, 0, null);
                canvas.drawBitmap(bmp, 0, bmp.getHeight(), null);
                canvas.drawBitmap(hero, canvas.getWidth() / 2f,
                        canvas.getHeight() / 2f, null);
                for (int i = 0; i < bullets.size(); i++){
                    //bullets.get(i).setXY(canvas.getWidth() / 2f, canvas.getHeight() / 2f);
                    bullets.get(i).draw(canvas);
                }
                for (int i = 0; i < enemies.size(); i++){
                    enemies.get(i).draw(canvas);
                }
                holder.unlockCanvasAndPost(canvas);
                for (int i = 0; i < bullets.size(); i++) {
                    Bullet bullet = bullets.get(i);
                    if(bullet != null && !(bullet.tx >= 0 && bullet.tx <= 1500 &&
                            bullet.ty >= 0 && bullet.ty <= 2000)){
                        bullets.remove(bullet);
                    }
                }
                for (int i = 0; i < enemies.size(); i++) {
                    Enemy enemy = enemies.get(i);
                    if(enemy != null && enemy.s > 4000){
                        enemies.remove(enemy);
                    }
                }
                for (int j = 0; j < enemies.size(); j++){
                    Enemy enemy = enemies.get(j);
                    for (int i = 0; i < bullets.size(); i++) {
                        Bullet bullet = bullets.get(i);
                        if(bullet != null && enemy != null){
                            if (Math.abs(bullet.tx - enemy.x) <= 30 &&
                                    Math.abs(bullet.ty - enemy.y) <= 30) {
                                enemies.remove(enemy);
                            }
                        }
                    }
                }
            }
        }
    }
}
