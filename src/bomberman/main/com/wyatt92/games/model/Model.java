package com.wyatt92.games.model;

import com.wyatt92.games.model.entities.Player;
import com.wyatt92.games.model.tiles.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 *  Base Interface of all models. Updates and draws objects.
 */
public interface Model
{
    void update();

    void draw(Graphics g);

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
}
