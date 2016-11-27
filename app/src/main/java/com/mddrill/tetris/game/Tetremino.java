package com.mddrill.tetris.game;

import android.graphics.Point;

import com.mddrill.tetris.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.mddrill.tetris.game.Block.BLOCK_WIDTH;
import static com.mddrill.tetris.game.Game.HEIGHT_IN_BLOCKS;
import static com.mddrill.tetris.game.Game.WIDTH_IN_BLOCKS;

/**
 * Created by mddri_000 on 11/22/2016.
 */

public class Tetremino {
    private static Shapes shape;
    private static final int xStep = BLOCK_WIDTH, yStep = BLOCK_WIDTH;
    private static int x, y;
    private static final Random GENERATOR = new Random();
    public static final int LEFT = -1, RIGHT = 1;
    private static List<Point> shapeMap;
    private static List<Block> blocks = new ArrayList();
    private static boolean blockToTheLeft;
    private static boolean blockToTheRight;
    private static boolean blockBelow;
    private static boolean touchingLeftBoundary;
    private static boolean touchingRightBoundary;
    private static boolean touchingBottomBoundary;
    private static boolean canRotate = true;
    private static boolean overBottomBoundary;
    private static boolean overLeftBoundary;
    private static boolean overRightBoundary;
    private static boolean overTopBoundary;
    private static boolean onTopOfBlock;
    private static ArrayList<Point> checkShapeMap;
    private static ArrayList<Block> checkBlocks;
    private static final long NORMAL_SPEED_NANOS = 1000000000;
    private static final long FAST_SPEED_NANOS = NORMAL_SPEED_NANOS / 4;
    private static long before = 0;
    public static void reGenerate() {
        shape = Shapes.keyToShape(GENERATOR.nextInt(7));
        x = GENERATOR.nextInt(WIDTH_IN_BLOCKS) * BLOCK_WIDTH;
        y = -BLOCK_WIDTH;
        shapeMap = new ArrayList<>(Arrays.asList(shape.getShapeMap()));
        blocks = new ArrayList<>();
        //add blocks to the new tetremino
        for(Point p : shapeMap){
            blocks.add( new Block((int)p.getX()*BLOCK_WIDTH + x, (int)p.getY()*BLOCK_WIDTH + y), shape.getImageResID());
        }
        //adjust to make tetremino within boundaries
        checkForContact();
        //if the tetremino appears on top of a block or is stuck offscreen, redo the
        //generation

        if(onTopOfBlock || overLeftBoundary && blockToTheRight || overRightBoundary && blockToTheLeft){
            resetContact();
            reGenerate();
        }
        while(overLeftBoundary){
            xMove(RIGHT);
            resetContact();
            checkForContact();
        }
        while(overRightBoundary){
            xMove(LEFT);
            resetContact();
            checkForContact();
        }
        resetContact();
        //add Blocks the the Pane

        Tetris.getGamePane().getChildren().addAll(blocks);
    }
    private static void checkForContact(){
        for(Block b : blocks){
            for(Block tb : Game.getBlocks()){
                if(tb.getX()==b.getX()+BLOCK_WIDTH && tb.getY()==b.getY()){
                    blockToTheRight = true;
                }
                if(tb.getX()==b.getX()-BLOCK_WIDTH && tb.getY()==b.getY()){
                    blockToTheLeft = true;
                }
                if(tb.getX()==b.getX() && tb.getY()==b.getY()+BLOCK_WIDTH){
                    blockBelow = true;
                }
                if(tb.getX()==b.getX() && tb.getY()==b.getY()){
                    onTopOfBlock = true;
                }

            });
            if(b.getY()+BLOCK_WIDTH>=HEIGHT_IN_BLOCKS*BLOCK_WIDTH){
                touchingBottomBoundary = true;
                if(b.getY()+BLOCK_WIDTH>HEIGHT_IN_BLOCKS*BLOCK_WIDTH){
                    overBottomBoundary = true;
                }
            }
            if(b.getX()<=0){
                touchingLeftBoundary = true;
                if(b.getX()<0){
                    overLeftBoundary = true;
                }
            }else if(b.getX()+BLOCK_WIDTH>=WIDTH_IN_BLOCKS*BLOCK_WIDTH){
                touchingRightBoundary = true;
                if(b.getX()+BLOCK_WIDTH>WIDTH_IN_BLOCKS*BLOCK_WIDTH){
                    overRightBoundary = true;
                }
            }
        });
    }
    private static void resetContact(){
        touchingLeftBoundary = false;
        touchingRightBoundary = false;
        touchingBottomBoundary = false;
        blockToTheRight = false;
        blockToTheLeft = false;
        blockBelow = false;
        overLeftBoundary = false;
        overRightBoundary = false;
        overBottomBoundary = false;
        overTopBoundary = false;
        onTopOfBlock = false;
        canRotate = true;
    }
    public static void checkRotation(int direction){
        //this method simulates the tetremino rotating, to check if it will
        //come in contact with anything

        checkShapeMap = new ArrayList<>();
        for(Point p : shapeMap){
            checkShapeMap.add(new Point(p));
        }
        checkBlocks = new ArrayList<>();
        for(Block b : blocks){
            checkBlocks.add(new Block(b));
        }
        for(Point p : shapeMap){
            //switch x and y
            p.setLocation(p.getY(), p.getX());

            //if x and y have same sign, switch sign of x, otherwise switch sign of y
            if(x<0 == y<0){
                p.setLocation(p.getX() * -direction, p.getY()*direction);
            }else{
                p.setLocation(p.getX() * direction, p.getY()* -direction);
            }

        }
        for(int i=0; i<4; i++){
            //squares.get(1) is used here for the absolute location of the block
            //because squares.get(1) is the pivot point of rotation (i should probably
            //clean this up later...)

            checkBlocks.get(i).setX(checkShapeMap.get(i).getX()*BLOCK_WIDTH+checkBlocks.get(1).getX());
            checkBlocks.get(i).setY(checkShapeMap.get(i).getY()*BLOCK_WIDTH+checkBlocks.get(1).getY());
        }
        for(Block b : checkBlocks){
            for( Block tb : Game.getBlocks()){
                if(tb.getX()==b.getX() && tb.getY()==b.getY()){
                    canRotate = false;
                }
            }
            if(b.getX()<0 || b.getX()+BLOCK_WIDTH > WIDTH_IN_BLOCKS*BLOCK_WIDTH
                    || b.getY()> HEIGHT_IN_BLOCKS*BLOCK_WIDTH){
                canRotate = false;
            }
        });
    }
    public static void rotate(int direction){

        checkRotation(direction);

        if (canRotate == true){
            for(Point p : shapeMap{
                //switch x and y
                p.setLocation(p.getY(), p.getX());
                //if x and y have same sign, switch sign of x, otherwise switch sign of y
                if(x<0 == y<0){
                    p.setLocation(p.getX() * -direction, p.getY()*direction);
                }else{
                    p.setLocation(p.getX() * direction, p.getY()* -direction);
                }
            });
            for(int i=0; i<4; i++){
                //squares.get(1) is used here for the absolute location of the block
                //because squares.get(1) is the pivot point of rotation (i should probably
                //clean this up later...)
                blocks.get(i).setX(shapeMap.get(i).getX()*BLOCK_WIDTH+blocks.get(1).getX());
                blocks.get(i).setY(shapeMap.get(i).getY()*BLOCK_WIDTH+blocks.get(1).getY());
            }
        }
        resetContact();
    }
    public static void xMove(int direction){
        checkForContact();
        if(direction == LEFT){
            if(!touchingLeftBoundary && !blockToTheLeft){
                for(Block b : blocks){
                    b.xMove(direction, xStep);
                }
            }else{
                Sound.playErrorSound();
            }
        }else{
            if(!touchingRightBoundary && !blockToTheRight){
                for(Block b : blocks){
                    b.xMove(direction, xStep);
                }
            }else{
                Sound.playErrorSound();
            }
        }
        resetContact();
    }
    public static void yMove(){
        for(Block b : blocks){
            b.yMove(yStep);
        }
    }
    public static void update(long now){
        checkForContact();
        if(blockBelow || touchingBottomBoundary){
            Game.getBlocks().addAll(blocks);
            reGenerate();
        }else{
            if(now-Tetremino.getBefore() >= Game.getLevel().getDelta()){
                yMove();
                Tetremino.setBefore(now);
            }
        }
        resetContact();
    }
    public enum Shapes {
        I(0, new Point[]{new Point(0, 1), new Point(0, 0), new Point(0, -1), new Point(0, -2)}, R.drawable.Tetromino_I),
        J(1, new Point[]{new Point(0, -1), new Point(0, 0), new Point(0, 1), new Point(-1, 1)}, R.drawable.Tetromino_J),
        L(2, new Point[]{new Point(0, -1), new Point(0, 0), new Point(0, 1), new Point(1, 1)}, R.drawable.Tetromino_L),
        O(3, new Point[]{new Point(1, 0), new Point(0, 0), new Point(0, 1), new Point(1, 1)}, R.drawable.Tetromino_O),
        S(4, new Point[]{new Point(1, 0), new Point(0, 0), new Point(0, 1), new Point(-1, 1)}, R.drawable.Tetromino_S),
        T(5, new Point[]{new Point(-1, 0), new Point(0, 0), new Point(1, 0), new Point(0, 1)}, R.drawable.Tetromino_T),
        Z(6, new Point[]{new Point(-1, 0), new Point(0, 0), new Point(0, 1), new Point(1, 1)}, R.drawable.Tetromino_Z);

        private final int key;
        private final Point[] map;
        private final int resID;

        Shapes(int key, Point[] map, int resID) {
            this.key = key;

            this.map = map;
            this.resID = resID;
        }
        public static Shapes keyToShape(int i) {
            for (Shapes shape : Shapes.values())
                if (shape.getKey() == i)
                    return shape;
            return null;
        }
        public String getImageResID() {
            return resID;
        }
        public int getKey() {
            return key;
        }
        public Point[] getShapeMap() {
            return map;
        }
    }


    //
    //GETTERS AND SETTERS
    //

    public static List<Block> getBlocks() {
        return blocks;
    }

    public static long getBefore() {
        return before;
    }

    public static void setBefore(long before) {
        Tetremino.before = before;
    }

}
}
