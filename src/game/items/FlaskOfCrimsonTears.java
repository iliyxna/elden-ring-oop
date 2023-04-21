package game.items;

public class FlaskOfCrimsonTears {
    private int usesLeft;
    private final int MAX_USES = 2;
    private final int HEAL_AMOUNT = 250;

    public FlaskOfCrimsonTears() {
        usesLeft = MAX_USES;
    }

    public boolean use() {
        if (usesLeft > 0) {
            usesLeft--;
            // restore health by HEAL_AMOUNT
            return true;
        }
        return false; // no uses left
    }

    public int getUsesLeft() {
        return usesLeft;
    }
}