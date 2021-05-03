package cz.jandudycha.main.states;


import cz.jandudycha.main.KeyInput;
import cz.jandudycha.main.RenderLayer;
import cz.jandudycha.main.texture.Assets;
import cz.jandudycha.main.ui.ClickListener;
import cz.jandudycha.main.ui.UIImageButton;
import cz.jandudycha.main.ui.UIManager;
import cz.jandudycha.main.world.World;

import java.awt.*;

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
        world = new World(keyInput,renderLayer);
        uiManager = new UIManager();
        keyInput.setUIManager(uiManager);
        uiManager.addObject(new UIImageButton(1100, 200, 50, 50, Assets.checkBox, new ClickListener() {

            @Override
            public void onClick() {
                keyInput.setUIManager(uiManager);
                System.out.println("hi");
            }
        }));
    }

    public void update() {
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









