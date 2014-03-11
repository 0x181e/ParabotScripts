package org.unix.tasks;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Mouse;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.api.methods.*;
import org.rev317.api.wrappers.interactive.Npc;
import org.rev317.api.wrappers.hud.Item;
import org.rev317.api.wrappers.scene.Tile;
import org.unix.vars.Generic;
import org.unix.vars.Stalls;

/**
 * Created by unix on 11/03/14.
 */
public class Sell implements Strategy {

    int shopInterface = 3824;
    int shopkeeper = 522;


    @Override
    public boolean activate() {

        return Inventory.isFull() || Interfaces.get(shopInterface).isVisible();
    }

    @Override
    public void execute() {

        if (Interfaces.get(3824).isVisible()) {
            if (Inventory.getCount() > 1) {
                for (Item i : Inventory.getItems()) {
                    if (i.getId() != 995) {
                        i.interact("Sell 10");
                    }
                }
            } else {
                Mouse.getInstance().moveMouse(490, 20);
                Time.sleep(250, 500);
                Mouse.getInstance().click(490, 20, true);
            }
        } else {

            if (Npcs.getNearest(522)[0] != null) {
                Npc shop = Npcs.getNearest(522)[0];

                if (shop.isOnScreen()) {
                    shop.interact("Trade");
                    Time.sleep(500, 1000);
                } else {
                    if (shop.distanceTo() < 3) {
                        Camera.turnTo(shop);
                        Time.sleep(250, 500);
                    } else if (shop.distanceTo() < 10) {
                        new Tile(2948, 3328, 0).clickMM();
                        Time.sleep(500, 1000);
                    } else {
                        System.out.println("[ûThief] Uh Oh... Were lost!");
                        System.exit(0);
                    }
                }

            }
        }

    }
}
