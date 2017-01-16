/**
 * Created by andreashammer on 16/01/2017.
 */
package xyz.hammerprod.RPG.item;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Item {
    public static Item GOLD = new Item("Gold", 0.005f, Integer.MAX_VALUE, "gold.png");

    private String name;
    private float weight;
    private boolean stackable = true;
    private int maxStackSize = 64;
    private Image texture;

    public Item(String name, float weight, int maxStackSize, String texturePath){
        this.name = name;
        this.weight = weight;
        this.maxStackSize = maxStackSize;
        try {
            this.texture = new Image(texturePath);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public Item(String name, float weight, boolean stackable, String texturePath){
        this.name = name;
        this.weight = weight;
        this.stackable = stackable;
        try {
            this.texture = new Image(texturePath);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public Item(String name, float weight, String texturePath){
        this.name = name;
        this.weight = weight;
        try {
            this.texture = new Image(texturePath);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public void render(int x, int y){
        this.texture.draw(x, y);
    }
}
