package cz.jandudycha.main.texture;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Assets {
    private static final int x = 32, y = 32;

    public static Font myFont = new Font("myFont", Font.BOLD, 22);

    public static BufferedImage[] tileTextures = new BufferedImage[50];
    public static BufferedImage[] checkBox = new BufferedImage[4];
    public static BufferedImage[] saveBtn = new BufferedImage[2];

    public static void init() {
        SpriteSheet tileSheet = new SpriteSheet(ImageLoader.loadImage("/textures/tileSheet.png"), 32, 32);
        SpriteSheet UISheet = new SpriteSheet(ImageLoader.loadImage("/textures/UISheet.png"), 32, 32);

        cropTiles(tileSheet);
        cropUI(UISheet);


    }

    private static void cropTiles(SpriteSheet tileSheet) {
        tileTextures[0] = tileSheet.crop(0, 0);//stone
        tileTextures[1] = tileSheet.crop(x, 0);//stone
        tileTextures[2] = tileSheet.crop(x * 2, 0);//gravel
        tileTextures[3] = tileSheet.crop(0, y);//grass
        tileTextures[4] = tileSheet.crop(x, y);//grass
        //sky
        tileTextures[5] = tileSheet.crop(0, y * 2);
        tileTextures[6] = tileSheet.crop(0, y * 3);
        tileTextures[7] = tileSheet.crop(0, y * 4);
        tileTextures[8] = tileSheet.crop(0, y * 5);
        tileTextures[9] = tileSheet.crop(0, y * 6);
        tileTextures[10] = tileSheet.crop(0, y * 7);
        tileTextures[11] = tileSheet.crop(0, y * 8);

        tileTextures[12] = tileSheet.crop(x * 2, y);//dirt

        tileTextures[13] = tileSheet.crop(x * 3, 0);
        tileTextures[14] = tileSheet.crop(x * 4, 0);
        tileTextures[15] = tileSheet.crop(x * 5, 0);
        tileTextures[16] = tileSheet.crop(x * 5, 0);
    }

    private static void cropUI(SpriteSheet UISheet) {
        checkBox[0] = UISheet.crop(0, y * 11);
        checkBox[1] = UISheet.crop(x, y * 11);
        checkBox[2] = UISheet.crop(x * 2, y * 11);
        checkBox[3] = UISheet.crop(x * 3, y * 11);

        saveBtn[0] = UISheet.crop(0, y * 12);
        saveBtn[1] = UISheet.crop(x, y * 12);
    }


}

