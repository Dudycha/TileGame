package cz.jandudycha.main.ui;


import java.awt.*;
import java.awt.image.BufferedImage;

public class UISimpleImageButton extends UIObject {

    private final BufferedImage image;
    private final ClickListener clicker;
    private final int blockID;

    public UISimpleImageButton(int x, int y, int width, int height, BufferedImage images, int blockID, ClickListener clicker) {
        super(x, y, width, height);
        this.image = images;
        this.clicker = clicker;
        this.blockID = blockID;
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics g) {
        if (isHovering()) {
            g.drawImage(image, x, y, width, height, null);
            g.setColor(Color.orange);
            g.drawRect(x, y, width, height);
        } else {
            g.drawImage(image, x, y, width, height, null);
        }

    }

    @Override
    public void onClick() {
        setSelectedID(blockID);
        clicker.onClick();
    }

}
