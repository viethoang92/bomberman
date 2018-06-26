package com.wyatt92.games.model;

import com.wyatt92.games.model.entities.*;
import com.wyatt92.games.model.items.Item;
import com.wyatt92.games.model.tiles.Tile;

import java.util.ArrayList;

/**
 *  Base Interface of all models. Updates and draws objects.
 */
public interface Model
{
    void update();

    void moveLeft(int id);

    void moveRight(int id);

    void moveUp(int id);

    void moveDown(int id);

    void placeBomb(int id);

    void resetWorld();

    Tile getTile(int x, int y);

    void loadWorld(String path);

    // GETTERS and SETTERS

    int getWidth();

    int getHeight();

    int[][] getTiles();

    int getPlayerCount();

    boolean isGameOver();


    ArrayList<Player> getPlayers();

    Player getPlayer(int id);

    void setGameOver(boolean b);

    void setWinner(int winner);

    int getWinner();

    int getPlayerAlive();

    ArrayList<Blast> getBlasts();

    ArrayList<Bomb> getBombs();

    ArrayList<Entity> getEntities();

    ArrayList<Item> getItems();

    ArrayList<Stone> getStones();

    int getTileRows();

    int getTileColumns();

    void addItem(Item i);

    void addBomb(Bomb b);

    void moveX(Player p);

    void moveY(Player p);

}
