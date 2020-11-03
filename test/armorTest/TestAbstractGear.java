package armorTest;

import org.junit.Before;
import org.junit.Test;

import armor.FootArmor;
import armor.Armor;
import armor.TypeOfArmor;
import armor.HandArmor;
import armor.HeadArmor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the abstract armor class.
 */
public class TestAbstractGear {
  private Armor headArmorNotCombined;
  private Armor handArmorNotCombined;
  private Armor footArmorNotCombined;
  private Armor headArmorCombined;
  private Armor handArmorCombined;
  private Armor footArmorCombined;

  @Before
  public void setup() {
    headArmorNotCombined = new HeadArmor("basic head", false, 5);
    handArmorNotCombined = new HandArmor("basic hand", false, 2);
    footArmorNotCombined = new FootArmor("basic foot", false, 5, 2);
    headArmorCombined = new HeadArmor("basic head", true, 4);
    handArmorCombined = new HandArmor("basic hand", true, 5);
    footArmorCombined = new FootArmor("basic foot", true, 6, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void invalidName() {
    Armor test = new HeadArmor("awesome", false, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void nullName() {
    Armor test = new HeadArmor(null, false, 1);
  }


  @Test
  public void getTypeOfArmor() {
    assertEquals(TypeOfArmor.HeadArmor, headArmorNotCombined.getArmorType());
    assertEquals(TypeOfArmor.HandArmor, handArmorNotCombined.getArmorType());
    assertEquals(TypeOfArmor.FootArmor, footArmorNotCombined.getArmorType());
  }

  @Test
  public void getName() {
    assertEquals("basic head", headArmorNotCombined.getTheNameOfArmor());
    assertEquals("basic hand", handArmorNotCombined.getTheNameOfArmor());
    assertEquals("basic foot", footArmorNotCombined.getTheNameOfArmor());
  }

  @Test
  public void flagCombined() {
    assertFalse(headArmorNotCombined.flagCombined());
    assertFalse(handArmorNotCombined.flagCombined());
    assertFalse(footArmorNotCombined.flagCombined());
    assertTrue(headArmorCombined.flagCombined());
    assertTrue(handArmorCombined.flagCombined());
    assertTrue(footArmorCombined.flagCombined());
  }

  @Test (expected = IllegalArgumentException.class)
  public void replaceWithBetterArmorNull() {
    Armor wimpyHead = new HeadArmor("wimpy head", true, 5);
    wimpyHead.replaceWithBetterArmor(null);
  }

  @Test
  public void replaceWithBetterArmorHeadCombined() {
    Armor wimpyHead = new HeadArmor("wimpy head", true, 5);
    Armor awesomeHead = new HeadArmor("awesome head", true, 10);
    assertEquals(awesomeHead, wimpyHead.replaceWithBetterArmor(awesomeHead));
  }

  @Test
  public void replaceWithBetterArmorHeadNotCombined() {
    Armor wimpyHead = new HeadArmor("wimpy head", false, 5);
    Armor awesomeHead = new HeadArmor("awesome head", true, 10);
    assertEquals(wimpyHead, wimpyHead.replaceWithBetterArmor(awesomeHead));
    assertEquals(awesomeHead, awesomeHead.replaceWithBetterArmor(wimpyHead));
  }

  @Test
  public void replaceWithBetterArmorHandCombined() {
    Armor wimpyHand = new HandArmor("wimpy hand", true, 5);
    Armor awesomeHand = new HandArmor("awesome hand", true, 10);
    assertEquals(awesomeHand, wimpyHand.replaceWithBetterArmor(awesomeHand));
  }

  @Test
  public void replaceWithBetterArmorHandNotCombined() {
    Armor wimpyHand = new HandArmor("wimpy hand", false, 5);
    Armor awesomeHand = new HandArmor("awesome hand", true, 10);
    assertEquals(wimpyHand, wimpyHand.replaceWithBetterArmor(awesomeHand));
    assertEquals(awesomeHand, awesomeHand.replaceWithBetterArmor(wimpyHand));
  }

  @Test
  public void replaceWithBetterArmorFootCombined() {
    Armor wimpyFoot = new FootArmor("wimpy head", true, 5, 0);
    Armor awesomeFoot = new FootArmor("awesome head", true, 10, 0);
    assertEquals(awesomeFoot, wimpyFoot.replaceWithBetterArmor(awesomeFoot));
  }

  @Test
  public void replaceWithBetterArmorFootNotCombined() {
    Armor wimpyFoot = new FootArmor("wimpy foot", true, 5, 0);
    Armor awesomeFoot = new FootArmor("awesome foot", false, 10, 0);
    assertEquals(wimpyFoot, wimpyFoot.replaceWithBetterArmor(awesomeFoot));
    assertEquals(awesomeFoot, awesomeFoot.replaceWithBetterArmor(wimpyFoot));
  }

  @Test
  public void compareToHeadHand() {
    assertTrue(headArmorNotCombined.compareTo(handArmorNotCombined) < 0);
    assertTrue(headArmorCombined.compareTo(handArmorNotCombined) < 0);
    assertTrue(headArmorNotCombined.compareTo(handArmorCombined) < 0);
    assertTrue(headArmorCombined.compareTo(handArmorCombined) < 0);
  }

  @Test
  public void compareToHeadFoot() {
    assertTrue(headArmorNotCombined.compareTo(footArmorNotCombined) < 0);
    assertTrue(headArmorCombined.compareTo(footArmorNotCombined) < 0);
    assertTrue(headArmorNotCombined.compareTo(footArmorCombined) < 0);
    assertTrue(headArmorCombined.compareTo(footArmorCombined) < 0);
  }

  @Test
  public void compareToHandFoot() {
    assertTrue(handArmorNotCombined.compareTo(footArmorNotCombined) < 0);
    assertTrue(handArmorCombined.compareTo(footArmorNotCombined) < 0);
    assertTrue(handArmorNotCombined.compareTo(footArmorCombined) < 0);
    assertTrue(handArmorCombined.compareTo(footArmorCombined) < 0);
  }

  @Test
  public void compareToCombinedUncombined() {
    assertTrue(headArmorNotCombined.compareTo(headArmorCombined) < 0);
    assertTrue(handArmorNotCombined.compareTo(handArmorCombined) < 0);
    assertTrue(footArmorNotCombined.compareTo(footArmorCombined) < 0);
  }

  @Test
  public void compareToSameAttack() {
    Armor head = new HeadArmor("awesome head", false, 5);
    Armor headOther = new HeadArmor("wimpy head", false, 2);
    assertTrue(head.compareTo(headOther) < 0);
    Armor hand = new HandArmor("awesome hand", false, 5);
    Armor handOther = new HandArmor("wimpy hand", false, 5);
    assertEquals(0, hand.compareTo(handOther));
    Armor foot = new FootArmor("awesome foot", false, 3, 3);
    Armor footOther = new FootArmor("wimpy foot", false, 3, 5);
    assertTrue(foot.compareTo(footOther) > 0);
  }

  @Test
  public void compareToSameDefense() {
    Armor head = new HeadArmor("awesome head", false, 5);
    Armor headOther = new HeadArmor("wimpy head", false, 5);
    assertEquals(0, head.compareTo(headOther));
    Armor hand = new HandArmor("awesome hand", false, 6);
    Armor handOther = new HandArmor("wimpy hand", false, 5);
    assertTrue(hand.compareTo(handOther) < 0);
    Armor foot = new FootArmor("awesome foot", false, 5, 3);
    Armor footOther = new FootArmor("wimpy foot", false, 3, 3);
    assertTrue(foot.compareTo(footOther) < 0);
  }

  @Test
  public void compareToDifferent() {
    Armor foot = new FootArmor("awesome head", false, 5, 3);
    Armor footOther = new FootArmor("wimpy head", false, 3, 5);
    assertTrue(foot.compareTo(footOther) < 0);
  }

  @Test
  public void toStringTest() {
    Armor head = new HeadArmor("awesome head", false, 5);
    Armor hand = new HandArmor("awesome hand", false, 6);
    Armor foot = new FootArmor("awesome foot", false, 5, 3);
    assertEquals("HeadArmor awesome head, attack = 0, defense = 5.",
            head.toString());
    assertEquals("HandArmor awesome hand, attack = 6, defense = 0.",
            hand.toString());
    assertEquals("FootArmor awesome foot, attack = 5, defense = 3.",
            foot.toString());
  }

  @Test
  public void equalsSelf() {
    assertEquals(handArmorCombined, handArmorCombined);
  }

  @Test
  public void equalsNotSame() {
    Armor test = new HeadArmor("basic head", false, 5);
    assertEquals(headArmorNotCombined, test);
  }

  @Test
  public void equalsDifferentStatusSameName() {
    Armor test = new HeadArmor("basic head", false, 2);
    assertNotEquals(headArmorNotCombined, test);
  }

  @Test
  public void equalsDifferentNameSameStatus() {
    Armor test = new HeadArmor("wimpy head", false, 5);
    assertNotEquals(headArmorNotCombined, test);
  }
}
