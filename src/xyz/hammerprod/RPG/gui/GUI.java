/**
 * Created by andreashammer on 16/01/2017.
 */
package xyz.hammerprod.RPG.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public abstract class GUI {
    public abstract void update(GameContainer container, float delta);

    public abstract void render(Graphics g);
}
