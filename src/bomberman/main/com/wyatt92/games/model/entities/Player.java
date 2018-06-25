package com.wyatt92.games.model.entities;


import com.wyatt92.games.model.utils.Sound;
import com.wyatt92.games.model.utils.Animation;
import com.wyatt92.games.model.Assets;
import com.wyatt92.games.model.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A Player can be controlled by an AI or a Human.
 */
public class Player extends DynamicEntity
{

//    private boolean isMoving = false;

    private Animation animDown, animUp, animLeft, animRight;
    private int maxBombs = 1;
    private int bombCount = 1;
    private int bombStrength = 1;
    private int id;

    // AttackTimer
    private long lastAttackTimer, attackCooldown = 400, attackTimer = attackCooldown;
    private long bombCooldown = 2000;

    protected Game game;

    /**
     * Constructor
     *
     * @param game world of the player
     * @param x x-coordinate of player
     * @param y y-coordinate of player
     */
    public Player(Game game, float x, float y, int id)
    {
        super(game, x, y, DynamicEntity.DEFAULT_CHARACTER_WIDTH, DynamicEntity.DEFAULT_CHARACTER_HEIGHT);
        this.id = id;
        bounds.x = 16;
        bounds.y = 32;
        bounds.width = 32;
        bounds.height = 32;

        animDown = new Animation(200, Assets.player_down);
        animUp = new Animation(200, Assets.player_up);
        animLeft = new Animation(200, Assets.player_left);
        animRight = new Animation(200, Assets.player_right);
    }

    private void updateBombCount()
    {
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();

        if (attackTimer < bombCooldown)
        {
            return;
        }
        if(bombCount < maxBombs)
        {
            bombCount++;
            System.out.println(id + " : bombCount = " + bombCount);
            attackTimer = 0;
        }
    }


    @Override
    public void destroy()
    {
        System.out.println(id + " : You died!");
    }

    public void addMaxBombs()
    {
        maxBombs++;
        System.out.println(id + ": You can now deploy " + maxBombs + " bombs at the same time.");
    }

    public void addBombStrength()
    {
        bombStrength++;
        System.out.println(id + ": Your bombblasts now covers " + bombStrength + " tiles in each direction.");
    }

    public void addPlayerSpeed() {
        speed+=1.0f;
    }

    public void placeBomb()
    {

        // attack cooldown
        if (attackTimer < attackCooldown)
            return;

        if (bombCount > 0)
        {
            Sound.playSound("bomb_Set.wav");
            Bomb b = new Bomb(game, super.getCenterPoint().x, super.getCenterPoint().y, bombStrength);
            game.getBombManager().addBomb(b);
            bombCount--;
            attackTimer = 0;
        }

    }

    public void moveUp(){
        yMove = -speed;
    }

    public void moveDown(){
        yMove = speed;
    }

    public void moveLeft(){
        xMove = -speed;
    }

    public void moveRight(){
        xMove = speed;
    }

    @Override
    public void update()
    {
        animDown.update();
        animUp.update();
        animRight.update();
        animLeft.update();

        updateBombCount();

    }

    @Override
    public void draw(Graphics g)
    {
        g.drawImage(getCurrentAnimationFrame(), (int) x, (int) y, width, height, null);
    }

    private BufferedImage getCurrentAnimationFrame()
    {
        if (xMove < 0)
        {
            return animLeft.getCurrentFrame();
        } else if (xMove > 0)
        {
            return animRight.getCurrentFrame();
        } else if (yMove < 0)
        {
            return animUp.getCurrentFrame();
        } else if (yMove > 0)
        {
            return animDown.getCurrentFrame();
        } else
        {
            return animDown.getCurrentFrame();
        }
    }

    public Point getCenterPoint()
    {

        return centerPoint;
    }

    public Game getGame()
    {
        return game;
    }

    public void setGame(Game game)
    {
        this.game = game;
    }

    public int getId()
    {
        return id;
    }
}
