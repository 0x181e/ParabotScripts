package org.unix.tasks;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.api.methods.*;
import org.rev317.api.wrappers.scene.SceneObject;
import org.rev317.api.wrappers.scene.Tile;
import org.unix.vars.Generic;
import org.unix.vars.Stalls;

/**
 * Created by unix on 11/03/14.
 */
public class Steal implements Strategy {
    @Override
    public boolean activate() {
        if (Generic.currentStall != Stalls.SCIMITAR_STALL) {
            if (Skill.THIEVING.getLevel() >= Generic.currentStall.getUpgrade().getReq()) {
                Generic.currentStall = Generic.currentStall.getUpgrade();
                System.out.println("Stall Upgraded to: " + Generic.currentStall.toString());
                return false;
            }
        }
        return !Inventory.isFull() && !Interfaces.get(3824).isVisible();
    }

    @Override
    public void execute() {
        if (SceneObjects.getNearest(Generic.currentStall.getId()) != null) {
            SceneObject targ = SceneObjects.getNearest(Generic.currentStall.getId())[0];

            if (Walking.isMoving())
                return;

            if (targ.isOnScreen()) {
                targ.interact("Steal from");
                Time.sleep(500, 1000);
            } else if (targ.distanceTo() < 3) {
                Camera.turnTo(targ);
                Time.sleep(250, 500);
            } else if (targ.distanceTo() < 10) {
                new Tile(3000, 3381, 0).clickMM();
                Time.sleep(500, 1500);
            } else {
                System.out.println("[ûThief] Uh Oh... We are lost!");
                System.exit(0);
            }
        }
    }
}
