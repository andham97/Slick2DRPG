/**
 * Created by andreashammer on 16/01/2017.
 */
package xyz.hammerprod.RPG.item;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import xyz.hammerprod.RPG.Main;

public abstract class Item {
    public static String itemPath = new String("data/res/items/");
    public static Item GOLD = new ItemGold("Gold", 0.005f, Integer.MAX_VALUE, "gold.png");

    public static Item getItemFromString(String name){
        switch(name){
            case "GOLD":
                return Item.GOLD;
        }
        return null;
    }

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
            this.texture = new Image(Item.itemPath + texturePath);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public Item(String name, float weight, boolean stackable, String texturePath){
        this.name = name;
        this.weight = weight;
        this.stackable = stackable;
        try {
            this.texture = new Image(Item.itemPath + texturePath);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public Item(String name, float weight, String texturePath){
        this.name = name;
        this.weight = weight;
        try {
            this.texture = new Image(Item.itemPath + texturePath);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public void render(int x, int y){
        this.texture.draw(x + ((Main.TILE_SIZE - this.texture.getWidth()) / 2), y + ((Main.TILE_SIZE - this.texture.getHeight()) / 2));
    }

    public String getName() {
        return name;
    }

    public float getWeight() {
        return weight;
    }

    public boolean isStackable() {
        return stackable;
    }

    public int getMaxStackSize() {
        return maxStackSize;
    }
}
