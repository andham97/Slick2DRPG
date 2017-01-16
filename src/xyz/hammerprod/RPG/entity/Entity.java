/**
 * Created by andreashammer on 15/01/2017.
 */
package xyz.hammerprod.RPG.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import xyz.hammerprod.RPG.util.Animation;
import xyz.hammerprod.RPG.util.TimingState;
import xyz.hammerprod.RPG.util.WorldPos;

public abstract class Entity {
    public static final int ENTITY_IDLE = 0;
    public static final int ENTITY_RIGHT = 1;
    public static final int ENTITY_DOWN = 2;
    public static final int ENTITY_LEFT = 3;
    public static final int ENTITY_UP = 4;

    protected WorldPos pos;
    protected Animation[] animation = new Animation[5];
    protected TimingState tState;
    protected int state = 0;
    protected boolean moving = false;

    public Entity(){
        this.pos = new WorldPos();
        this.tState = new TimingState(0);
    }

    public boolean isMoving(){
        return this.moving;
    }

    public TimingState getTimingState(){
        return tState;
    }

    public void move(int dx, int dy){
        if(this.isMoving())
            return;
        this.pos.translate(dx, dy);
        this.moving = true;
    }

    public void setState(int value){
        this.state = value;
    }

    public abstract void update(GameContainer container, float delta);

    public abstract void interact();

    public abstract boolean isStatic();

    public void render(){
        this.animation[this.state].draw(pos);
    }
}
