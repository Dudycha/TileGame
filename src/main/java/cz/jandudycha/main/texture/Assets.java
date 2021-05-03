package cz.jandudycha.main.texture;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Assets {
    private static final int x = 32, y = 32;

    public static Font myFont = new Font("myFont", Font.BOLD, 22);

    public static BufferedImage[] tileTextures = new BufferedImage[50];
    public static BufferedImage[] checkBox = new BufferedImage[2];

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
    }

    private static void cropUI(SpriteSheet UISheet) {
        checkBox[0] = UISheet.crop(0, y * 11);
        checkBox[1] = UISheet.crop(x, y * 11);
    }


}

