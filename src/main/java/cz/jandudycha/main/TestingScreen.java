package cz.jandudycha.main;

import java.awt.*;

public class TestingScreen {

    private final KeyInput keyInput;
    private final RenderLayer renderLayer;

    public TestingScreen(KeyInput keyInput, RenderLayer renderLayer) {
        this.keyInput = keyInput;
        this.renderLayer = renderLayer;
    }


    public void update() {

    }

    public void render(Graphics g) {
        g.setColor(Color.green);
        g.drawString("mouse X: " + keyInput.getMouseX(), 980, 20);
        g.drawString("mouse Y: " + keyInput.getMouseY(), 980, 35);


        g.drawString("coordinate X: " + renderLayer.getGame().getGame().getWorld().getMapEditor().getblockCoordinateX(), 980, 60);
        g.drawString("coordinate Y: " + renderLayer.getGame().getGame().getWorld().getMapEditor().getblockCoordinateY(), 980, 75);
    }
}
