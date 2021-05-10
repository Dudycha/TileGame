package cz.jandudycha.main;

import cz.jandudycha.main.ui.UIManager;

import java.awt.event.*;


public class KeyInput implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

    private boolean left, right, up, down, space, leftMouse, reload, escape, bButton;
    private int mouseX = 0, mouseY = 0;
    private boolean clicked = false;
    private UIManager uiManager;
    private double wheelMovePom = 0;


    public KeyInput() {

    }

    public void setUIManager(UIManager uiManager) {
        this.uiManager = uiManager;
    }

    public void update() {
        if (uiManager != null) {
            uiManager.onMouseMove(mouseX, mouseY);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_A) {
            left = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_D) {
            right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            up = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_S) {
            down = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            space = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_R) {
            reload = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            escape = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            left = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_D) {
            right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            up = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_S) {
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            space = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_R) {
            reload = false;
        }


    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 1) {
            leftMouse = true;
        }


    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == 1) {
            leftMouse = false;
        }
        if (uiManager != null && e.getButton() == 1) {
            uiManager.onMouseRelease(e);
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

    }


    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        wheelMovePom = e.getPreciseWheelRotation();
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeftMouse() {
        return leftMouse;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }
}
