package com.wyatt92.games.model.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * This class is responsible for Animation.
 * Loops through images at a specific animation speed
 */
public class Animation
{
    private int animSpeed, index;
    private long lastTime, timer;
    private BufferedImage[] frames;
    private boolean random;

    /**
     * Constructor
     *
     * @param animSpeed speed of animation
     * @param frames list of buffered images
     */
    public Animation(int animSpeed, BufferedImage[] frames){
    this.animSpeed = animSpeed;
    this.frames = frames;
    index = 0;
    timer = 0;
    lastTime = System.currentTimeMillis();
    random = false;
    }

    public void update(){
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if(timer > animSpeed) {
            index = random ? new Random().nextInt(frames.length) : index+1;
            timer = 0;
            if(index >= frames.length){
                index = 0;
            }
        }
    }


    public BufferedImage getCurrentFrame() {
    return frames[index];
    }

    public int getAnimSpeed()
    {
        return animSpeed;
    }

    public void setAnimSpeed(int animSpeed)
    {
        this.animSpeed = animSpeed;
    }


    public boolean isRandom()
    {
        return random;
    }

    public void setRandom(boolean random)
    {
        this.random = random;
    }
}
