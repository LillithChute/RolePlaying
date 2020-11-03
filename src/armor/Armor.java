package armor;

/**
 * This provides the operations of armor that a player will wear.  There are three kinds of
 * armor.  There are head, hands, and foot armor.  Additionally, two armor items that are not
 * combined could be combined to create one piece of armor.
 */
public interface Armor extends Comparable<Armor> {

  /**
   * This returns the armor type.
   *
   * @return The kind of armor.
   */
  TypeOfArmor getArmorType();

  /**
   * In this world, armor has a name, so we need to get that for combining purposes.
   *
   * @return Name of the armor.
   */
  String getTheNameOfArmor();

  /**
   * Armor has an attack power rating.
   *
   * @return The attack power of the armor piece.
   */
  int getAttackPower();

  /**
   * Armor has a set defensive rating.
   *
   * @return The defense rating of the armor.
   */
  int getArmorDefense();

  /**
   * This will take two pieces of armor and combine them into one awesome piece.
   *
   * @param otherArmor The second piece of armor.
   * @return The new piece of armor from the combination.
   */
  Armor combineArmorPieces(Armor otherArmor);

  /**
   * A check on whether or not this piece of armor is a combined piece already.
   *
   * @return Whether this armomr is a combined piece.
   */
  boolean flagCombined();

  /**
   * The player can switch out a better piece of combined gear.  If the piece being compared is not
   * better, they will keep their armor.
   *
   * @param otherArmor The new armor being compared.
   * @return The better piece of armor.
   */
  Armor replaceWithBetterArmor(Armor otherArmor);
}
