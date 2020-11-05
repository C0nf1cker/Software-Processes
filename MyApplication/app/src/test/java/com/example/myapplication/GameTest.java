package com.example.myapplication;

import android.view.View;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void goMain() {
    }

    @Test
    public void victoryNotification_increaseScoreTest() {
        Game g = new Game();
        boolean pressed = g.pressed;
        int counter=  g.counter;
        View v = null;
        g.victoryNotification_increaseScore(v);
        assertEquals(counter, 1);
        assertEquals(pressed,true);
    }

    @Test
    public void playAgain() {
    }
}