/**
 * Created by andreashammer on 15/01/2017.
 */
package xyz.hammerprod.RPG;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.TextField;
import xyz.hammerprod.RPG.entity.Entity;
import xyz.hammerprod.RPG.entity.EntityCreator;
import xyz.hammerprod.RPG.entity.EntityManager;
import xyz.hammerprod.RPG.entity.EntityPlayer;
import xyz.hammerprod.RPG.map.MapManager;
import xyz.hammerprod.RPG.util.WorldPos;

public class GameManager {
    private static GameManager instance;
    private static EntityManager entityManager;

    private MapManager mapMgr;

    public static int getTileID(int x, int y, int layer){
        return instance.mapMgr.getTileID(x, y, layer);
    }

    public static int getTileID(WorldPos pos, int layer){
        return getTileID((int) pos.getX(), (int) pos.getY(), layer);
    }

    public static boolean entityAtPosition(Entity self, WorldPos pos){
        return instance.entityManager.entityAtPosition(self, pos);
    }

    public static void interactWithEntityAtPosition(WorldPos p){
        instance.entityManager.interactWithEntity(p);
    }

    public static String getTileProperty(int id, String property){
        if(id == 0)
            return "";
        return instance.mapMgr.getTileProperty(id, property);
    }

    public static int getMapLayerCount(){
        return instance.mapMgr.getLayerCount();
    }

    public static void teleport(String newMap){
        instance.loadMap(newMap);
    }

    public GameManager(GameContainer container){
        mapMgr = new MapManager();
        entityManager = new EntityManager();
        loadMap("data/maps/Test");
        instance = this;
    }

    public void loadMap(String newMap){
        mapMgr.teleport(newMap);
        entityManager.reset();
        int i = mapMgr.getEntityLayer();
        if(i == -1)
            return;
        for(int x = 0; x < Main.WIDTH / Main.TILE_SIZE; x++){
            for(int y = 0; y < Main.HEIGHT / Main.TILE_SIZE; y++){
                int id = mapMgr.getTileID(x, y, i);
                if(id != 0){
                    System.out.println("X: " + x + " Y: " + y + " TYPE: " + mapMgr.getTileProperty(id, "type"));
                    String type = mapMgr.getTileProperty(id, "type");
                    String color = mapMgr.getTileProperty(id, "color");
                    if(mapMgr.getTileProperty(id, "static").equals("false"))
                        entityManager.registerDynamicEntity(EntityCreator.createEntity(type, color, x, y));
                    else
                        entityManager.registerStaticEntity(EntityCreator.createEntity(type, color, x, y));
                }
            }
        }
    }

    public void update(GameContainer container, float delta){
        entityManager.update(container, delta);
    }

    public void render(GameContainer container, Graphics g){
        mapMgr.render();
        entityManager.render();
    }
}
