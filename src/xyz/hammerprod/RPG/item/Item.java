/**
 * Created by andreashammer on 16/01/2017.
 */
package xyz.hammerprod.RPG.item;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import xyz.hammerprod.RPG.Main;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class Item {
    public static String itemPath = new String("data/res/items/");
    public static final Map<String, Item> items;
    static {
        final Map<String, Item> i = new HashMap<>();
        i.put("GOLD", new ItemGold("Gold", 0.005f, 999, "gold.png"));
        i.put("STEEL_SWORD", new ItemSteelSword("Steel sword", 3f, false, "steel_sword.png"));
        i.put("APPLE", new ItemApple("Apple", 0.5f, 64, "apple.png"));
        i.put("CHEESE", new ItemCheese("Cheese", 1.0f, 64, "cheese.png"));
        i.put("FISH", new ItemFish("Fish", 1.0f, 64, "fish.png"));
        i.put("PORK", new ItemPork("Pork", 1.0f, 64, "pork.png"));
        i.put("CAKE", new ItemCake("Cake", 1.0f, 64, "cake.png"));
        items = Collections.unmodifiableMap(i);
    }

    public static Item getItemFromString(String name){
        return items.get(name);
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
