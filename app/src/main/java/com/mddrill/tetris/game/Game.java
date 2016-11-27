package com.mddrill.tetris.game;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.mddrill.tetris.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mddrill.tetris.game.Block.BLOCK_WIDTH;

public class Game extends AppCompatActivity {
    public static final int WIDTH_IN_BLOCKS = 10, HEIGHT_IN_BLOCKS = 15;
    public static final int SCREEN_WIDTH = BLOCK_WIDTH * WIDTH_IN_BLOCKS, SCREEN_HEIGHT = BLOCK_WIDTH * HEIGHT_IN_BLOCKS;
    private final int POINTS_PER_SCORE = 100;
    private final String SIGNATURE = "Tetris by\nMatt Drill";
    private List<Block> blocks = new ArrayList<>();
    private int rowMovingDown;
    private int[] numSquaresInEachRow;
    private int score;
    private Level level = Level.ONE;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));
    }

    private void update(long now){
        if(!isGameOver()){

            System.out.println(now);
            Tetremino.update(now);
            if(areRowsMovingDown()){
                moveRowsDown(rowMovingDown);
            }else{
                checkIfFullRow();
            }
        }else{
            gameOver();
        }
    }
    private boolean isGameOver(){
        for(Block b : blocks){
            if(b.getY() <= 0){
                return true;
            }
        }
        return false;
    }
    private void gameOver(){
        //createGameOverMenu();
    }
    private void checkIfFullRow(){
        //The method removes the lowest row that is full
        //it only removes one row for the sake of animation
        for(Block b : blocks){
            numSquaresInEachRow[(int)b.getY()/BLOCK_WIDTH]++;
        }
        //count down in this loop so that rows at the bottom are removed first
        for(int i = HEIGHT_IN_BLOCKS-1; i>=0; i--){
            if(numSquaresInEachRow[i] >= WIDTH_IN_BLOCKS){
                removeRow(i);
                break;
            }
        }
        Arrays.fill(numSquaresInEachRow, 0);
    }
    private void removeRow(int row){
        System.out.println("removeRow called");
        score += POINTS_PER_SCORE;
        level = Level.setLevel(score);
        //showScore.setText(String.valueOf(score));
        for(Block b : blocks){
            if(b.getY()/BLOCK_WIDTH == row){
                gamePane.getChildren().remove(b);
            }
        }
        //Figure this out later
        //blocks.removeIf(s->(s.getY()/BLOCK_WIDTH==row));
        moveRowsDown(row);
        rowMovingDown = row;
        Sound.playClearRowSound();
    }
    private void moveRowsDown(int row){
        //Move all rows above parameter row down 1
        for(Block b : blocks){
            if(b.getY()/BLOCK_WIDTH <= row){
                b.setY(b.getY() + 1);
            }
        }
    }
    private boolean areRowsMovingDown(){
        for(Block b : blocks){
            if(b.getY()%BLOCK_WIDTH !=0){
                return true;
            }
        }
        return false;
    }
    public enum Level{
        ONE("Level One", 750000000),
        TWO("Level Two", 500000000),
        THREE("Level Three", 250000000),
        FOUR("Level Four", 125000000),
        FIVE("Level Five", 62500000);

        private final String text;
        //This is the score needed to achieve this level
        private final int speed;


        Level(String text, int speed){
            this.text = text;
            this.speed = speed;
        }
        public static Level setLevel(int score){
            if(score >= 4000){
                return FIVE;
            }else if(score >= 3000){
                return FOUR;
            }else if(score >= 2000){
                return THREE;
            }else if(score >= 1000){
                return TWO;
            }else {
                return ONE;
            }
        }

        public String getText() {
            return text;
        }

        public int getDelta(){
            if(increaseSpeed){
                return speed / 2;
            }else{
                return speed;
            }
        }

    }
    //To test private methods
    public accessPrivateMethod() {
        return this.
    }
    //
    //GETTERS AND SETTERS************************************************************
    //
    public List<Block> getBlocks() {
        return blocks;
    }

    public Level getLevel() {
        return level;
    }
}
