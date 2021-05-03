package cz.jandudycha.main.world;

import cz.jandudycha.main.KeyInput;
import cz.jandudycha.main.RenderLayer;

import java.awt.*;

public class MapEditor {

    private int[][] worldMap;
    private final KeyInput keyInput;
    private int blockCoordinateX, blockCoordinateY;
    private final World world;
    private final RenderLayer renderLayer;


    public MapEditor(int[][] worldMap, KeyInput keyInput, World world, RenderLayer renderLayer) {
        this.renderLayer = renderLayer;
        this.keyInput = keyInput;
        this.worldMap = worldMap;
        this.world = world;

    }

    public void update() {
        if (keyInput.getMouseX() < renderLayer.getWINDOW_WIDTH() - 300) {
            blockCoordinateX = keyInput.getMouseX() / world.getTILE_WIDTH();
        }
        if (keyInput.getMouseY() < renderLayer.getWINDOW_HEIGHT() - 30) {
            blockCoordinateY = keyInput.getMouseY() / world.getTILE_HEIGHT();
        }

        if (keyInput.isLeftMouse()) {
            worldMap[blockCoordinateY][blockCoordinateX] = 0;
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.ORANGE);
        g.drawRect(blockCoordinateX * world.getTILE_WIDTH(), blockCoordinateY * world.getTILE_HEIGHT(), world.getTILE_WIDTH(), world.getTILE_HEIGHT());
    }

    public int getblockCoordinateX() {
        return blockCoordinateX;
    }

    public int getblockCoordinateY() {
        return blockCoordinateY;
    }
}
