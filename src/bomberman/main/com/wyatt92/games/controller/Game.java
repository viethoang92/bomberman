package com.wyatt92.games.controller;

import com.wyatt92.games.model.Assets;
import com.wyatt92.games.model.Model;
import com.wyatt92.games.model.World;
import com.wyatt92.games.model.states.GameState;
import com.wyatt92.games.model.states.MenuState;
import com.wyatt92.games.model.states.State;
import com.wyatt92.games.model.ui.UIManager;
import com.wyatt92.games.view.GamePanel;
import com.wyatt92.games.view.View;

import java.awt.*;


/**
 * Controls and updates the view.
 * Keeps View and Model separated.
 */
public class Game implements Runnable{



    private boolean running = false;
    private Thread thread1;

    private View view;

    private Graphics g;

    // States
    private State gameState;
    private State menuState;

    // World
    private World world;

    // Input
    private GameKeyListener gameKeyListener;
    private GameMouseListener gameMouseListener;
    private UIManager uiManager;

    Game(View view) {
        this.view = view;
        gameKeyListener = new GameKeyListener();
        gameMouseListener = new GameMouseListener();
        uiManager = new UIManager();
        gameMouseListener.setUiManager(uiManager);
        init();
        run();
    }

    // METHODS

    private void init() {
        view.getGamePanel().addKeyListener(gameKeyListener);
        view.getGamePanel().addMouseListener(gameMouseListener);
        view.getGamePanel().addMouseMotionListener(gameMouseListener);
        Assets.init();
//        gamePanel.setDoubleBuffered(true);

        world = new World("world1.txt");
        gameState = new GameState(world);
        menuState = new MenuState(world, gameState, uiManager);
        State.setCurrentState(menuState);
    }

    public void run() {

        // init Timer
        int fps = 60;
        double timePerTick = 1000000000 /fps;
        double delta = 0 ;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while(running) {
            // update Time and delta
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick; // number between 0 and 1
            timer += now - lastTime;
            lastTime = now;


            if(delta >= 1) {
                update();
//                gamePanel.draw(gamePanel.getGraphics());
                view.repaint();
                ticks++;
                delta--;
            }

            // reset Timer
            if(timer >= 1000000000) {
//                System.out.println("Ticks and Frames: " + ticks); // shows FPS

                ticks = 0;
                timer = 0;
            }
        }

        stop();
    }
    synchronized void start(){
        if(running) return;
        running = true;
        thread1 = new Thread(this);
        thread1.start();

    }

    private synchronized void stop(){
        if(!running) return;
        running = false;
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void update(){
        gameKeyListener.update();
        if(world.getPlayerOne()!=null){
            world.getPlayerOne().xMove = 0;
            world.getPlayerOne().yMove = 0;
            if(gameKeyListener.W)
                world.getPlayerOne().moveUp();
            if(gameKeyListener.S)
                world.getPlayerOne().moveDown();
            if(gameKeyListener.A)
                world.getPlayerOne().moveLeft();
            if(gameKeyListener.D)
                world.getPlayerOne().moveRight();
            if(gameKeyListener.SPACE)
                world.getPlayerOne().placeBomb();

        }
        if(world.getPlayerTwo()!=null){
            world.getPlayerTwo().xMove = 0;
            world.getPlayerTwo().yMove = 0;

            if(gameKeyListener.UP)
                world.getPlayerTwo().moveUp();
            if(gameKeyListener.DOWN)
                world.getPlayerTwo().moveDown();
            if(gameKeyListener.LEFT)
                world.getPlayerTwo().moveLeft();
            if(gameKeyListener.RIGHT)
                world.getPlayerTwo().moveRight();
            if(gameKeyListener.CTRL)
                world.getPlayerTwo().placeBomb();
        }
        if(State.getCurrentState() != null)
        {
            State.getCurrentState().update();
        }


    }


    // GETTERS and SETTERS

    public World getWorld()
    {
        return world;
    }

    public void setWorld(World world)
    {
        this.world = world;
    }
}
