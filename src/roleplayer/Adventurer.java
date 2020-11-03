package roleplayer;

import armor.Armor;
import armor.FootArmor;
import armor.HandArmor;
import armor.HeadArmor;
import java.util.ArrayList;

/**
 * This will create an adventurer.
 */
public class Adventurer implements RolePlayer {

  private final String adventurerName;
  private final int attackPower;
  private final int defenseStat;
  private int increaseAttack;
  private int increaseDefense;
  private final ArrayList<Armor> headArmor;
  private final ArrayList<Armor> handArmor;
  private final ArrayList<Armor> footArmor;

  /**
   * A constructor to help build a player instance.
   *
   * @param nameOfAdventurer The name of the role player.
   * @param setAttackPower  the attack power of the role player
   * @param setDefense the defensive status of the role player
   * @throws IllegalArgumentException if the name is null or has length 0,
   *                                  or if the attack or defense is negative.
   */
  public Adventurer(String nameOfAdventurer, int setAttackPower, int setDefense) {
    // We need to make sure we have minimum requirements.
    if (nameOfAdventurer == null || nameOfAdventurer.length() == 0
            || nameOfAdventurer.split(" ").length == 0) {
      throw new IllegalArgumentException("The adventurer must have a name.");
    }

    if (setAttackPower < 0 || setDefense < 0) {
      throw new IllegalArgumentException("Attack and defence must be positive.");
    }

    this.adventurerName = nameOfAdventurer.toLowerCase();
    this.attackPower = setAttackPower;
    this.defenseStat = setDefense;
    this.increaseAttack = 0;
    this.increaseDefense = 0;
    this.headArmor = new ArrayList<>();
    this.handArmor = new ArrayList<>();
    this.footArmor = new ArrayList<>();
  }

  @Override
  public void addArmor(Armor newArmor) {
    if (newArmor == null) {
      throw new IllegalArgumentException("The adventurer must have gear.");
    }
    
    if (newArmor instanceof HeadArmor) {
      int sizeLimit = 1;

      armorAdd(this.headArmor, sizeLimit, newArmor);
    } else if (newArmor instanceof HandArmor) {
      int sizeLimit = 2;

      armorAdd(this.handArmor, sizeLimit, newArmor);
    } else if (newArmor instanceof FootArmor) {
      int sizeLimit = 2;

      armorAdd(this.footArmor, sizeLimit, newArmor);
    }
  }

  @Override
  public ArrayList<Armor> getAllHeadArmor() {
    return this.headArmor;
  }

  @Override
  public ArrayList<Armor> getAllHandArmor() {
    return this.handArmor;
  }

  @Override
  public ArrayList<Armor> getAllFootArmor() {
    return this.footArmor;
  }

  @Override
  public String getAdventurerName() {
    return this.adventurerName;
  }

  @Override
  public int getAttackPower() {
    return this.attackPower;
  }

  @Override
  public int getDefensiveStats() {
    return this.defenseStat;
  }

  @Override
  public int getTotalAttackPower() {
    return this.increaseAttack + this.attackPower;
  }

  @Override
  public int getTotalDefensiveStat() {
    return this.increaseDefense + this.increaseDefense;
  }


  @Override
  public void increaseStatus() {
    updateStats(this.headArmor);
    updateStats(this.handArmor);
    updateStats(this.footArmor);
  }

  private void updateStats(ArrayList<Armor> armorList) {
    for (Armor armor : armorList) {
      this.increaseAttack += armor.getAttackPower();
      this.increaseDefense += armor.getArmorDefense();
    }
  }


  private void armorAdd(ArrayList<Armor> armorList, int sizeLimit, Armor newArmor) {
    // Make sure we check to see if we have a full set of armor.
    if (armorList.size() == sizeLimit) {
      boolean armorReplace = false;

      // See if any piece of armor can be combined.
      for (int i = 0; i < sizeLimit; i++) {
        try {
          armorList.set(i, armorList.get(i).combineArmorPieces(newArmor));
          return;
        } catch (Exception ignore) {
          // Check to see of the combined pieces we have the best piece.
          if (armorList.get(i).equals(newArmor)) {
            return;
          }
          if (armorList.get(i).replaceWithBetterArmor(newArmor).equals(newArmor)) {
            armorReplace = true;
          }
          armorList.set(i, armorList.get(i).replaceWithBetterArmor(newArmor));
        }
      }

      if (!armorReplace) {
        throw new IllegalArgumentException(String.format("This piece of armor (%s) can't be "
                        + "added. The adventurer is fully equipped.\n",
                newArmor.getTheNameOfArmor()));
      }

      return;
    } else if (armorList.size() < sizeLimit && armorList.size() > 0) {

      // Get the set of armor and combine.
      for (int i = 0; i < armorList.size(); i++) {
        try {
          armorList.set(i, armorList.get(i).combineArmorPieces(newArmor));

          return;
        } catch (Exception ignore) {
          if (armorList.get(i).equals(newArmor)) {

            return;
          }
        }
      }
      armorList.add(newArmor);
      return;
    }

    armorList.add(newArmor);
  }

  @Override
  public String toString() {
    return String.format("%s, attack = %d, defense = %d.",
            this.adventurerName, this.attackPower, this.defenseStat);
  }
}
