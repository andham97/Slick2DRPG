/**
 * Created by andreashammer on 16/01/2017.
 */
package xyz.hammerprod.RPG.item;

public class ItemStack {
    private Item item;
    private int stackSize;

    public ItemStack(Item item, int ss){
        this.item = item;
        this.stackSize = ss;
    }

    public int getStackSize() {
        return stackSize;
    }

    public void setStackSize(int stackSize) {
        this.stackSize = stackSize;
    }

    public Item getItem() {
        return item;
    }

    public int addItems(int amt){
        if(this.stackSize + amt <= this.item.getMaxStackSize()) {
            this.stackSize += amt;
            return 0;
        }
        else {
            int a = this.item.getMaxStackSize() - this.stackSize;
            this.stackSize = this.item.getMaxStackSize();
            return amt - a;
        }

    }

    public int removeItems(int size){
        if(this.stackSize >= size) {
            this.stackSize -= size;
            return 0;
        }
        else {
            int a = size - this.stackSize;
            this.stackSize = 0;
            return a;
        }
    }

    public boolean merge(ItemStack stack){
        if(stack != null && this.item == stack.getItem()){
            int toMuch = this.addItems(stack.getStackSize());
            stack.setStackSize(toMuch);
            return true;
        }
        else
            return false;
    }

    public void render(int x, int y) {
        this.item.render(x, y);
    }
}
