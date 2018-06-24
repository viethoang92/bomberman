package com.wyatt92.games.model.states;


import com.wyatt92.games.model.Model;
import com.wyatt92.games.model.World;

import java.awt.*;

/**
 * This class sets the State that will be updated and drawn.
 * Plays different background music depending on the state.
 */
public abstract class State implements Model
{
    private static State currentState = null;
    private static State gameState;
    private static State menuState;
    private static State gameOverState;
    protected World world;
    private int winner;

    public State(World world) {
        this.world = world;
        gameState = new GameState(world);
        menuState = new MenuState(world);
        gameOverState = new GameOverState(world);
    }

    protected State()
    {
    }

    protected abstract void playLoopMusic();

    protected abstract void stopLoopMusic();

    @Override
    public abstract void update();

    @Override
    public abstract void draw(Graphics g);

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

    public static State getGameState()
    {
            return gameState;
    }
    public static State getGameOverState()
    {
        return gameOverState;
    }

    public void setWinner(int winner)
    {
        this.winner = winner;
    }

    public int getWinner()
    {
        return winner;
    }
}
