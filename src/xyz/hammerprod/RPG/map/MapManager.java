/**
 * Created by andreashammer on 13/01/2017.
 */
package xyz.hammerprod.RPG.map;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

public class MapManager {
    private HashMap<String, Map> maps = new HashMap<String, Map>();
    private String dir = "data/maps";
    private String currentMap;

    public MapManager(){
        Iterator<File> i = FileUtils.iterateFiles(new File(dir), new String[] {"tmx"}, true);
        while(i.hasNext()){
            File f = (File) i.next();
            String name = f.getPath();
            Map m = new Map(name);
            name = name.split(".tmx")[0];
            maps.put(name, m);
            System.out.println(name);
        }
    }

    public void render(){
        maps.get(currentMap).render();
    }

    public void teleport(String newMap){
        this.currentMap = newMap;
    }

    public int getTileID(int x, int y, int i){
        return maps.get(currentMap).getTileID(x, y, i);
    }

    public int getEntityLayer(){
        return maps.get(currentMap).getEntityLayer();
    }

    public int getLayerCount(){
        return maps.get(currentMap).getLayerCount();
    }

    public String getTileProperty(int id, String property){
        return maps.get(currentMap).getTileProperty(id, property);
    }
}