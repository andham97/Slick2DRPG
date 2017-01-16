/**
 * Created by andreashammer on 15/01/2017.
 */
package xyz.hammerprod.RPG.util;

public class TimingState {
    private float timer = 0;
    private float target = 0;

    public TimingState(float target){
        this.target = target;
    }

    public boolean finished(){
        if(this.timer > this.target){
            this.timer = 0;
            return true;
        }
        return false;
    }

    public void setTarget(float val){
        this.target = val;
    }

    public void tick(float interval){
        this.timer += interval;
        if(this.timer > this.target + (50 * interval)){
            this.timer = this.target + interval;
        }
    }

    public float getProgress(){
        return this.timer / this.target;
    }

    public void restart(){
        this.timer = 0;
    }
}
