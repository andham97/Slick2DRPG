/**
 * Created by andreashammer on 17/01/2017.
 */
package xyz.hammerprod.RPG.gui;

import jdk.nashorn.internal.runtime.Timing;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import xyz.hammerprod.RPG.util.TimingState;

public class GUIManager {
    private boolean visible;
    private GUI currentGUI;
    private TimingState tState;

    public GUIManager(){
        this.tState = new TimingState(200);
    }

    public void activateGUI(GUI g){
        this.visible = true;
        this.currentGUI = g;
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

    public void render(){
        if(!this.visible)
            return;
        this.currentGUI.render();
    }

    public void revoke() {
        if(!this.tState.finished())
            return;
        this.visible = false;
        this.currentGUI = null;
    }
}
