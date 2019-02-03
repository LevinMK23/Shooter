package com.acid.shooter.core;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

public class Bullet {

    Bitmap sprite;
    float tx = 640, ty = 1000, x, y;
    float sin, cos, speed = 20;
    Matrix matrix;

    public Bullet(Bitmap bmp, float x, float y) {
        sprite = bmp;
        this.x = x;
        this.y = y;
        float c = (float) Math.sqrt((tx - x) * (tx - x)  + (ty - y) * (ty - y));
        sin = (y - ty) / c;
        cos = (x - tx) / c;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(sprite, tx, ty, null);
        update();
    }

    public void update(){
        tx += cos * speed;
        ty += sin * speed;

    }

}
