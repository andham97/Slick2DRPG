/**
 * Created by andreashammer on 16/01/2017.
 */
package xyz.hammerprod.RPG.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import xyz.hammerprod.RPG.util.Animation;
import xyz.hammerprod.RPG.util.TimingState;
import xyz.hammerprod.RPG.util.WorldPos;

public class EntityChest extends Entity {

    public static final int CHEST_CLOSED = 0;
    public static final int CHEST_OPENING = 1;
    public static final int CHEST_OPEN = 2;

    private boolean opening = false;
    private boolean closing = false;
    private TimingState animState;

    public EntityChest(int x, int y, String color){
        this.pos = new WorldPos(x, y);
        this.tState = new TimingState(260);
        this.animState = new TimingState(20);
        this.state = CHEST_CLOSED;
        String filename = "chest_" + color;
        try {
            this.animation[0] = new Animation(new Image[]{new Image("data/res/entity/" + filename + "_closed.png")}, new int[]{100}, false);
            this.animation[1] = new Animation(new SpriteSheet(new Image("data/res/entity/" + filename + "_opening.png"), 32, 32), 300);
            this.animation[2] = new Animation(new Image[]{new Image("data/res/entity/" + filename + "_open.png")}, new int[]{100}, false);
        }
        catch(SlickException e){
            e.printStackTrace();
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
                this.closing = false;
            }
        }
        else if(!this.isOpening() && !this.isClosing()){
            this.animState.tick(delta);
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
            this.state = CHEST_OPENING;
            this.tState.restart();
            this.animation[1].restart();
            this.animation[1].setCurrentFrame(0);
            this.animation[1].setSpeed(1.0f);
        }
        else if(this.state == CHEST_OPEN){
            this.closing = true;
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
}
