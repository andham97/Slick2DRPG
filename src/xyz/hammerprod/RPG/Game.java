/**
 * Created by andreashammer on 13/01/2017.
 */
package xyz.hammerprod.RPG;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import xyz.hammerprod.RPG.map.MapManager;

public class Game extends BasicGame {

    private GameManager mgr;

    public Game(final String TITLE){
        super(TITLE);
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        mgr = new GameManager(container);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        mgr.update(container, delta);
    }

    @Override
    public void render(GameContainer container, Graphics graphics) throws SlickException {
        //container.setShowFPS(false);
        mgr.render(container, graphics);
    }
}
