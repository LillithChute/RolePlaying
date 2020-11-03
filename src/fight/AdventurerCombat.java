package fight;

import armor.Armor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import roleplayer.RolePlayer;

/**
 * This will implement combat.
 */
public class AdventurerCombat implements Combat {

  private final ArrayList<RolePlayer> adventurers;
  private final Map<String, ArmorList> armorLists;
  private final int numberOfCombatants;

  /**
   * A constructor that sets up the combat.
   */
  public AdventurerCombat() {
    this.adventurers = new ArrayList<>();
    this.armorLists = new HashMap<>();
    this.numberOfCombatants = 2;
  }

  @Override
  public void addAdventurer(RolePlayer adventurer) {
    if (adventurer == null) {
      throw new IllegalArgumentException("Null adventurers.");
    }
    if (this.adventurers.size() == numberOfCombatants) {
      throw new IllegalStateException("Too many adventurers.");
    }
    for (RolePlayer rolePlayer : this.adventurers) {
      // We don't want the same adventurer!
      if (adventurer.getAdventurerName().equals(rolePlayer.getAdventurerName())) {
        throw new IllegalArgumentException("Duplicate adventurer names.");
      }
    }

    this.adventurers.add(adventurer);
  }

  @Override
  public void addArmorList(String adventurerName, ArmorList armorList) {
    if (adventurerName == null) {
      throw new IllegalArgumentException("Every adventurer needs a name.");
    }
    if (armorList == null) {
      throw new IllegalArgumentException("Must have an armor list.");
    }
    if (this.armorLists.size() == numberOfCombatants) {
      throw new IllegalStateException("Too many armor lists.");
    }
    for (RolePlayer rolePlayer : this.adventurers) {
      if (adventurerName.equals(rolePlayer.getAdventurerName())) {
        this.armorLists.put(adventurerName, armorList);

        return;
      }
    }

    throw new IllegalArgumentException("Incorrect adventurer name.");
  }

  @Override
  public String getArmorList() {
    // We need armor.
    if (this.armorLists.size() == 0) {
      throw new IllegalStateException("No armor lists in the battle.");
    }

    String result = "";

    // Use Map to put together the list of armor
    for (Map.Entry<String, ArmorList> entry : this.armorLists.entrySet()) {
      result += String.format("%s:\n", entry.getKey());

      for (Armor item : entry.getValue().getArmorList()) {
        result += String.format("%s, attack %d, defense %d\n",
                item.getTheNameOfArmor(), item.getAttackPower(), item.getArmorDefense());
      }
    }

    return result;
  }

  @Override
  public void putArmorOn() {
    // Make sure all the numbers line up
    if (this.armorLists.size() != numberOfCombatants
            || this.adventurers.size() != numberOfCombatants) {
      throw new IllegalStateException("Armor amounts or adventurers are not enough.");
    }

    //  This loop will put the armor on.
    for (RolePlayer adventurer : this.adventurers) {
      System.out.printf("%s is putting on \n", adventurer.getAdventurerName());

      // Sort this adventurer's armor.
      this.armorLists.get(adventurer.getAdventurerName()).sort();

      for (Armor item : this.armorLists.get(adventurer.getAdventurerName()).getArmorList()) {
        try {
          adventurer.addArmor(item);
        } catch (Exception e) {
          System.out.print(e.getMessage());
        }
      }

      adventurer.increaseStatus();
      System.out.println("");
    }
    System.out.println("");
  }

  @Override
  public String getAdventurerState() {
    if (this.adventurers.size() == 0) {
      throw new IllegalStateException("There are no adventurers for combat.");
    }

    String result = "";

    for (RolePlayer rolePlayer : this.adventurers) {
      result += String.format("- %s: \n"
                      + "\tattack power = %d, defense = %d\n"
                      + "\tfinal attack power = %d, final defense = %d\n",
              rolePlayer.getAdventurerName(), rolePlayer.getAttackPower(),
              rolePlayer.getDefensiveStats(),
              rolePlayer.getTotalAttackPower(), rolePlayer.getTotalDefensiveStat());

      result += "- head armor\n";
      result += formatArmor(rolePlayer.getAllHeadArmor());

      result += "- hand armor\n";
      result += formatArmor(rolePlayer.getAllHandArmor());

      result += "- foot armor\n";
      result += formatArmor(rolePlayer.getAllFootArmor());
      result += "\n";
    }
    return result;
  }

  private String formatArmor(ArrayList<Armor> armorList) {
    String result = "";

    if (armorList.size() == 0) {
      result += String.format("\t%s\n", "There is no armor.");
    } else {
      for (Armor item : armorList) {
        result += String.format("\t%s, attack = %d, defense = %d\n",
                item.getTheNameOfArmor(), item.getAttackPower(), item.getArmorDefense());
      }
    }

    return result;
  }

  @Override
  public String getCombatResult() {
    if (armorLists.size() != numberOfCombatants || adventurers.size() != numberOfCombatants) {
      throw new IllegalStateException("Adventurers and armor have not been loaded yet.");
    }
    
    // We know if the total and base power and defense are the same, no equipment has been taken.
    if ((adventurers.get(0).getAttackPower() == adventurers.get(0).getTotalAttackPower()
            && adventurers.get(0).getDefensiveStats()
            == adventurers.get(0).getTotalDefensiveStat())
            || (adventurers.get(1).getAttackPower() == adventurers.get(1).getTotalAttackPower()
            && adventurers.get(1).getDefensiveStats()
            == adventurers.get(1).getTotalDefensiveStat())) {
      throw new IllegalStateException("The adventurers haven't armored up.");
    }
    
    
    String combatResult = "unknown";
    
    int damageAdventurerOne = this.adventurers.get(1).getTotalAttackPower()
            - this.adventurers.get(0).getTotalDefensiveStat();
    int damageAdventurerTwo = this.adventurers.get(0).getTotalAttackPower()
            - this.adventurers.get(1).getTotalDefensiveStat();
    
    
    if (damageAdventurerOne < damageAdventurerTwo) {
      combatResult = String.format("%s gets %d damage, %s gets %d damage. %s wins.",
              this.adventurers.get(0).getAdventurerName(), damageAdventurerOne,
              this.adventurers.get(1).getAdventurerName(), damageAdventurerTwo,
              this.adventurers.get(0).getAdventurerName());
    } else if (damageAdventurerOne > damageAdventurerTwo) {
      combatResult = String.format("%s gets %d damage, %s gets %d damage. %s wins.",
              this.adventurers.get(0).getAdventurerName(), damageAdventurerOne,
              this.adventurers.get(1).getAdventurerName(), damageAdventurerTwo,
              this.adventurers.get(1).getAdventurerName());
    } else {
      combatResult = String.format("%s gets %d damage, %s gets %d damage. Tie.",
              this.adventurers.get(0).getAdventurerName(), damageAdventurerOne,
              this.adventurers.get(1).getAdventurerName(), damageAdventurerTwo);
    }
    return combatResult;
  }

  @Override
  public String toString() {
    return "Combat.";
  }
}
