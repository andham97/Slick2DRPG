/**
 * Created by andreashammer on 13/01/2017.
 */
package xyz.hammerprod.RPG.map;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import java.util.HashMap;

public class Map {
    private TiledMap map;
    private HashMap<Integer, HashMap<String, String>> props = new HashMap<Integer, HashMap<String, String>>();
    private int entityLayer = -1;

    public Map(String name){
        try {
            map = new TiledMap(name);
            this.entityLayer = Integer.parseInt(map.getMapProperty("entityLayer", "-1"));

        }
        catch(SlickException e){
            e.printStackTrace();
        }
    }

    public int getEntityLayer(){
        return this.entityLayer;
    }

    public void render(){
        map.render(0, 0);
    }

    public int getTileID(int x, int y, int i){
        return map.getTileId(x, y, i);
    }

    public String getTileProperty(int id, String property){
        if(props.get(id) != null && props.get(id).get(property) != null){
            return props.get(id).get(property);
        }
        String val = map.getTileProperty(id, property, "false");
        HashMap<String, String> p = new HashMap<String, String>();
        p.put(property, val);
        props.put(id, p);
        return val;
    }

    public int getLayerCount(){
        return map.getLayerCount();
    }
}
