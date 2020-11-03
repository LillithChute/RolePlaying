package armorTest;

import org.junit.Before;
import org.junit.Test;

import armor.Armor;
import armor.FootArmor;

import static org.junit.Assert.assertEquals;

public class TestFootArmor {
  private Armor footArmor;

  @Before
  public void setup() {
    footArmor = new FootArmor("basic foot",
            false, 2, 5);
  }

  @Test
  public void constructor() {
    assertEquals("FootArmor basic foot, attack = 2, defense = 5.",
            footArmor.toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void constructorBadAttack() {
    Armor test = new FootArmor("wimpy foot", false, -5, 0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void constructorBadDefense() {
    Armor test = new FootArmor("wimpy foot", false, 7, -2);
  }

  @Test
  public void getAttackPower() {
    assertEquals(2, footArmor.getAttackPower());
  }

  @Test
  public void getDefense() {
    assertEquals(5, footArmor.getArmorDefense());
  }

  @Test
  public void combinedHeadNotCombined() {
    Armor other = new FootArmor("other foot", false, 5, 10);
    assertEquals(7, footArmor.combineArmorPieces(other).getAttackPower());
    assertEquals(7, other.combineArmorPieces(footArmor).getAttackPower());
    assertEquals(15, footArmor.combineArmorPieces(other).getArmorDefense());
    assertEquals(15, other.combineArmorPieces(footArmor).getArmorDefense());
  }

  @Test (expected = IllegalArgumentException.class)
  public void combinedHeadCombined() {
    Armor other = new FootArmor("new foot", true, 5, 3);
    footArmor.combineArmorPieces(other);
    other.combineArmorPieces(footArmor);
  }

  @Test (expected = IllegalArgumentException.class)
  public void combinedHeadAllCombined() {
    footArmor = new FootArmor("basic foot",
            true, 2, 5);
    Armor other = new FootArmor("new foot",
            true, 5, 3);
    footArmor.combineArmorPieces(other);
    other.combineArmorPieces(footArmor);
  }
}
