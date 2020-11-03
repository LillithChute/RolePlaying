package armor;

public class HeadArmor extends AbstractArmor {

  private final int defense;

  /**
   * This represents the helmet.
   *
   * @param name THe name of the helmet.
   * @param hasBeenCombined If the armor has already been combined.
   * @param defense Defensive stats.
   */
  public HeadArmor(String name, boolean hasBeenCombined, int defense) {
    super(TypeOfArmor.HeadArmor, name, hasBeenCombined);

    if (defense < 0) {
      throw new IllegalArgumentException("Defense must be greater than zero.");
    }

    this.defense = defense;
  }


  @Override
  public int getAttackPower() {
    return 0;
  }

  @Override
  public int getArmorDefense() {
    return this.defense;
  }

  @Override
  public Armor combineArmorPieces(Armor otherArmor) {
    // CHeck to see if armor can be combined and if so, do it.
    if (this.canBeCombined(otherArmor)) {
      String descriptor = getTheNameOfArmor().split(" ")[0];
      String nounOtherArmor = otherArmor.getTheNameOfArmor();

      // rename the armor
      String newName = String.format("%s, %s", descriptor, nounOtherArmor);
      int newDefense = this.getArmorDefense() + otherArmor.getArmorDefense();

      return new HeadArmor(newName, true, newDefense);
    } else {
      throw new IllegalArgumentException("These pieces of armor can't be combined.");
    }
  }
}
