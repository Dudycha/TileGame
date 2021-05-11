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

    public void checkBlankSpace() {
        if (xOffset < 0) {
            xOffset = 0;
        } else if (xOffset > renderLayer.getGame().getGame().getWorld().getSqlManager().getWorldMap()[0].length *
                renderLayer.getGame().getGame().getWorld().getTILE_WIDTH() - renderLayer.getWINDOW_WIDTH() + 250) {
            xOffset = renderLayer.getGame().getGame().getWorld().getSqlManager().getWorldMap()[0].length *
                    renderLayer.getGame().getGame().getWorld().getTILE_WIDTH() - renderLayer.getWINDOW_WIDTH() + 250;
        }
        if (yOffset < 0) {
            yOffset = 0;
        } else if (yOffset > renderLayer.getGame().getGame().getWorld().getSqlManager().getWorldMap().length *
                renderLayer.getGame().getGame().getWorld().getTILE_HEIGHT() -
                renderLayer.getWINDOW_HEIGHT()) {

            yOffset = renderLayer.getGame().getGame().getWorld().getSqlManager().getWorldMap().length *
                    renderLayer.getGame().getGame().getWorld().getTILE_HEIGHT() -
                    renderLayer.getWINDOW_HEIGHT();
        }


    }


    public void move(float xAmt, float yAmt) {
        xOffset += xAmt;
        yOffset += yAmt;
        checkBlankSpace();
    }

    public void centerOnEntity(Entity e) {
        xOffset = e.getX() - renderLayer.getWINDOW_WIDTH() / 2 + 125;
        yOffset = e.getY() - renderLayer.getWINDOW_HEIGHT() / 2;
        checkBlankSpace();
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
