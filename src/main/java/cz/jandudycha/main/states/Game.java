package cz.jandudycha.main.states;


import cz.jandudycha.main.KeyInput;
import cz.jandudycha.main.RenderLayer;
import cz.jandudycha.main.texture.Assets;
import cz.jandudycha.main.ui.*;
import cz.jandudycha.main.world.World;
import org.w3c.dom.ls.LSOutput;

import java.awt.*;
import java.sql.SQLException;

public class Game extends State {

    private boolean gameOver = false;
    private World world;
    private final KeyInput keyInput;
    private final UIManager uiManager;
    private final RenderLayer renderLayer;


    public Game(final RenderLayer renderLayer, final KeyInput keyInput) {
        super(renderLayer, keyInput);
        this.renderLayer = renderLayer;
        Assets.init();
        this.keyInput = keyInput;
        world = new World(keyInput, renderLayer);
        uiManager = new UIManager();
        keyInput.setUIManager(uiManager);

        for (int i = 0; i < Assets.tileTextures.length; i++) {
            uiManager.addObject(new UISimpleImageButton(1100, i * 31, 30, 30, Assets.tileTextures[i], i, new ClickListener() {
                @Override
                public void onClick() {

                }
            }));
        }
        uiManager.addObject(new UIImageButton(990, 200, 64, 32, Assets.saveBtn, new ClickListener() {
            @Override
            public void onClick() {
                world.saveMap();
            }
        }));


    }

    public void update() {
        world.getMapEditor().setTileToInsert(uiManager.getSelectedID());


        if (!gameOver) {
            world.update();
        }
        uiManager.update();
    }


    public void render(Graphics g) {
        if (!gameOver) {
            world.render(g);
        }
        uiManager.render(g);
    }

    public World getWorld() {
        return world;
    }

    @Override
    public Game getGame() {
        return this;
    }
}









