/**
 * Created by andreashammer on 16/01/2017.
 */
package xyz.hammerprod.RPG.inventory;

import xyz.hammerprod.RPG.item.ItemStack;

public class Inventory {
    private ItemStack[][] items;

    public Inventory(int sx, int sy){
        items = new ItemStack[sx][sy];
    }

    public ItemStack getItem(int x, int y){
        return items[x][y];
    }

    public ItemStack[][] getItems(){
        return items;
    }

    public void setItem(ItemStack item, int x, int y){
        if(x < 0 || x >= this.items.length || y < 0 || y >= this.items[x].length)
            return;
        this.items[x][y] = item;
    }

    public boolean addItem(ItemStack item){
        for(int y = 0; y < items[0].length; y++){
            for(int x = 0; x < items.length; x++){
                if(items[x][y] != null) {
                    items[x][y].merge(item);
                    if (item.getStackSize() == 0)
                        return true;
                }
            }
        }
        for(int y = 0; y < items[0].length; y++){
            for(int x = 0; x < items.length; x++){
                if(items[x][y] == null) {
                    items[x][y] = item;
                    return true;
                }
            }
        }
        return false;
    }
}
