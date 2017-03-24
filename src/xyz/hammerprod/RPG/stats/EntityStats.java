/**
 * Created by andreashammer on 20/01/2017.
 */
package xyz.hammerprod.RPG.stats;

public class EntityStats {
    private float hp;
    private float mp;
    private float attack;
    private float defence;
    private float luck;
    private int level;

    public EntityStats(){
        this.hp = 0;
        this.mp = 0;
        this.attack = 0;
        this.defence = 0;
        this.luck = 0;
    }

    public EntityStats(float hp, float mp, float att, float def, float luck){
        this.hp = hp;
        this.mp = mp;
        this.attack = att;
        this.defence = def;
        this.luck = luck;
    }

    public void useMP(int val){
        this.mp -= val;
    }

    public void looseHP(int val){
        this.hp -= val;
    }

    public float getHp() {
        return hp;
    }

    public void setHp(float hp) {
        this.hp = hp;
    }

    public float getMp() {
        return mp;
    }

    public void setMp(float mp) {
        this.mp = mp;
    }

    public float getAttack() {
        return attack;
    }

    public void setAttack(float attack) {
        this.attack = attack;
    }

    public float getDefence() {
        return defence;
    }

    public void setDefence(float defence) {
        this.defence = defence;
    }

    public float getLuck() {
        return luck;
    }

    public void setLuck(float luck) {
        this.luck = luck;
    }
}
