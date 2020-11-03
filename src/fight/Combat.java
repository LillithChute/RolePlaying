package fight;

import roleplayer.RolePlayer;

/**
 * This defines the operations needed to address combat.
 */
public interface Combat {

  /**
   * This adds the adventurer to the combat.
   *
   * @param adventurer The new adventurer.
   */
  void addAdventurer(RolePlayer adventurer);

  /**
   * This is the list of armor for the specified adventurer.
   *
   * @param adventurerName The name of the adventurer.
   * @param armorList The list of armor.
   */
  void addArmorList(String adventurerName, ArmorList armorList);

  /**
   * Gets all the armor lists.
   *
   * @return The armor list.
   */
  String getArmorList();

  /**
   * This is where the adventurer picks the armor to put on.
   */
  void putArmorOn();

  /**
   * Gets the state of the adventurers.
   *
   * @return The current state of the adventurers.
   */
  String getAdventurerState();

  /**
   * Gets the result of the combat.
   *
   * @return The combat result.
   */
  String getCombatResult();
}
