package cz.jandudycha.main.world;


import cz.jandudycha.main.KeyInput;
import cz.jandudycha.main.RenderLayer;
import cz.jandudycha.main.texture.Assets;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;


public class World {
    private int[][] worldMap = new int[30][60];
    private final int TILE_WIDTH = 32;
    private final int TILE_HEIGHT = 32;
    private final SQLiteDatabase sql = new SQLiteDatabase();
    private String worldMapSQLdata;
    private final KeyInput keyInput;
    private final MapEditor mapEditor;
    private final RenderLayer renderLayer;
    private final GameCamera gameCamera;
    private int xStart, xEnd, yStart, yEnd;


    public World(KeyInput keyInput, RenderLayer renderLayer) {
        this.keyInput = keyInput;
        this.renderLayer = renderLayer;
        mapEditor = new MapEditor(worldMap, keyInput, this, renderLayer);
        try {
            ResultSet rs = sql.displayWorld();
            while (rs.next()) {
                worldMapSQLdata = rs.getString("worldMap");
            }
            fillWorldMap();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        gameCamera = new GameCamera(renderLayer, 100, 50);
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
        xStart = (int) Math.max(0, gameCamera.getxOffset() / TILE_WIDTH + 1); // leva strana

        xEnd = Math.min(worldMap[0].length, ((int) gameCamera.getxOffset() + renderLayer.getWINDOW_WIDTH() - 250) / TILE_WIDTH - 2);  // prava strana

        yStart = (int) Math.max(0, gameCamera.getyOffset() / TILE_HEIGHT + 1); // top

        yEnd = Math.min(worldMap.length, ((int) gameCamera.getyOffset() + renderLayer.getWINDOW_HEIGHT()) / TILE_HEIGHT - 2);



        for (int i = yStart; i < yEnd; i++) {
            for (int j = xStart; j < xEnd; j++) {
                g.drawImage(Assets.tileTextures[worldMap[i][j]], (j * TILE_WIDTH) - (int) gameCamera.getxOffset(), i * TILE_HEIGHT - (int) gameCamera.getyOffset(), null);
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

    public void saveMap() {
        StringBuilder prepMap = new StringBuilder();

        for (int i = 0; i < worldMap.length; i++) {
            for (int j = 0; j < worldMap[0].length; j++) {
                if (worldMap[i][j] > 99) {
                    prepMap.append(worldMap[i][j]);
                } else if (worldMap[i][j] > 9) {
                    prepMap.append("0").append(worldMap[i][j]);
                } else {
                    prepMap.append("00").append(worldMap[i][j]);
                }
                if (j < worldMap[0].length - 1) {
                    prepMap.append(",");
                }
            }
            prepMap.append("\n");
        }
        try {
            sql.saveMap(prepMap.toString());
            System.out.println("MAP saved!");
        } catch (Exception e) {
            System.out.println("ERROR saving map");
            e.printStackTrace();
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

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    public int[][] getWorldMap() {
        return worldMap;
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



