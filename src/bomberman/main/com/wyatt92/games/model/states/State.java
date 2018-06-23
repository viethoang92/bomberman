package com.wyatt92.games.model.states;


import com.wyatt92.games.model.Model;

import java.awt.*;

/**
 * This class sets the State that will be updated and drawn.
 * Plays different background music depending on the state.
 */
public abstract class State implements Model
{
    private static State currentState = null;

    public static State getCurrentState()
    {
        return currentState;
    }

    public static void setCurrentState(State state) {
        if(currentState!=null)
        {
            currentState.stopLoopMusic();
        }
        currentState = state;
        currentState.playLoopMusic();
    }

    @Override
    public abstract void update();

    @Override
    public abstract void draw(Graphics g);

    protected abstract void playLoopMusic();

    protected abstract void stopLoopMusic();
}
