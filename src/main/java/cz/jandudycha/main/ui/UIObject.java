package cz.jandudycha.main.ui;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class UIObject {

    protected int x, y;
    protected int width, height;
    protected Rectangle bounds;
    private boolean hovering = false;
    private boolean selected = false;
    private int selectedID = -1;


    public UIObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bounds = new Rectangle(x, y, width, height);
    }

    public abstract void update();

    public abstract void render(Graphics g);

    public abstract void onClick();


    public void onMouseMove(int mouseX,int mouseY) {
        hovering = bounds.contains(mouseX, mouseY);
    }

    public void onMouseRelease(MouseEvent e) {
        if (hovering) {
            onClick();
        }
    }

    public void updateBounds(){
        bounds = new Rectangle(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSelectedID() {
        return selectedID;
    }

    public void setSelectedID(int selectedID) {
        this.selectedID = selectedID;
    }

    public boolean isHovering() {
        return hovering;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }
}
