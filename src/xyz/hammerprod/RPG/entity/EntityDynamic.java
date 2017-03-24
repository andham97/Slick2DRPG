/**
 * Created by andreashammer on 20/01/2017.
 */
package xyz.hammerprod.RPG.entity;

import xyz.hammerprod.RPG.stats.EntityStats;

public abstract class EntityDynamic extends Entity {
    protected EntityStats stats;

    public EntityStats getStats(){
        return stats;
    }
}
