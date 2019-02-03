package com.acid.shooter.core;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

import java.util.Random;

public class Enemy {

    Bitmap [] sprites;
    float tx = 640, ty = 1000, x, y;
    float s = 0;
    float sin, cos, speed = 5;
    int pos = 0;
    Random rnd = new Random();
    long startTime;

    public Enemy(Bitmap [] bmp) {
        sprites = bmp;
        startTime = System.currentTimeMillis();
        x = (float) ((rnd.nextInt(2000)) * Math.pow(-1, rnd.nextInt(10)));
        y = (float) ((2000 + rnd.nextInt(2000)) * Math.pow(-1, rnd.nextInt(10)));
        float c = (float) Math.sqrt((tx - x) * (tx - x)  + (ty - y) * (ty - y));
        sin = -(y - ty) / c;
        cos = -(x - tx) / c;
    }

    public void draw(Canvas canvas){
        //canvas.rotate(30);
        canvas.drawBitmap(sprites[pos], x, y, null);
        long currentTime = System.currentTimeMillis();
        if(currentTime - startTime > 500){
            pos = (pos + 1) % 3;
            startTime = currentTime;
        }
        update();
    }

    public void update(){
        x += cos * speed;
        y += sin * speed;
        s = (float) Math.sqrt(x * x + y * y);
    }
}
