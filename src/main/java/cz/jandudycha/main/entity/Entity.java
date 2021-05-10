package cz.jandudycha.main.entity;


import cz.jandudycha.main.RenderLayer;
import cz.jandudycha.main.states.Game;

import java.awt.*;

public abstract class Entity {

    private final RenderLayer renderLayer;
    private final Game game;
    private int x;
    private int y;
    private int hitpoints = 9;

    private int VELOCITY =5;
    private int GRAVITY = 3;

    public Entity(RenderLayer renderLayer,Game game, int x, int y) {
        this.renderLayer = renderLayer;
        this.game = game;
        this.x = x;
        this.y = y;
    }

    public abstract void update();

    public abstract void render(Graphics g);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(int hitpoints) {
        this.hitpoints = hitpoints;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setVELOCITY(int VELOCITY) {
        this.VELOCITY = VELOCITY;
    }


    public int getVELOCITY() {
        return VELOCITY;
    }

    public int getGRAVITY() {
        return GRAVITY;
    }

    public RenderLayer getRenderLayer() {
        return renderLayer;
    }

    public Game getGame() {
        return game;
    }
}
