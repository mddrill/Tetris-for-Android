package com.mddrill.tetris.game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.security.spec.ECField;

import static org.junit.Assert.*;

/**
 * Created by mddri_000 on 11/26/2016.
 */
public class GameTest {
    Game classUnderTest;
    @Before
    public void setUp() {

        classUnderTest = new Game();
    }

    @After
    public void tearDown() {
    }
    @Test
    public void onCreateTest() throws Exception {

    }
    @Test
    public void updateTest() throws Exception {

    }
    public void isGameOverTest() throws Exception {
        boolean expected = false;
        boolean result = classUnderTest.isGameOver();
        assert


    }
    @Test
    public void gameOverTest() throws Exception {

    }
    @Test
    public void checkIfFullRowTest() throws Exception {

    }
    @Test
    public void removeRowTest() throws Exception {

    }
    @Test
    public void moveRowsDownTest() throws Exception {

    }
    @Test
    public void areRowsMovingDownTest() throws Exception {

    }
    ////enum levels methods
    @Test
    public void setLevelTest() throws Exception {

    }
    @Test
    public void getDelta() throws Exception {

    }
    ///////////////////////


}