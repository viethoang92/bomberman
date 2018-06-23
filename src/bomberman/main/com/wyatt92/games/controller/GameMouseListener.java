package com.wyatt92.games.controller;

import com.wyatt92.games.model.ui.UIManager;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Listens to mouse motions and mouse clicks.
 */
public class GameMouseListener implements MouseListener, MouseMotionListener
{
    private boolean leftPressed, rightPressed;
    private int mouseX, mouseY;
    private UIManager uiManager;

    public GameMouseListener() {

    }

    public void setUiManager (UIManager uiManager) {
        this.uiManager = uiManager;
    }
    // Getters

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public Point getMousePosition() {
        return new Point(mouseX, mouseY);
    }
    // Implemented methods

    @Override
    public void mouseClicked(MouseEvent e)
    {    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        if(e.getButton() == MouseEvent.BUTTON1) {
            leftPressed = true;
        } else if(e.getButton() == MouseEvent.BUTTON3)
            rightPressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        if(e.getButton() == MouseEvent.BUTTON1) {
            leftPressed = false;
        } else if(e.getButton() == MouseEvent.BUTTON3)
            rightPressed = false;

        if(uiManager != null){
            uiManager.onMouseRelease(e);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }

    @Override
    public void mouseDragged(MouseEvent e)
    {

    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        mouseX = e.getX();
        mouseY = e.getY();

        if(uiManager != null) {
            uiManager.onMouseMove(e);
        }
    }
}