package armor;

/**
 * This class will help create the implementation of the Armor Interface.  It will implement
 * the common operations of armor as defined by the specifications.
 */
abstract class AbstractArmor implements Armor {

  private final TypeOfArmor armorType;
  private final String armorName;
  private final boolean flagCombinedGear;

  /**
   * This creates an instance of Armor.
   *
   * @param armorType The kind of armor this is.
   * @param armorName The name of this piece of armor.
   * @param flagCombinedGear Is this piece already combined.
   * @throws IllegalArgumentException The name or the type of armor can't be null.
   */
  public AbstractArmor(TypeOfArmor armorType, String armorName, boolean flagCombinedGear) {
    if (armorType == null || armorName == null) {
      throw new IllegalArgumentException("Constructor arguments can't be null.");
    }

    if (armorName.split(" ").length < 2) {
      throw new IllegalArgumentException("The name of the armor needs a noun and adjective.");
    }

    this.armorType = armorType;
    this.armorName = armorName;
    this.flagCombinedGear = flagCombinedGear;
  }

  @Override
  public TypeOfArmor getArmorType() {
    return this.armorType;
  }

  @Override
  public String getTheNameOfArmor() {
    return this.armorName;
  }

  @Override
  public abstract int getAttackPower();

  @Override
  public abstract int getArmorDefense();

  @Override
  public boolean flagCombined() {
    return this.flagCombinedGear;
  }

  @Override
  public abstract Armor combineArmorPieces(Armor otherArmor);

  @Override
  public Armor replaceWithBetterArmor(Armor otherArmor) {
    // Armor can't be null
    if (otherArmor == null) {
      throw new IllegalArgumentException("Null gear.");
    }

    System.out.printf("Attempting to switch %s with %s->%n", this.armorName,
            otherArmor.getTheNameOfArmor());

    // If the armor types are the same and they can't be combined
    if (this.getArmorType().equals(otherArmor.getArmorType()) && !this.canBeCombined(otherArmor)) {
      // If they are both already combined.  Now we can check stats.
      if (this.flagCombined() && otherArmor.flagCombined()) {
        // If the other armor is better, switch.
        if (this.compareTo(otherArmor) > 0) {
          System.out.print("Armor switched!\n");

          return otherArmor;
        } else {
          System.out.print("Keeping original armor.\n");

          return this;
        }
      } else {
        System.out.print("Keeping original armor.\n");

        return this;
      }
    }
    System.out.print("Keeping original armor.\n");

    return this;
  }

  /**
   * Check if armor can be combined.
   *
   * @param otherArmor Armor being checked
   * @return whether the armor can be combined
   */
  protected boolean canBeCombined(Armor otherArmor) {
    // check if the items are already combined.
    if (this.flagCombined() || otherArmor.flagCombined()) {
      return false;
    }

    // Are they the same kind of armor.
    if (!this.armorType.equals(otherArmor.getArmorType())) {
      return false;
    }

    // check if they have the same adj
    String noun = armorName.split(" ")[0];
    String nounOther = otherArmor.getTheNameOfArmor().split(" ")[0];

    return !noun.equals(nounOther);
  }

  @Override
  public int compareTo(Armor o) {
    //  Check the types
    if (this instanceof HeadArmor && !(o instanceof HeadArmor)) {
      return -1;
    } else if (!(this instanceof HeadArmor) && (o instanceof HeadArmor)) {
      return 1;
    } else if (this instanceof FootArmor && !(o instanceof FootArmor)) {
      return 1;
    } else if (!(this instanceof FootArmor) && o instanceof FootArmor) {
      return -1;
    }

    // Check if they are the same type and whether they are a combined piece.
    // whether combined
    if (this.flagCombined() && !o.flagCombined()) {
      return 1;
    } else if (!(this.flagCombined()) && o.flagCombined()) {
      return -1;
    }

    if (this.getAttackPower() == o.getAttackPower()) {
      return -Integer.compare(this.getArmorDefense(), o.getArmorDefense());
    } else if (this.getArmorDefense() == o.getArmorDefense()) {
      return -Integer.compare(this.getAttackPower(), o.getAttackPower());
    }

    return -Integer.compare(this.getAttackPower(), o.getAttackPower());
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof Armor)) {
      return false;
    }

    Armor that = (Armor) obj;

    return this.armorType.equals(that.getArmorType())
            && this.armorName.equals(that.getTheNameOfArmor())
            && this.getAttackPower() == that.getAttackPower()
            && this.getArmorDefense() == that.getArmorDefense();
  }

  @Override
  public String toString() {
    // Print out armor stats.
    return String.format("%s %s, attack = %d, defense = %d.",
            this.armorType, this.armorName, this.getAttackPower(), this.getArmorDefense());
  }

  @Override
  public int hashCode() {
    return this.toString().hashCode();
  }
}
