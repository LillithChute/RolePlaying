package armor;

/**
 * This is the armor for the hands, also known as gloves.
 */
public class HandArmor extends AbstractArmor {

  private final int attackPower;

  /**
   * Construct a {@code HandGear} instance.
   *
   * @param name The name of the armor.
   * @param canBeCombined Whether it is combined.
   * @param attackPower The attack power.
   * @throws IllegalArgumentException if attack power is less than zero.
   */
  public HandArmor(String name, boolean canBeCombined, int attackPower) {

    super(TypeOfArmor.HandArmor, name, canBeCombined);

    if (attackPower < 0) {
      throw new IllegalArgumentException("Attack power can't be less than zero.");
    }

    this.attackPower = attackPower;
  }

  @Override
  public int getAttackPower() {
    return this.attackPower;
  }

  @Override
  public int getArmorDefense() {
    return 0;
  }

  @Override
  public Armor combineArmorPieces(Armor otherArmor) {
    // Combine gear if we can.
    if (this.canBeCombined(otherArmor)) {
      String descriptor = getTheNameOfArmor().split(" ")[0];
      String nounOtherArmor = otherArmor.getTheNameOfArmor();
      String newName = String.format("%s, %s", descriptor, nounOtherArmor);
      int newAttack = this.getAttackPower() + otherArmor.getAttackPower();

      return new HandArmor(newName, true, newAttack);
    }

    throw new IllegalArgumentException("This armor can't be combined.");
  }
}
