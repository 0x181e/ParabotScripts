package org.unix;

import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.input.Mouse;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.api.methods.Inventory;
import org.rev317.api.methods.Skill;
import org.unix.tasks.Sell;
import org.unix.tasks.Steal;
import org.unix.vars.Generic;
import org.unix.vars.Stalls;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by unix on 11/03/14.
 */

@ScriptManifest(
        author = "ûnix",
        name = "ûThief",
        category = Category.THIEVING,
        version = 0.1,
        description = "",
        servers = "CrisisX"
)
public class uThief extends Script implements Paintable {

    long startTime;
    int startingCash;
    int startExp;
    int startLevel;

    @Override
    public boolean onExecute() {

        if (Inventory.getItems(995) != null) {
            startingCash = Inventory.getItems(995)[0].getStackSize();
        } else {
            startingCash = 0;
        }
        System.out.println("Starting Cash: " + startingCash);

        Generic.currentStall = Stalls.CRAFTING_STALL;
        ArrayList<Strategy> sl = new ArrayList<Strategy>();
        sl.add(new Sell());
        sl.add(new Steal());
        startTime = System.currentTimeMillis();
        startExp = Skill.THIEVING.getExperience();
        startLevel = Skill.THIEVING.getLevel();
        provide(sl);
        return true;
    }

    @Override
    public void onFinish() {}

    //START: Code generated using Enfilade's Easel
    private Image getImage(String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch(IOException e) {
            return null;
        }
    }

    private final Color color1 = new Color(255, 255, 255);

    private final Font font1 = new Font("Bauhaus 93", 0, 24);

    private final Image img1 = getImage("http://i328.photobucket.com/albums/l359/0x181e/Untitled-2_zpsef0e3529.png");

    @Override
    public void paint(Graphics g1) {

        long diff = System.currentTimeMillis() - startTime;
        long s = diff / 1000 % 60;
        long m = diff / (60 * 1000) % 60;
        long h = diff / (60 * 60 * 1000);

        Graphics2D g = (Graphics2D)g1;
        g.drawImage(img1, 0, 0, null);
        g.setFont(font1);
        g.setColor(color1);
        g.drawString(String.format("%s:%s:%s", h, m, s), 175, 367);
        g.drawString(Calc.workOut(Inventory.getItems(995)[0].getStackSize() - startingCash), 175, 392);
        g.drawString(Calc.workOut(Skill.THIEVING.getExperience() - startExp), 175, 417);
        g.drawString(Integer.toString(Skill.THIEVING.getLevel() - startLevel), 175, 443);
        g.drawString(Generic.currentStall.toString(), 175, 467);

        g.setColor(Color.YELLOW);
        g.drawLine(Mouse.getInstance().getPoint().x - 5, Mouse.getInstance().getPoint().y - 5, Mouse.getInstance().getPoint().x + 5, Mouse.getInstance().getPoint().y + 5);
        g.drawLine(Mouse.getInstance().getPoint().x - 5, Mouse.getInstance().getPoint().y + 5, Mouse.getInstance().getPoint().x + 5, Mouse.getInstance().getPoint().y - 5);
    }
    //END: Code generated using Enfilade's Easel

}
