package cz.jandudycha.main;


import cz.jandudycha.main.states.Game;
import cz.jandudycha.main.states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class RenderLayer extends Canvas implements Runnable {
    private final int WINDOW_WIDTH = 1250;
    private final int WINDOW_HEIGHT = 650;
    private boolean isRunning = false;
    private final KeyInput keyInput;
    private final TestingScreen testingScreen;

    //States
    private State game;


    public RenderLayer() {
        this.setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        keyInput = new KeyInput();
        newGame();
        State.setState(game);
        testingScreen = new TestingScreen(keyInput, this);


        this.addKeyListener(keyInput);
        this.addMouseListener(keyInput);
        this.addMouseMotionListener(keyInput);
        this.addMouseWheelListener(keyInput);


    }

    public void newGame() {
        game = new Game(this, keyInput);

    }

    @Override
    public void run() {
        long lastTimeCycle = System.nanoTime();
        long lastTimeOutput = System.currentTimeMillis();
        double unprocessedTicks = 0;
        double nsPerTick = Math.pow(10, 9) / 60;
        int tick = 0;
        int FPS = 0;


        while (isRunning) {
            long nowTimeCycle = System.nanoTime();
            unprocessedTicks += (nowTimeCycle - lastTimeCycle) / nsPerTick;
            lastTimeCycle = nowTimeCycle;
            while (unprocessedTicks >= 1) {
                tick++;
                unprocessedTicks--;
                update();
            }
            FPS++;
            render();
            if (System.currentTimeMillis() - lastTimeOutput > 1000) {
                lastTimeOutput += 1000;
                System.out.println("Ticks:" + tick + " FPS:" + FPS);
                FPS = 0;
                tick = 0;
            }
        }
    }


    private void update() {
        keyInput.update();

        if (State.getState() != null) {
            State.getState().update();
        }
        testingScreen.update();
    }

    private void render() {
        BufferStrategy buffer = this.getBufferStrategy();
        if (buffer == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = buffer.getDrawGraphics();
        g.setColor(this.getForeground());
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        if (State.getState() != null) {
            State.getState().render(g);

        }

        testingScreen.render(g);
        g.dispose();
        buffer.show();
    }


    public void start() {
        isRunning = true;
        Thread t = new Thread(this);
        t.start();
    }

    public int getWINDOW_WIDTH() {
        return WINDOW_WIDTH;
    }

    public int getWINDOW_HEIGHT() {
        return WINDOW_HEIGHT;
    }

    public KeyInput getKeyInput() {
        return keyInput;
    }

    public State getGame() {
        return game;
    }
}
