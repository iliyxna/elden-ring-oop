package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actors.Enemy;
import game.actors.GodrickTheGrafted;
import game.actors.PileOfBones;
import game.actors.Player;
import game.environments.GoldenFogDoor;
import game.environments.SiteOfLostGrace;
import game.utils.FancyMessage;
import game.utils.Status;

/**
 * An action executed if an actor is killed.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Iliyana, Damia
 *
 */
public class DeathAction extends Action {
    /**
     * Attacker
     */
    private Actor attacker;

    /**
     * Constructor for DeathAction class.
     * @param actor attacker
     */
    public DeathAction(Actor actor) {
        this.attacker = actor;
    }

    /**
     * When the target is killed, the items & weapons carried by target
     * will be dropped to the location in the game map where the target was
     *
     * @param target The actor performing the action.
     * @param map The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor target, GameMap map) {
        String result = "";

        ActionList dropActions = new ActionList();

        // Demigod will only drop Remebrance of the Grafted
        if (target.hasCapability(Status.DEMIGOD)){
            for (Item item : target.getItemInventory()){
                dropActions.add(item.getDropAction(target));
            }
            result += FancyMessage.DEMIGOD_FELLED;
            SiteOfLostGrace godrickTheGrafted = new SiteOfLostGrace();
            map.at(((GodrickTheGrafted)target).getLastLocation().x(),((GodrickTheGrafted)target).getLastLocation().y()).setGround(godrickTheGrafted);

            // EXTRA: so that player can leave the room only after Godrick dies.
            GoldenFogDoor newBossDoor = new GoldenFogDoor();
            map.at(1,1).setGround(newBossDoor);
            newBossDoor.addDestination("to Boss Room", ((Player)attacker).getMapOfLastLostGrace().at(36, 11));
        }
        // other enemies will drop items, weapons
        else {
            for (Item item : target.getItemInventory())
                dropActions.add(item.getDropAction(target));
            for (WeaponItem weapon : target.getWeaponInventory())
                dropActions.add(weapon.getDropAction(target));
        }

        // execute drop items
        for (Action drop : dropActions)
            drop.execute(target, map);

        // Display number of runes dropped and update player runes
        if (attacker.hasCapability(Status.PLAYER)) {
            Player player = (Player) attacker;
            if (target.hasCapability(Status.ENEMY)) {
                Enemy targetEnemy = (Enemy) target;
                player.getRuneManager().addRunes(targetEnemy.getEnemyRuneValue().getRuneValue());
                new Display().println("Runes gained from killing "+ targetEnemy + ": $" + targetEnemy.getEnemyRuneValue());
            } else if (target.hasCapability(Status.PILE_OF_BONES)) {
                // pile of bones could previously be HSS or Skeletal bandit
                PileOfBones pileOfBones = (PileOfBones) target;
                // HSS or skeletal bandit?
                Enemy targetEnemy = pileOfBones.getPreviousEnemy();
                player.getRuneManager().addRunes(targetEnemy.getEnemyRuneValue().getRuneValue());
                new Display().println("Runes gained from killing "+ targetEnemy + ": $" + targetEnemy.getEnemyRuneValue());
            }
        }
        // remove actor
        map.removeActor(target);
        result += System.lineSeparator() + menuDescription(target);
        return result;
    }

    /**
     * Returns a descriptive string.
     * @param actor The actor performing the action.
     * @return a descriptive string
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " is killed. ";
    }
}
