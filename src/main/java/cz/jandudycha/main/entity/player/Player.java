package cz.jandudycha.main.entity.player;

import cz.jandudycha.main.RenderLayer;
import cz.jandudycha.main.entity.Entity;
import cz.jandudycha.main.states.Game;
import cz.jandudycha.main.texture.Assets;

import java.awt.*;

public class Player extends Entity {


    private final PlayerMovemet playerMovemet;

    public Player(RenderLayer renderLayer, Game game, int x, int y) {
        super(renderLayer, game, x, y);
        playerMovemet = new PlayerMovemet(game, this);
    }


    @Override
    public void update() {
        playerMovemet.update();
        getGame().getWorld().getGameCamera().centerOnEntity(this);
    }


    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.playerRight[0], getX() -(int) getGame().getWorld().getGameCamera().getxOffset(), getY()-(int) getGame().getWorld().getGameCamera().getyOffset(), 64, 64, null);
    }
}
