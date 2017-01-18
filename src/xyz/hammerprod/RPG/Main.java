package xyz.hammerprod.RPG;

import org.newdawn.slick.*;

public class Main {
    public static final int TILE_SIZE = 32;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 640;

    private static final String TITLE = "Game";

    public static void main(String[] args){
        try {
            PrepareMaps.prepare();
            AppGameContainer a = new AppGameContainer(new Game(TITLE));
            a.setDisplayMode(WIDTH, HEIGHT, false);
            a.setVSync(true);
            a.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
