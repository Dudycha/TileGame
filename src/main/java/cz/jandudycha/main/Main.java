package cz.jandudycha.main;




import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public static void main(String[] args) {
        Main main = new Main();
        main.init();
    }

    private void init() {
        RenderLayer renderLayer = new RenderLayer();
        this.add(renderLayer);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setVisible(true);
        this.setLocation(350,200);
        this.setTitle("-- name --");
        renderLayer.start();
       // this.setIconImage(Assets.titleImage);

    }
}
