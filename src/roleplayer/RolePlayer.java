package roleplayer;

import armor.Armor;
import java.util.ArrayList;

/**
 * This will present a role player in our combat simulator.
 */
public interface RolePlayer {

  /**
   * The adventurer chooses armor.
   *
   * @param newArmor The armor chosen.
   */
  void addArmor(Armor newArmor);

  /**
   * This will gather up all the helmets the player has.
   *
   * @return A list of head armor.
   */
  ArrayList<Armor> getAllHeadArmor();

  /**
   * This will gather up all the gloves the player has.
   *
   * @return A list of hand armor.
   */
  ArrayList<Armor> getAllHandArmor();

  /**
   * This will gather up all the boots the player has.
   *
   * @return A list of foot armor.
   */
  ArrayList<Armor> getAllFootArmor();

  /**
   * Get the adventurer's name.
   *
   * @return The name of the player.
   */
  String getAdventurerName();

  /**
   * Get the adventurers attack power.
   *
   * @return the attack power.
   */
  int getAttackPower();

  /**
   * Get the adventurers defense.
   *
   * @return the defense.
   */
  int getDefensiveStats();

  /**
   * Get the adventurer's final attack power.
   *
   * @return the final attack power
   */
  int getTotalAttackPower();

  /**
   * Get the adventurer's final defense.
   *
   * @return the final defense
   */
  int getTotalDefensiveStat();

  /**
   * Update the players final stats.
   */
  void increaseStatus();
}
