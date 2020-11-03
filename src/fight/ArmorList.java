package fight;


import armor.Armor;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This is the list of the 10 pieces of armor.
 */
public class ArmorList {
  private final ArrayList<Armor> armorList;

  /**
   * Constructs a new list of armor pieces.
   *
   * @param listOfArmor The armor list.
   * @throws IllegalArgumentException if the armor list is null or doesn't have 10 gear items.
   */
  public ArmorList(ArrayList<Armor> listOfArmor) {
    if (listOfArmor == null) {
      throw new IllegalArgumentException("You must have armor.");
    }
    if (listOfArmor.size() != 10) {
      throw new IllegalArgumentException("There must be 10 pieces of armor.");
    }

    this.armorList = listOfArmor;
  }

  /**
   * Get the gear list.
   *
   * @return the gear list
   */
  public ArrayList<Armor> getArmorList() {
    return this.armorList;
  }

  /**
   * Sort the gear list based on the status of the gear items.
   */
  public void sort() {
    Collections.sort(this.armorList);
  }

  @Override
  public String toString() {
    String result = "";

    for (Armor item : this.armorList) {
      result += String.format("%s, Attack = %d, Defense = %d, Combined Armor = %s\n",
              item.getTheNameOfArmor(), item.getAttackPower(), item.getArmorDefense(),
              item.flagCombined());
    }
    return result;
  }
}
