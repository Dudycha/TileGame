package cz.jandudycha.main.states;


import cz.jandudycha.main.KeyInput;
import cz.jandudycha.main.RenderLayer;
import cz.jandudycha.main.entity.player.Player;
import cz.jandudycha.main.texture.Assets;
import cz.jandudycha.main.ui.*;
import cz.jandudycha.main.world.World;

import java.awt.*;

public class Game extends State {

    private boolean gameOver = false;
    private World world;
    private final KeyInput keyInput;
    private final UIManager uiManager;
    private final RenderLayer renderLayer;
    private final Player player;


    public Game(final RenderLayer renderLayer, final KeyInput keyInput) {
        super(renderLayer, keyInput);
        this.renderLayer = renderLayer;
        Assets.init();
        this.keyInput = keyInput;
        world = new World(keyInput, renderLayer);
        player = new Player(renderLayer, this, 0, 0);
        uiManager = new UIManager();
        keyInput.setUIManager(uiManager);




    }

    public void update() {

        player.update();

        if (!gameOver) {
            world.update();
        }
        uiManager.update();
    }


    public void render(Graphics g) {
        if (!gameOver) {
            world.render(g);
        }
        player.render(g);
        uiManager.render(g);
    }

    public World getWorld() {
        return world;
    }

    @Override
    public Game getGame() {
        return this;
    }

    public KeyInput getKeyInput() {
        return keyInput;
    }
}









