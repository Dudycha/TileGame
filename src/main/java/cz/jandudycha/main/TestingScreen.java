package cz.jandudycha.main;

import cz.jandudycha.main.texture.Assets;
import cz.jandudycha.main.ui.ClickListener;
import cz.jandudycha.main.ui.UIImageButton;
import cz.jandudycha.main.ui.UIManager;
import cz.jandudycha.main.ui.UISimpleImageButton;

import java.awt.*;

public class TestingScreen {

    private final KeyInput keyInput;
    private final RenderLayer renderLayer;
    private final UIManager uiManager;

    public TestingScreen(KeyInput keyInput, final RenderLayer renderLayer) {
        this.keyInput = keyInput;
        this.renderLayer = renderLayer;
        uiManager = new UIManager();
        keyInput.setUIManager(uiManager);

        for (int i = 0; i < Assets.tileTextures.length; i++) {
            uiManager.addObject(new UISimpleImageButton(1180, i * 31, 30, 30, Assets.tileTextures[i], i, new ClickListener() {
                @Override
                public void onClick() {

                }
            }));
        }


        uiManager.addObject(new UIImageButton(1020, 130, 64, 32, Assets.saveBtn, new ClickListener() {
            @Override
            public void onClick() {
                renderLayer.getGame().getGame().getWorld().saveMap();
            }
        }));
    }


    public void update() {
        renderLayer.getGame().getGame().getWorld().getMapEditor().setTileToInsert(uiManager.getSelectedID());
        uiManager.update();

    }

    public void render(Graphics g) {
        g.setColor(renderLayer.getForeground());
        g.fillRect(renderLayer.getWINDOW_WIDTH() - 250, 0, 250, renderLayer.getWINDOW_HEIGHT());
        g.setColor(Color.black);
        g.fillRect(renderLayer.getWINDOW_WIDTH() - 250, 0, 2, renderLayer.getWINDOW_HEIGHT());


        g.setColor(Color.green);
        g.drawString("mouse X: " + keyInput.getMouseX(), 1020, 20);
        g.drawString("mouse Y: " + keyInput.getMouseY(), 1020, 35);


        g.drawString("coordinate X: " + renderLayer.getGame().getGame().getWorld().getMapEditor().getblockCoordinateX(), 1020, 60);
        g.drawString("coordinate Y: " + renderLayer.getGame().getGame().getWorld().getMapEditor().getblockCoordinateY(), 1020, 75);


        g.setColor(Color.green);
        g.drawString("" + renderLayer.getGame().getGame().getWorld().getxStart(), 1050, 250);
        g.drawString("" + renderLayer.getGame().getGame().getWorld().getxEnd(), 1100, 250);

        g.drawString("" + renderLayer.getGame().getGame().getWorld().getyStart(), 1075, 220);
        g.drawString("" + renderLayer.getGame().getGame().getWorld().getyEnd(), 1075, 280);


        uiManager.render(g);
    }
}
