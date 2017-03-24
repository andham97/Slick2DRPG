/**
 * Created by andreashammer on 17/01/2017.
 */
package xyz.hammerprod.RPG.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import xyz.hammerprod.RPG.GameManager;
import xyz.hammerprod.RPG.util.TimingState;
import xyz.hammerprod.RPG.util.WorldPos;

public class GUIManager {
    private boolean visible;
    private boolean startup = true;
    private GUI currentGUI;
    private WorldPos currentGUIPosition;
    private TimingState tState;

    public GUIManager(){
        this.tState = new TimingState(200);
    }

    public void activateGUI(GUI g, WorldPos p){
        this.visible = true;
        this.currentGUI = g;
        this.currentGUIPosition = p;
    }

    public boolean isVisible() {
        return visible;
    }

    public void update(GameContainer container, float delta){
        if(!this.visible)
            return;
        this.tState.tick(delta);
        Input i = container.getInput();
        if(i.isKeyDown(Input.KEY_ESCAPE)){
            this.revoke();
            return;
        }
        this.currentGUI.update(container, delta);
    }

    public void render(Graphics g){
        if(!this.visible)
            return;
        this.currentGUI.render(g);
    }

    public void revoke() {
        if(!this.tState.finished())
            return;
        this.visible = false;
        this.currentGUI = null;
        if(this.currentGUIPosition != WorldPos.NONE){
            GameManager.revokeEntityGUIAtPosition(this.currentGUIPosition);
        }
    }
}
