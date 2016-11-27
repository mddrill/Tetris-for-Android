package com.mddrill.tetris.game;

import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by mddri_000 on 11/22/2016.
 */

public class TimerThread extends Thread{

    public static final String TAG = TimerThread.class.getSimpleName();
    // flag to hold game state
    private boolean running;
    private SurfaceHolder surfaceHolder;
    private GameView gameView;

    public TimerThread(SurfaceHolder surfaceHolder, GameView gameView) {
        super();
        this.gameView = gameView;
        this.surfaceHolder = surfaceHolder;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        Log.d(TAG, "Starting game loop");
        long tickCount = 0L;
        while (running) {
            tickCount++;

            // update game state
            // render state to the screen
            Log.d(TAG, "Game loop executed " + tickCount + " times");
        }
    }

}
