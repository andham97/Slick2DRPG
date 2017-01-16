/**
 * Created by andreashammer on 15/01/2017.
 */
package xyz.hammerprod.RPG.util;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import xyz.hammerprod.RPG.Main;

public class Animation extends org.newdawn.slick.Animation {
    public void draw(WorldPos pos){
        this.draw(pos.getX() * Main.TILE_SIZE, pos.getY() * Main.TILE_SIZE);
    }

    public Animation(Image[] i, int[] d, boolean autoUpdate){
        super(i, d, autoUpdate);
    }

    public Animation(SpriteSheet s, int d){
        super(s, d);
    }
}
