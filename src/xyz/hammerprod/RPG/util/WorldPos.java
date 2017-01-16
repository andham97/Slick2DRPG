/**
 * Created by andreashammer on 15/01/2017.
 */
package xyz.hammerprod.RPG.util;

public class WorldPos {
    private float x, y, lx, ly;

    public WorldPos(){
        this.x = 0;
        this.y = 0;
    }

    public WorldPos(float x, float y){
        this.x = x;
        this.y = y;
    }

    public WorldPos(WorldPos pos){
        this.x = pos.getX();
        this.y = pos.getY();
    }

    public float getX(){
        return this.x;
    }

    public float getY(){
        return this.y;
    }

    public void setX(float value){
        this.x = value;
    }

    public void setY(float value){
        this.y = value;
    }

    public void translate(float dx, float dy){
        this.lx = this.x;
        this.ly = this.y;
        this.x += dx;
        this.y += dy;
    }

    public float getLX(){
        return this.lx;
    }

    public float getLY(){
        return this.ly;
    }

    public static float calculateAnimationCoord(float p, float d){
        if(p / d == Float.POSITIVE_INFINITY || p / d == Float.NEGATIVE_INFINITY){
            return 0;
        }
        return p / d;
    }

    public boolean compareTo(WorldPos pos){
        return this.getX() == pos.getX() && this.getY() == pos.getY();
    }
}
