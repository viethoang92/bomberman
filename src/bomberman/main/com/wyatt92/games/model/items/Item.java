package com.wyatt92.games.model.items;

import com.wyatt92.games.model.Model;
import com.wyatt92.games.model.utils.Animation;
import com.wyatt92.games.model.utils.Sound;
import com.wyatt92.games.model.World;
import com.wyatt92.games.model.entities.Player;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Iterator;

/**
 * Grants a player additional abilities when picked up.
 */
public abstract class Item implements Model
{
    public static Item[] items = new Item[10];
    public static Item bombStrengthItem = new BombStrengthItem(1);
    public static Item bombCountItem = new MaxBombsItem(2);
    public static Item playerSpeedItem = new PlayerSpeedItem(3);


    public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32;

    protected World world;
    protected Animation animation;
    protected String name;
    protected final int id;

    protected Rectangle bounds;

    protected int x, y, count;
    protected boolean pickedUp = false;

    /**
     * Constructor
     *
     * @param images animationframes as BufferedImages
     * @param name name as String
     * @param id id as Integer
     */
    public Item(BufferedImage[] images, String name, int id){
        this.animation = new Animation(500,images);
        this.name = name;
        this.id = id;
        count = 1;

        bounds = new Rectangle(x,y, ITEMWIDTH, ITEMHEIGHT);

        items[id] = this;
    }

    @Override
    public void update() {
        animation.update();
        Iterator<Player> it = world.getEntityManager().getPlayers().iterator();
        while(it.hasNext()) {
            Player p = it.next();
            if(p.getCollisionBounds(0f,0f).intersects(bounds)){
                Sound.playSound("item_get.wav");
                pickedUp = true;
                addEffect(p);
            }
        }
    }



    @Override
    public void draw(Graphics g){
        if(world != null)
            draw(g, x, y);
    }

    public void draw(Graphics g, int x, int y)
    {
        g.drawImage(animation.getCurrentFrame(),x, y, ITEMWIDTH, ITEMHEIGHT, null);
    }

    public abstract Item createNew(int x, int y);

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
        bounds.x = x;
        bounds.y = y;
    }


    protected abstract void addEffect(Player player);

    // GETTERS and SETTERS

    public World getWorld()
    {
        return world;
    }

    public void setWorld(World world)
    {
        this.world = world;
    }

    public Animation getAnimation()
    {
        return animation;
    }

    public void setAnimation(Animation animation)
    {
        this.animation = animation;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public boolean isPickedUp()
    {
        return pickedUp;
    }
}
