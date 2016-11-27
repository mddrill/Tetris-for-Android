package com.mddrill.tetris.game;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.widget.ImageView;

import java.util.Random;



/**
 * Created by mddri_000 on 11/14/2016.
 */
public class Block {
    public static final int BLOCK_WIDTH = 40;
    int x, y;
    Bitmap bmp;
    Block(int x, int y, Bitmap bmp){
        this.x = x;
        this.y = y;
        this.bmp = bmp;
    }
    Block(Block b){
        this.x = b.getX();
        this.y = b.getY();
        this.bmp = b.getBmp();
    }
    ///////Movements/////////////////////////////////////////////////////////
    public void xMove(int direction, int xStep){
        x += direction * xStep;
    }
    public void yMove(int yStep){
        y += yStep;
    }

    ////Draw/////////////////////////////////////////
    public void draw(Canvas canvas){
        canvas.drawBitmap(bmp, x, y, null);
    }

    public Bitmap getBmp() {
        return bmp;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
