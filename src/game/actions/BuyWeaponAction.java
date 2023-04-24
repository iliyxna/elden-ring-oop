package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Purchasable;
import game.actors.Player;
import game.actors.Trader;
import game.rune.Rune;

import java.util.Map;

/**
 * An action class that allows Player to buy weapon from Trader.
 * Note that it's from PLAYER to TRADER
 * @author Iliyana Samsudin
 * @see Action
 * @see Trader
 * @version 1.0
 */
public class BuyWeaponAction extends Action {
    private Purchasable weapon;

    public BuyWeaponAction(WeaponItem weapon){
        this.weapon = (Purchasable) weapon;
    }

    @Override
    public String execute(Actor seller, GameMap map) {
        String retString;
        Player player = (Player) seller;
        int playerRune = player.getTotalRunes().getRuneValue();
        int weaponPrice = weapon.getBuyPrice().getRuneValue();

        // Transaction
        if (playerRune >= weaponPrice){
            player.addWeaponToInventory((WeaponItem) weapon);
            player.subtractRunes(weaponPrice);
            retString = weapon.toString() + " has been successfully added to inventory.";
        } else {
            retString = "Purchase failed. Player does not have enough runes.";
        }
        return retString;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " bought " + weapon.toString() + " for $" + weapon.getBuyPrice();
    }

}

