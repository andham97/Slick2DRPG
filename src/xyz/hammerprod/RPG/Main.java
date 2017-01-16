package xyz.hammerprod.RPG;

import org.newdawn.slick.*;

public class Main {
    public static final int TILE_SIZE = 32;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 640;

    private static final String TITLE = "Game";

    public static void main(String[] args){
        try {
            AppGameContainer a = new AppGameContainer(new Game(TITLE));
            a.setDisplayMode(WIDTH, HEIGHT, false);
            a.setVSync(true);
            a.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public static void sort(){
        int[][] tables = new int[][]{{2, 7}, {4, 0}, {6, 2}, {2, 0}};
        int[][] newTable = new int[tables.length][2];
        for(int j = 0; j < tables.length; j++) {
            int index = -1;
            int amtFreeTables = 0;
            int tempI = 0;
            for (int i = 0; i < tables.length; i++) {
                if (tables[i][0] > amtFreeTables) {
                    amtFreeTables = tables[i][0];
                    index = tables[i][1];
                    tempI = i;
                }
            }
            newTable[j] = new int[]{amtFreeTables, index};
            tables[tempI] = new int[]{-1, -1};
        }
    }
}
