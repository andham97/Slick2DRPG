/**
 * Created by andreashammer on 15/01/2017.
 */
package xyz.hammerprod.RPG.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SpriteSheet;
import xyz.hammerprod.RPG.GameManager;
import xyz.hammerprod.RPG.util.Animation;

import org.newdawn.slick.SlickException;
import xyz.hammerprod.RPG.util.TimingState;
import xyz.hammerprod.RPG.util.WorldPos;

public class EntityPlayer extends Entity {

    private TimingState teleportState;
    public static EntityPlayer instance;

    public EntityPlayer(){
        this.pos = new WorldPos();
        this.pos.setX(5);
        this.pos.setY(7);
        this.tState = new TimingState(320);
        this.teleportState = new TimingState(400);
        try {
            this.animation[0] = new Animation(new SpriteSheet("data/res/entity/player_idle.png", 32, 32), 80);
            this.animation[1] = new Animation(new SpriteSheet("data/res/entity/player_right.png", 32, 32), 80);
            this.animation[2] = new Animation(new SpriteSheet("data/res/entity/player_down.png", 32, 32), 80);
            this.animation[3] = new Animation(new SpriteSheet("data/res/entity/player_left.png", 32, 32), 80);
            this.animation[4] = new Animation(new SpriteSheet("data/res/entity/player_up.png", 32, 32), 80);
        }
        catch(SlickException e){
            e.printStackTrace();
        }
        EntityPlayer.instance = this;
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
        boolean walkable = false;
        String teleport = "";
        int teleportLayer = 0;
        for(int i = 0; i < GameManager.getMapLayerCount(); i++){
            String val = GameManager.getTileProperty(GameManager.getTileID(this.pos, i), "walkable");
            if(!val.equals(""))
                walkable = !val.equals("false");
            val = GameManager.getTileProperty(GameManager.getTileID(this.pos, i), "teleport");
            if(!val.equals("") && !val.equals("false")) {
                teleport = val;
                teleportLayer = i;
            }
        }
        if(!walkable){
            this.pos.translate(-dx, -dy);
            return;
        }
        if(!teleport.equals("")){
            this.teleport(teleport, teleportLayer, dx, dy);
            return;
        }
        if(GameManager.entityAtPosition(this, this.pos)){
            this.pos.translate(-dx, -dy);
            return;
        }
        this.moving = true;
    }

    public void teleport(String teleport, int layer, int dx, int dy){
        if(!this.teleportState.finished()){
            this.pos.translate(-dx, -dy);
            return;
        }
        int x = Integer.parseInt(GameManager.getTileProperty(GameManager.getTileID(this.pos, layer), "x"));
        int y = Integer.parseInt(GameManager.getTileProperty(GameManager.getTileID(this.pos, layer), "y"));
        this.pos.setX(x);
        this.pos.setY(y);
        GameManager.teleport(teleport);
    }

    public void setState(int value){
        this.state = value;
    }

    public void update(GameContainer container, float delta){
        Input i = container.getInput();
        this.teleportState.tick(delta);
        if(this.getTimingState().finished()) {
            this.moving = false;
        }
        if(!this.isMoving()) {
            boolean playerMove = false;
            if (i.isKeyDown(Input.KEY_UP) || i.isKeyDown(Input.KEY_W)) {
                this.setState(Entity.ENTITY_UP);
                this.move(0, -1);
                playerMove = true;
            }
            if (i.isKeyDown(Input.KEY_RIGHT) || i.isKeyDown(Input.KEY_D)) {
                this.setState(Entity.ENTITY_RIGHT);
                this.move(1, 0);
                playerMove = true;
            }
            if (i.isKeyDown(Input.KEY_DOWN) || i.isKeyDown(Input.KEY_S)) {
                this.setState(Entity.ENTITY_DOWN);
                this.move(0, 1);
                playerMove = true;
            }
            if (i.isKeyDown(Input.KEY_LEFT) || i.isKeyDown(Input.KEY_A)) {
                this.setState(Entity.ENTITY_LEFT);
                this.move(-1, 0);
                playerMove = true;
            }
            if (!playerMove) {
                this.setState(Entity.ENTITY_IDLE);
            }
        }
        else{
            this.getTimingState().tick(delta);
        }
        if(this.state != Entity.ENTITY_IDLE && i.isKeyDown(Input.KEY_SPACE)){
            WorldPos p = new WorldPos(this.pos);
            int dx = 0, dy = 0;
            switch (this.state){
                case ENTITY_DOWN:
                    dy = 1;
                    break;
                case ENTITY_UP:
                    dy = -1;
                    break;
                case ENTITY_LEFT:
                    dx = -1;
                    break;
                case ENTITY_RIGHT:
                    dx = 1;
                    break;
            }
            p.translate(dx, dy);
            GameManager.interactWithEntityAtPosition(p);
        }
    }

    @Override
    public void interact() {

    }

    @Override
    public boolean isStatic(){
        return false;
    }

    public void render(){
        if(this.isMoving()){
            WorldPos p = new WorldPos(pos.getLX(), pos.getLY());
            p.translate(
                    WorldPos.calculateAnimationCoord(
                            this.getTimingState().getProgress(),
                            pos.getX() - pos.getLX()),
                    WorldPos.calculateAnimationCoord(
                            this.getTimingState().getProgress(),
                            pos.getY() - pos.getLY()));
            this.animation[this.state].draw(p);
        }
        else
            this.animation[this.state].draw(pos);
    }
}
