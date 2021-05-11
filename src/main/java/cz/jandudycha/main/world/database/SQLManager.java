package cz.jandudycha.main.world.database;

import cz.jandudycha.main.RenderLayer;
import cz.jandudycha.main.world.SQLiteDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLManager {

    private final RenderLayer renderLayer;
    private int[][] worldMap = new int[30][120];
    private final SQLiteDatabase sql = new SQLiteDatabase();
    private String worldMapSQLdata;

    public SQLManager(RenderLayer renderLayer) {
        this.renderLayer = renderLayer;
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

    public int[][] getWorldMap() {
        return worldMap;
    }
}
