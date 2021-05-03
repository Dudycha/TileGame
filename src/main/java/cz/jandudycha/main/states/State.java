package cz.jandudycha.main.states;


import cz.jandudycha.main.KeyInput;
import cz.jandudycha.main.RenderLayer;

import java.awt.*;


public abstract class State {

    private static State currentState = null;

    protected RenderLayer renderLayer;
    protected KeyInput keyInput;

    public State(RenderLayer renderLayer, KeyInput keyInput) {
        this.renderLayer = renderLayer;
        this.keyInput = keyInput;
    }

    public static void setState(State state) {
        currentState = state;
    }

    public static State getState() {
        return currentState;
    }


    public abstract void update();

    public abstract void render(Graphics g);

    public abstract Game getGame();


}
