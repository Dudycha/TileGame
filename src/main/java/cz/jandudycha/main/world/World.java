package cz.jandudycha.main.world;


import cz.jandudycha.main.KeyInput;
import cz.jandudycha.main.RenderLayer;
import cz.jandudycha.main.texture.Assets;
import cz.jandudycha.main.world.database.SQLManager;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;


public class World {

    private final int TILE_WIDTH = 32;
    private final int TILE_HEIGHT = 32;
    private final SQLManager sqlManager;
    private final KeyInput keyInput;
    private final MapEditor mapEditor;
    private final RenderLayer renderLayer;
    private final GameCamera gameCamera;
    private int xStart, xEnd, yStart, yEnd;


    public World(KeyInput keyInput, RenderLayer renderLayer) {
        this.keyInput = keyInput;
        this.renderLayer = renderLayer;
        this.sqlManager = new SQLManager(renderLayer);
        mapEditor = new MapEditor(sqlManager.getWorldMap(), keyInput, this, renderLayer);
        gameCamera = new GameCamera(renderLayer, 100, 50);

    }


    public void update() {
        mapEditor.update();
    }

    public void render(Graphics g) {
        xStart = (int) Math.max(0, gameCamera.getxOffset() / TILE_WIDTH); // leva strana

        xEnd = Math.min(sqlManager.getWorldMap()[0].length, ((int) gameCamera.getxOffset() + renderLayer.getWINDOW_WIDTH() - 250) / TILE_WIDTH + 1);  // prava strana

        yStart = (int) Math.max(0, gameCamera.getyOffset() / TILE_HEIGHT); // top

        yEnd = Math.min(sqlManager.getWorldMap().length, ((int) gameCamera.getyOffset() + renderLayer.getWINDOW_HEIGHT()) / TILE_HEIGHT + 1);


        for (int i = yStart; i < yEnd; i++) {
            for (int j = xStart; j < xEnd; j++) {
                g.drawImage(Assets.tileTextures[sqlManager.getWorldMap()[i][j]], (j * TILE_WIDTH) - (int) gameCamera.getxOffset(), i * TILE_HEIGHT - (int) gameCamera.getyOffset(), null);
            }
        }


        mapEditor.render(g);
    }


    public int getTILE_WIDTH() {
        return TILE_WIDTH;
    }

    public int getTILE_HEIGHT() {
        return TILE_HEIGHT;
    }

    public MapEditor getMapEditor() {
        return mapEditor;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    public SQLManager getSqlManager() {
        return sqlManager;
    }

    public int getxStart() {
        return xStart;
    }

    public int getxEnd() {
        return xEnd;
    }

    public int getyStart() {
        return yStart;
    }

    public int getyEnd() {
        return yEnd;
    }
}



