package armorTest;

import armor.Armor;
import armor.HandArmor;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test of hand armor.
 */
public class TestHandArmor {
  private Armor handArmor;

  @Before
  public void setup() {
    handArmor = new HandArmor("basic hand",
            false, 2);
  }

  @Test
  public void constructor() {
    assertEquals("HandArmor basic hand, attack = 2, defense = 0.",
            handArmor.toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void constructorBadTest() {
    Armor test = new HandArmor("weak hand", false, -5);
  }

  @Test
  public void getAttackPowerTest() {
    assertEquals(2, handArmor.getAttackPower());
  }

  @Test
  public void getDefenseTest() {
    assertEquals(0, handArmor.getArmorDefense());
  }

  @Test
  public void handNotCombined() {
    Armor off = new HandArmor("off hand", false, 5);
    assertEquals(7, handArmor.combineArmorPieces(off).getAttackPower());
    assertEquals(7, off.combineArmorPieces(handArmor).getAttackPower());
  }

  @Test (expected = IllegalArgumentException.class)
  public void handCombined() {
    Armor off = new HandArmor("off hand", true, 5);
    handArmor.combineArmorPieces(off);
    off.combineArmorPieces(handArmor);
  }

  @Test (expected = IllegalArgumentException.class)
  public void handAllCombined() {
    handArmor = new HandArmor("basic hand",
            true, 2);
    Armor off = new HandArmor("off hand",
            true, 5);
    handArmor.combineArmorPieces(off);
    off.combineArmorPieces(handArmor);
  }
}
