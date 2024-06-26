package game.utils;

/**
 * Use this enum class to give `buff` or `debuff`.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by : Mustafa, Iliyana, Damia
 */
public enum Status {
    HOSTILE_TO_ENEMY,
    CAN_DESPAWN_WHEN_RESET, // For future implementation, new enemy added may not be able to be de-spawned
    HEAVY_SKELETAL_SWORDSMAN,
    GIANT_CRAB,
    LONE_WOLF,
    GIANT_DOG,
    GIANT_CRAYFISH,
    PILE_OF_BONES,
    CAN_ENTER_FLOOR,
    SITE_OF_LOSTGRACE,
    ENEMY,
    PLAYER,
    CONSUMABLE,  // for items
    SELLABLE,
    PURCHASEABLE,
    SKELETAL_TYPE, // HSS, Skeletal bandit
    DOG_TYPE,   // Lone wolf, Giant dog
    WATER_TYPE, // Giant crab, Giant crayfish
    ENEMY_WEAPON_SKILL,
    UNIQUE_SKILL,
    RANGED_ATTACK,
    DEMIGOD, // REQ3
    DOG,
    SC_CREATURES_TYPE, // Dog
    GODRICK_SOLDIER,
    INVADER,
    POISONED,
    POISONOUS_ITEM
}
