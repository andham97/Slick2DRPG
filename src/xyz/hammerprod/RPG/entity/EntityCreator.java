/**
 * Created by andreashammer on 16/01/2017.
 */
package xyz.hammerprod.RPG.entity;

import org.newdawn.slick.GameContainer;

public class EntityCreator {
    public static Entity createEntity(String type, String color, String invStr, int x, int y){
        switch(type){
            case "chest":
                return new EntityChest(x, y, color, invStr);
            case "player":
                return new EntityPlayer();
        }
        return null;
    }

    public static Entity createEntity(String type, String color, int x, int y){
        return createEntity(type, color, "", x, y);
    }

    public static Entity createEntity(String type, int x, int y){
        return createEntity(type, "", x, y);
    }

    public static Entity createEntity(String type){
        return createEntity(type, 0, 0);
    }
}
