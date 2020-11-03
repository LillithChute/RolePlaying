package armor;

public class FootArmor extends AbstractArmor {

  private final int attackPower;
  private final int defense;

  /**
   * This will build out armor for the feet.
   *
   * @param name The armor name.
   * @param canBeCombined Can this armor be combined.
   * @param attackPower attack power.
   * @param defense defensive status.
   * @throws IllegalArgumentException if attack or defense is less than zero.
   */
  public FootArmor(String name, boolean canBeCombined, int attackPower, int defense) {

    super(TypeOfArmor.FootArmor, name, canBeCombined);

    if (attackPower < 0 || defense < 0) {
      throw new IllegalArgumentException("Attack and defense must be greater than zero.");
    }

    this.attackPower = attackPower;
    this.defense = defense;
  }

  @Override
  public int getAttackPower() {
    return this.attackPower;
  }

  @Override
  public int getArmorDefense() {
    return this.defense;
  }

  @Override
  public Armor combineArmorPieces(Armor otherArmor) {
    // Check to see if combining can happen.
    if (this.canBeCombined(otherArmor)) {
      String descriptor = getTheNameOfArmor().split(" ")[0];
      String nounOtherArmor = otherArmor.getTheNameOfArmor();
      String newName = String.format("%s, %s", descriptor, nounOtherArmor);

      int newAttack = this.attackPower + otherArmor.getAttackPower();
      int newDefense = this.defense + otherArmor.getArmorDefense();

      return new FootArmor(newName, true, newAttack, newDefense);
    } else {
      throw new IllegalArgumentException("These items can't be combined.");
    }
  }
}
