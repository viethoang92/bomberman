package com.wyatt92.games.model.entities;

import com.wyatt92.games.model.Model;

import java.awt.*;

/**
 * An Entity is any object that can be placed into a level.
 * Entities can be created (spawned) and destroyed through GamePlay.
 * Loses health if it gets hurt and consequently can be destroyed if the health drops to 0;
 * If not set to active it gets removed by the EntityManager.
 * All entities have a default health that can be changed individually for subclasses.
 * Can collide with Tiles and other Entities.
 */
public abstract class Entity
{
    protected static final int DEFAULT_HEALTH = 3;
    protected Model model;
    protected float x,y,xOffset, yOffset;
    protected int width, height;
    protected int health;
    protected boolean active = true;
    protected Rectangle bounds;
    protected Point centerPoint;

    /**
     * Constructor
     *
     * @param x x-coordinate of entity
     * @param y y-coordinate of entity
     * @param width width of entity
     * @param height height of entity
     */
    Entity(float x, float y, int width, int height) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        xOffset = width/2;
        yOffset = height/2;
        health = DEFAULT_HEALTH;
        centerPoint = new Point((int)(x+xOffset)/width * width + width/2,(int) (y+yOffset)/height * height + height/2);

        bounds = new Rectangle(0,0, width, height);
    }


    public abstract void update();

    public void hurt(int amount) {
        health -= amount;
        if(health <= 0) {
            setActive(false);
        }
    }




    public Rectangle getCollisionBounds(float xOffset, float yOffset) {
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
    }

    // GETTERS AND SETTERS

    public float getX()
    {
        return x;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public static int getDefaultHealth()
    {
        return DEFAULT_HEALTH;
    }

    public boolean isActive()
    {
        return active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    // TODO change center-point
    public Point getCenterPoint(){
        return new Point((int)(x+xOffset)/width * width + width/2,(int) (y+yOffset)/height * height + height/2);
    }

    public void setCenterPoint(Point centerPoint)
    {
        this.centerPoint = centerPoint;
    }

    public Rectangle getBounds()
    {
        return bounds;
    }

    public void setBounds(Rectangle bounds)
    {
        this.bounds = bounds;
    }

    public float getxOffset()
    {
        return xOffset;
    }

    public void setxOffset(float xOffset)
    {
        this.xOffset = xOffset;
    }

    public float getyOffset()
    {
        return yOffset;
    }

    public void setyOffset(float yOffset)
    {
        this.yOffset = yOffset;
    }

    public int getHealth()
    {
        return health;
    }

    public void setHealth(int health)
    {
        this.health = health;
    }
}
