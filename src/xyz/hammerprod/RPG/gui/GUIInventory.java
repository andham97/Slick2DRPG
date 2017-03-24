/**
 * Created by andreashammer on 16/01/2017.
 */
package xyz.hammerprod.RPG.gui;

import org.newdawn.slick.*;
import xyz.hammerprod.RPG.GameManager;
import xyz.hammerprod.RPG.Main;
import xyz.hammerprod.RPG.inventory.Inventory;
import xyz.hammerprod.RPG.item.ItemStack;

@SuppressWarnings("Duplicates")
public class GUIInventory extends GUI {

    private Inventory inv;
    protected Inventory bar;
    private Image texture;
    private Image selTex;
    private boolean selected = false;
    private int selectX = -1;
    private int selectY = -1;
    private int slotX = 0;
    private int slotY = 0;
    private int xOffset = 14;
    private int yOffset = 21;
    private int lyOffset = 145;

    public GUIInventory(Inventory inv){
        this.inv = inv;
        this.bar = GameManager.getActiveBar();
        try {
            this.texture = new Image("data/res/gui/inventory.png");
            this.selTex = new Image("data/res/gui/inv_sel.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(GameContainer container, float delta) {
        Input i = container.getInput();
        if (i.isKeyPressed(Input.KEY_UP) || i.isKeyDown(Input.KEY_W)) {
            this.moveCursor(0, -1);
        }
        if (i.isKeyPressed(Input.KEY_RIGHT) || i.isKeyDown(Input.KEY_D)) {
            this.moveCursor(1, 0);
        }
        if (i.isKeyPressed(Input.KEY_DOWN) || i.isKeyDown(Input.KEY_S)) {
            this.moveCursor(0, 1);
        }
        if (i.isKeyPressed(Input.KEY_LEFT) || i.isKeyDown(Input.KEY_A)) {
            this.moveCursor(-1, 0);
        }
        if(i.isKeyPressed(Input.KEY_SPACE)){
            if(i.isKeyDown(Input.KEY_LSHIFT) || i.isKeyDown(Input.KEY_RSHIFT)){
                appendItem();
            }
            else {
                if (!this.selected) {
                    this.selectX = this.slotX;
                    this.selectY = this.slotY;
                    this.selected = true;
                } else {
                    this.moveItem();
                }
            }
        }
    }

    private void appendItem(){
        if(this.slotY < this.inv.getItems()[this.slotX].length) {
            this.bar.addItem(this.inv.getItem(this.slotX, this.slotY));
            this.inv.setItem(null, this.slotX, this.slotY);
        }
        else {
            this.inv.addItem(this.bar.getItem(this.slotX, 0));
            this.bar.setItem(null, this.slotX, 0);
        }
    }

    private void moveItem(){
        // From inventory
        if(this.selectY < this.bar.getItems()[selectX].length + this.inv.getItems()[this.selectX].length - 1){
            ItemStack sel = this.inv.getItem(this.selectX, this.selectY);
            // To inventory
            if(this.slotY < this.bar.getItems()[this.slotX].length + this.inv.getItems()[this.slotX].length - 1) {
                ItemStack cur = this.inv.getItem(this.slotX, this.slotY);
                if(cur != null && sel != null && sel != null && cur != sel && cur.getItem() == sel.getItem()){
                    cur.merge(sel);
                    if(sel.getStackSize() == 0)
                        sel = null;
                    this.inv.setItem(sel, this.selectX, this.selectY);
                    this.inv.setItem(cur, this.slotX, this.slotY);
                }
                else {
                    this.inv.setItem(cur, this.selectX, this.selectY);
                    this.inv.setItem(sel, this.slotX, this.slotY);
                }
            }
            // To active bar
            else {
                ItemStack cur = this.bar.getItem(this.slotX, 0);
                if(cur != null && sel != null && cur != sel && cur.getItem() == sel.getItem()){
                    cur.merge(sel);
                    if(sel.getStackSize() == 0)
                        sel = null;
                    this.inv.setItem(sel, this.selectX, this.selectY);
                    this.bar.setItem(cur, this.slotX, 0);
                }
                else {
                    this.inv.setItem(cur, this.selectX, this.selectY);
                    this.bar.setItem(sel, this.slotX, 0);
                }
            }
        }
        // From active bar
        else {
            ItemStack sel = this.bar.getItem(this.selectX, 0);
            // To inventory
            if(this.slotY < this.bar.getItems()[this.slotX].length + this.inv.getItems()[this.slotX].length - 1) {
                ItemStack cur = this.inv.getItem(this.slotX, this.slotY);
                if(cur != null && sel != null && cur != sel && cur.getItem() == sel.getItem()){
                    cur.merge(sel);
                    if(sel.getStackSize() == 0)
                        sel = null;
                    this.bar.setItem(sel, this.selectX, 0);
                    this.inv.setItem(cur, this.slotX, this.slotY);
                }
                else {
                    this.bar.setItem(cur, this.selectX, 0);
                    this.inv.setItem(sel, this.slotX, this.slotY);
                }
            }
            // To active bar
            else {
                ItemStack cur = this.bar.getItem(this.slotX, 0);
                if(cur != null && sel != null && cur != sel && cur.getItem() == sel.getItem()){
                    cur.merge(sel);
                    if(sel.getStackSize() == 0)
                        sel = null;
                    this.bar.setItem(cur, this.slotX, 0);
                    this.bar.setItem(sel, this.selectX, 0);
                }
                else {
                    this.bar.setItem(sel, this.slotX, 0);
                    this.bar.setItem(cur, this.selectX, 0);
                }
            }
        }
        this.selected = false;
        this.selectX = -1;
        this.selectY = -1;
    }

    private void moveCursor(int x, int y){
        this.slotX += x;
        this.slotY += y;
        if(this.slotX < 0)
            this.slotX = 0;
        else if(this.slotY != 4 && this.slotX >= this.inv.getItems().length || this.slotY == 4 && this.slotX >= this.bar.getItems().length)
            this.slotX = this.inv.getItems().length - 1;
        if(this.slotY < 0)
            this.slotY = 0;
        else if(this.slotY >= this.inv.getItems()[0].length + this.bar.getItems()[0].length)
            this.slotY = this.inv.getItems()[0].length - 1 + this.bar.getItems()[0].length;
    }

    @Override
    public void render(Graphics g) {
        int dx = Main.WIDTH / 2;
        dx -= this.texture.getWidth() / 2;
        int dy = Main.HEIGHT / 2;
        dy -= this.texture.getHeight() / 2;
        this.texture.draw(dx, dy);
        for(int x = 0; x < this.inv.getItems().length; x++){
            for(int y = 0; y < this.inv.getItems()[x].length; y++){
                ItemStack i = this.inv.getItem(x, y);
                if(i != null){
                    i.render(g, xOffset + (x * 4) + dx + (x * Main.TILE_SIZE), yOffset + (y * 3) + dy + (y * Main.TILE_SIZE));
                }
                if(x == this.slotX && y == this.slotY){
                    this.selTex.draw(xOffset + (x * 4) + dx + (x * Main.TILE_SIZE), yOffset + (y * 3) + dy + (y * Main.TILE_SIZE));
                }
            }
        }
        for(int x = 0; x < this.bar.getItems().length; x++){
            int y = this.bar.getItems()[x].length - 1;
            ItemStack i = this.bar.getItem(x, y);
            if(i != null){
                i.render(g, xOffset + (x * 4) + dx + (x * Main.TILE_SIZE), lyOffset + dy);
            }
            y += this.inv.getItems()[x].length;
            if(x == this.slotX && y == this.slotY){
                this.selTex.draw(xOffset + (x * 4) + dx + (x * Main.TILE_SIZE), lyOffset + dy);
            }
        }
        if(this.selected){
            int x = this.selectX, y = this.selectY;
            if(y < this.bar.getItems()[selectX].length + this.inv.getItems()[x].length - 1){
                this.selTex.draw(xOffset + (x * 4) + dx + (x * Main.TILE_SIZE), yOffset + (y * 3) + dy + (y * Main.TILE_SIZE));
            }
            else {
                this.selTex.draw(xOffset + (x * 4) + dx + (x * Main.TILE_SIZE), lyOffset + dy);
            }
        }
    }
}
