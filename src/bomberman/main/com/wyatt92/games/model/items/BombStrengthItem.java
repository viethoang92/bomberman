package com.wyatt92.games.model.items;

import com.wyatt92.games.model.Assets;
import com.wyatt92.games.model.entities.Player;

/**
 * Grants a player more bombStrength when picked up.
 * If the player places a bomb the bomb covers more tiles with blasts when it explodes.
 */
public class BombStrengthItem extends Item
{

    public BombStrengthItem(int id)
    {
        super(Assets.bombStrength, "Powerup", id);
    }

    @Override
    public void addEffect(Player player)
    {
        player.addBombStrength();
    }

    @Override
    public Item createNew(int x, int y){
        Item i = new BombStrengthItem(id);
        i.setPosition(x,y);
        return i;
    }

}
