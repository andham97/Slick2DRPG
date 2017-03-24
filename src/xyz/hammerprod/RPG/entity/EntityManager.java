/**
 * Created by andreashammer on 16/01/2017.
 */
package xyz.hammerprod.RPG.entity;

import org.newdawn.slick.GameContainer;
import xyz.hammerprod.RPG.util.WorldPos;

import java.util.ArrayList;

public class EntityManager {
    private ArrayList<Entity> staticEntities = new ArrayList<Entity>();
    private ArrayList<EntityDynamic> dynamicEntities = new ArrayList<EntityDynamic>();
    private EntityPlayer playerRef;

    public EntityManager(){
        playerRef = (EntityPlayer) EntityCreator.createEntity("player");
        dynamicEntities.add(playerRef);
    }

    public void registerStaticEntity(Entity e){
        staticEntities.add(e);
    }

    public void registerDynamicEntity(EntityDynamic e){
        dynamicEntities.add(e);
    }

    public void reset(){
        dynamicEntities.clear();
        staticEntities.clear();
        dynamicEntities.add(playerRef);
    }

    public void update(GameContainer container, float delta){
        for(Entity e : staticEntities){
            e.update(container, delta);
        }
        for(Entity e : dynamicEntities){
            e.update(container, delta);
        }
    }

    public void render(){
        for(Entity e : staticEntities){
            e.render();
        }
        for(Entity e : dynamicEntities){
            e.render();
        }
    }

    public boolean entityAtPosition(Entity self, WorldPos pos){
        for(Entity e : staticEntities){
            if(e != self){
                if(e.pos.compareTo(pos)) {
                    return true;
                }
            }
        }
        for(Entity e : dynamicEntities){
            if(e != self){
                if(e.pos.compareTo(pos)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void interactWithEntity(WorldPos pos) {
        for(Entity e : staticEntities){
            if(e.pos.compareTo(pos)) {
                e.interact();
                return;
            }
        }
        for(Entity e : dynamicEntities){
            if(e.pos.compareTo(pos)) {
                e.interact();
                return;
            }
        }
    }

    public EntityPlayer getPlayer(){
        return this.playerRef;
    }

    public void revokeEntityGUIAtPosition(WorldPos p) {
        for(Entity e : staticEntities){
            if(e.pos.compareTo(p)){
                e.GUIRevoked();
                return;
            }
        }
        for(Entity e : dynamicEntities){
            if(e.pos.compareTo(p)){
                e.GUIRevoked();
                return;
            }
        }
    }
}
