package org.unix.vars;

import org.rev317.api.methods.Skill;

/**
 * Created by unix on 11/03/14.
 */
public enum Stalls {

    SCIMITAR_STALL(4878, 90, 0, null),
    MAGIC_STALL(4877, 75, 1379, SCIMITAR_STALL),
    GENERAL_STALL(4876, 55, 2347, MAGIC_STALL),
    FOOD_STALL(4875, 30, 2309, GENERAL_STALL),
    CRAFTING_STALL(4874, 1, 1609, FOOD_STALL);

    public int id, req, loot;
    public Stalls upgrade;

    Stalls(int ID, int Requirement, int Loot, Stalls Upgrade) {
        this.id = ID;
        this.req = Requirement;
        this.upgrade = Upgrade;
        this.loot = Loot;
    }

    public Stalls getUpgrade() {
        return upgrade;
    }

    public int getId() {
        return id;
    }

    public int getReq() {
        return req;
    }

    public int getLoot() {
        return loot;
    }

}
