package org.unix;

/**
 * Created by unix on 11/03/14.
 */
public class Calc {

    public static String workOut(int input) {
        if (input >= 100000 && input <= 999999) {
            return input / 1000 + "K";
        } else if (input >= 1000000 && input <= 999999999) {
            return input / 1000000 + "M";
        } else if (input >= 1000000000) {
            return input / 1000000000 + "B";
        }
        return "" + input;
    }

}
