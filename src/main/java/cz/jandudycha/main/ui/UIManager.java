package cz.jandudycha.main.ui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UIManager {
    private final ArrayList<UIObject> objects;
    private int selectedID = 0;

    public UIManager() {
        objects = new ArrayList<>();
    }

    public void update() {
        for (UIObject o : objects) {
            o.update();
        }
        checkForSelectedIds();
    }


    public void render(Graphics g) {
        for (UIObject o : objects) {
            o.render(g);
        }
    }

    private void checkForSelectedIds() {
        for (UIObject o : objects) {
            if (o.getSelectedID() != -1) {
                selectedID = o.getSelectedID();
                o.setSelectedID(-1);
            }
        }

    }

    public void onMouseMove(int mouseX, int mouseY) {
        for (UIObject o : objects) {
            o.onMouseMove(mouseX, mouseY);
        }
    }


    public void onMouseRelease(MouseEvent e) {
        for (UIObject o : objects) {
            o.onMouseRelease(e);
        }
    }

    public int getSelectedID() {
        return selectedID;
    }

    public void addObject(UIObject o) {
        objects.add(o);
    }


    public ArrayList<UIObject> getObjects() {
        return objects;
    }


}
