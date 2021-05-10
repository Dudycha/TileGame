package cz.jandudycha.main.world;

import cz.jandudycha.main.RenderLayer;
import cz.jandudycha.main.entity.Entity;

public class GameCamera {
    private float xOffset, yOffset;
    private final RenderLayer renderLayer;

    public GameCamera(RenderLayer renderLayer, float xOffSet, float yOffset) {
        this.xOffset = xOffSet;
        this.yOffset = yOffset;
        this.renderLayer = renderLayer;
    }

    public void move(float xAmt, float yAmt) {
        xOffset += xAmt;
        yOffset += yAmt;
    }

    public void centerOnEntity(Entity e) {
        xOffset = e.getX() - renderLayer.getWINDOW_WIDTH() /2;
        yOffset = e.getY() - renderLayer.getWINDOW_HEIGHT() /2;
    }

    public float getxOffset() {
        return xOffset;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }
}
