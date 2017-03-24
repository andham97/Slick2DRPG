/**
 * Created by andreashammer on 16/01/2017.
 */
package xyz.hammerprod.RPG.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import xyz.hammerprod.RPG.GameManager;
import xyz.hammerprod.RPG.gui.GUIChest;
import xyz.hammerprod.RPG.gui.GUIInventory;
import xyz.hammerprod.RPG.inventory.Inventory;
import xyz.hammerprod.RPG.item.Item;
import xyz.hammerprod.RPG.item.ItemGold;
import xyz.hammerprod.RPG.item.ItemStack;
import xyz.hammerprod.RPG.util.Animation;
import xyz.hammerprod.RPG.util.TimingState;
import xyz.hammerprod.RPG.util.WorldPos;

public class EntityChest extends Entity {

    public static final int CHEST_CLOSED = 0;
    public static final int CHEST_OPENING = 1;
    public static final int CHEST_OPEN = 2;
    private int lastStaticState;

    private boolean opening = false;
    private boolean closing = false;
    private TimingState animState;
    private Inventory inventory;

    public EntityChest(int x, int y, String color, String inventoryString){
        this.pos = new WorldPos(x, y);
        this.tState = new TimingState(500);
        this.animState = new TimingState(20);
        this.state = CHEST_CLOSED;
        this.lastStaticState = CHEST_CLOSED;
        this.inventory = new Inventory(9, 3);
        String filename = "chest_" + color;
        try {
            this.animation[0] = new Animation(new Image[]{new Image("data/res/entity/" + filename + "_closed.png")}, new int[]{100}, false);
            this.animation[1] = new Animation(new SpriteSheet(new Image("data/res/entity/" + filename + "_opening.png"), 32, 32), 300);
            this.animation[2] = new Animation(new Image[]{new Image("data/res/entity/" + filename + "_open.png")}, new int[]{100}, false);
        }
        catch(SlickException e){
            e.printStackTrace();
        }
        if(!inventoryString.equals("")){
            String[] ss = inventoryString.split("; ");
            for(String s : ss){
                String[] sss = s.split(":");
                System.out.println(inventoryString);
                int amt = Integer.parseInt(sss[1]);
                while(amt > 0) {
                    ItemStack stack = new ItemStack(Item.getItemFromString(sss[0]), amt);
                    amt -= stack.getItem().getMaxStackSize();
                    this.inventory.addItem(stack);
                }
            }
        }
    }

    public void updateState(int value){
        if(value < CHEST_CLOSED || value > CHEST_OPEN){
            return;
        }
        this.state = value;
    }

    public boolean isOpening(){
        return opening;
    }

    public boolean isClosing(){
        return closing;
    }

    @Override
    public void update(GameContainer container, float delta) {
        if(this.isOpening()){
            this.tState.tick(delta);
            if(this.tState.finished()){
                this.state = CHEST_OPEN;
                this.opening = false;
            }
        }
        else if(this.isClosing()){
            this.tState.tick(delta);
            if(this.tState.finished()){
                this.state = CHEST_CLOSED;
                this.lastStaticState = this.state;
                this.closing = false;
            }
        }
        else if(!this.isOpening() && !this.isClosing()){
            this.animState.tick(delta);
            if(this.state != this.lastStaticState){
                this.lastStaticState = this.state;
                GameManager.registerGUI(new GUIChest(this.inventory), this.pos);
            }
        }
    }

    @Override
    public void interact() {
        if(this.isClosing() || this.isOpening())
            return;
        if(!this.animState.finished())
            return;
        if(this.state == CHEST_CLOSED){
            this.opening = true;
            this.lastStaticState = this.state;
            this.state = CHEST_OPENING;
            this.tState.restart();
            this.animation[1].restart();
            this.animation[1].setCurrentFrame(0);
            this.animation[1].setSpeed(1.0f);
        }
        else if(this.state == CHEST_OPEN){
            this.closing = true;
            this.lastStaticState = this.state;
            this.state = CHEST_OPENING;
            this.tState.restart();
            this.animation[1].restart();
            this.animation[1].setCurrentFrame(1);
            this.animation[1].setSpeed(-1.0f);
        }
    }

    @Override
    public boolean isStatic() {
        return true;
    }

    @Override
    public void GUIRevoked() {
        this.closing = true;
        this.lastStaticState = this.state;
        this.tState.restart();
        this.state = CHEST_OPENING;
        this.animation[1].restart();
        this.animation[1].setCurrentFrame(1);
        this.animation[1].setSpeed(-1.0f);
    }
}
