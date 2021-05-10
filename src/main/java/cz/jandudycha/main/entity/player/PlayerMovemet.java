package cz.jandudycha.main.entity.player;


import cz.jandudycha.main.states.Game;


public class PlayerMovemet {
    private final Game game;
    private final Player player;


    public PlayerMovemet(Game game, Player player) {
        this.player = player;
        this.game = game;
    }

    public void update() {
        if (game.getKeyInput().isLeft()) {
            player.setX(player.getX() - player.getVELOCITY());
        }

        if (game.getKeyInput().isRight()) {
            player.setX(player.getX() + player.getVELOCITY());
        }

        if(game.getKeyInput().isUp()){
            player.setY(player.getY() - player.getVELOCITY());
        }
        if(game.getKeyInput().isDown()){
            player.setY(player.getY() + player.getVELOCITY());
        }

    }


}
