package armorTest;

import org.junit.Before;
import org.junit.Test;

import armor.HeadArmor;
import armor.Armor;

import static org.junit.Assert.assertEquals;
/**
 * Tests for helmets.
 */
public class TestHeadArmor {
  private Armor headArmor;

  @Before
  public void setup() {
    headArmor = new HeadArmor("basic helmet",
            false, 2);
  }

  @Test
  public void constructor() {
    assertEquals("HeadArmor basic helmet, attack = 0, defense = 2.",
            headArmor.toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void constructorNegative() {
    Armor test = new HeadArmor("Wimpy Helmet", false, -5);
  }

  @Test
  public void getAttackPowerTest() {
    assertEquals(0, headArmor.getAttackPower());
  }

  @Test
  public void getDefenseTest() {
    assertEquals(2, headArmor.getArmorDefense());
  }

  @Test
  public void helmetNotCombinedTest() {
    Armor second = new HeadArmor("new helmet", false, 5);
    assertEquals(7, headArmor.combineArmorPieces(second).getArmorDefense());
    assertEquals(7, second.combineArmorPieces(headArmor).getArmorDefense());
  }

  @Test (expected = IllegalArgumentException.class)
  public void helmetCombinedTest() {
    Armor second = new HeadArmor("new helmet", true, 5);
    headArmor.combineArmorPieces(second);
    second.combineArmorPieces(headArmor);
  }

  @Test(expected = IllegalArgumentException.class)
  public void helmetAllCombinedTest() {
    headArmor = new HeadArmor("basic helmet",
            true, 2);
    Armor second = new HeadArmor("new helmet",
            true, 5);
    headArmor.combineArmorPieces(second);
    second.combineArmorPieces(headArmor);
  }
}
