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
    private int tileToInsert = 0;
    private boolean outOfBounds = false;


    public MapEditor(int[][] worldMap, KeyInput keyInput, World world, RenderLayer renderLayer) {
        this.renderLayer = renderLayer;
        this.keyInput = keyInput;
        this.worldMap = worldMap;
        this.world = world;

    }

    public void update() {
        checkForOutOfBounds();


        if (keyInput.isLeftMouse() && !outOfBounds) {
            worldMap[blockCoordinateY][blockCoordinateX] = tileToInsert;
        }
    }

    private void checkForOutOfBounds() {
        if (keyInput.getMouseX() < renderLayer.getWINDOW_WIDTH() - 290) {
            blockCoordinateX = (keyInput.getMouseX() +
                    (int) renderLayer.getGame().getGame().getWorld().getGameCamera().getxOffset()) /
                    world.getTILE_WIDTH();
        } else {
            outOfBounds = true;
        }

        if (keyInput.getMouseY() < renderLayer.getWINDOW_HEIGHT() - 10) {
            blockCoordinateY = (keyInput.getMouseY() +
                    (int) renderLayer.getGame().getGame().getWorld().getGameCamera().getyOffset()) /
                    world.getTILE_HEIGHT();
        } else {
            outOfBounds = true;
        }

        if (keyInput.getMouseX() < 0) {
            outOfBounds = true;
            blockCoordinateX = 0;
        }

        if (keyInput.getMouseY() < 0) {
            outOfBounds = true;
            blockCoordinateY = 0;
        }


        if ((keyInput.getMouseX() < renderLayer.getWINDOW_WIDTH() - 300) &&
                (keyInput.getMouseY() < renderLayer.getWINDOW_HEIGHT() - 10) &&
                !(keyInput.getMouseX() < 0) && !(keyInput.getMouseY() < 0)) {
            outOfBounds = false;
        }
    }

    public void render(Graphics g) {
        if (!outOfBounds) {
            g.setColor(Color.ORANGE);
            g.drawRect(blockCoordinateX * world.getTILE_WIDTH()
                            - (int) renderLayer.getGame().getGame().getWorld().getGameCamera().getxOffset(),
                    blockCoordinateY * world.getTILE_HEIGHT()
                            - (int) renderLayer.getGame().getGame().getWorld().getGameCamera().getyOffset(),
                    world.getTILE_WIDTH(), world.getTILE_HEIGHT());
        }
    }

    public int getTileToInsert() {
        return tileToInsert;
    }

    public void setTileToInsert(int tileToInsert) {
        this.tileToInsert = tileToInsert;
    }

    public int getblockCoordinateX() {
        return blockCoordinateX;
    }

    public int getblockCoordinateY() {
        return blockCoordinateY;
    }
}
