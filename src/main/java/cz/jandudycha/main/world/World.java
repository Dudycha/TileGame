package cz.jandudycha.main.world;


import cz.jandudycha.main.KeyInput;
import cz.jandudycha.main.RenderLayer;
import cz.jandudycha.main.texture.Assets;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class World {
    private int[][] worldMap = new int[20][30];
    private final int TILE_WIDTH = 32;
    private final int TILE_HEIGHT = 32;
    private final SQLiteDatabase sql = new SQLiteDatabase();
    private String worldMapSQLdata;
    private final KeyInput keyInput;
    private final MapEditor mapEditor;
    private final RenderLayer renderLayer;

    public World(KeyInput keyInput, RenderLayer renderLayer) {
        this.keyInput = keyInput;
        this.renderLayer = renderLayer;
        mapEditor = new MapEditor(worldMap, keyInput, this,renderLayer);
        try {
            ResultSet rs = sql.displayWorld();
            while (rs.next()) {
                worldMapSQLdata = rs.getString("worldMap");
            }
            fillWorldMap();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        consoleLogWorld();
    }

    private void consoleLogWorld() {
        for (int i = 0; i < worldMap.length; i++) {
            System.out.println();
            for (int j = 0; j < worldMap[0].length; j++) {
                if (worldMap[i][j] < 10) {
                    System.out.print(worldMap[i][j] + "  ");
                } else {
                    System.out.print(worldMap[i][j] + " ");
                }
            }
        }
    }


    public void update() {
        mapEditor.update();
    }

    public void render(Graphics g) {

        for (int i = 0; i < worldMap.length; i++) {
            for (int j = 0; j < worldMap[0].length; j++) {
                g.drawImage(Assets.tileTextures[worldMap[i][j]], j * TILE_WIDTH, i * TILE_HEIGHT, null);
            }
        }
        mapEditor.render(g);
    }

    private void fillWorldMap() {
        int charactersTotal = worldMapSQLdata.length();
        int positionInSQLData = 0;
        int indexX = 0;
        int indexY = 0;

        while (positionInSQLData < charactersTotal) {
            while (worldMapSQLdata.charAt(positionInSQLData) != '\n') {
                StringBuilder sb = new StringBuilder();
                if (worldMapSQLdata.charAt(positionInSQLData) == ',') {
                    positionInSQLData++;
                }
                sb.append(worldMapSQLdata.charAt(positionInSQLData++));
                sb.append(worldMapSQLdata.charAt(positionInSQLData++));
                sb.append(worldMapSQLdata.charAt(positionInSQLData++));
                worldMap[indexY][indexX] = Integer.parseInt(sb.toString());
                indexX++;
            }
            positionInSQLData++;
            indexX = 0;
            indexY++;
        }


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

    public int[][] getWorldMap() {
        return worldMap;
    }
}


