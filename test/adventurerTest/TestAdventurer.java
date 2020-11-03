package adventurerTest;

import org.junit.Before;
import org.junit.Test;

import roleplayer.RolePlayer;
import roleplayer.Adventurer;
import armor.Armor;
import armor.FootArmor;
import armor.HandArmor;
import armor.HeadArmor;

import static org.junit.Assert.assertEquals;

/**
 * Test for the adventurer.
 */
public class TestAdventurer {

  private RolePlayer rolePlayer;

  @Before
  public void setup() {
    rolePlayer = new Adventurer("Lillith The Magnificent", 5, 2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void nullName() {
    RolePlayer test = new Adventurer(null, 5, 3);
  }

  @Test (expected = IllegalArgumentException.class)
  public void emptyStringName() {
    RolePlayer test = new Adventurer("", 5, 3);
  }

  @Test (expected = IllegalArgumentException.class)
  public void blankName() {
    RolePlayer test = new Adventurer(" ", 5, 3);
  }

  @Test (expected = IllegalArgumentException.class)
  public void badAttack() {
    RolePlayer test = new Adventurer("s", -5, 3);
  }

  @Test (expected = IllegalArgumentException.class)
  public void badDefense() {
    RolePlayer test = new Adventurer("s", 5, -3);
  }

  @Test (expected = IllegalArgumentException.class)
  public void addArmorNull() {
    rolePlayer.addArmor(null);
  }

  @Test
  public void addOneHeadGear() {
    Armor head = new HeadArmor("basic head", false, 2);
    rolePlayer.addArmor(head);
    assertEquals(head, rolePlayer.getAllHeadArmor().get(0));
  }

//  @Test
//  public void addTwoHeadGear() {
//    // start with an uncombined head armor.
//    Armor head = new HeadArmor("basic head", false, 2);
//    rolePlayer.addArmor(head);
//
//    // add the same kind, no changes.
//    rolePlayer.addArmor(head);
//    assertEquals(1, rolePlayer.getAllHeadArmor().size());
//    assertEquals(head, rolePlayer.getAllHeadArmor().get(0));
//
//    // add a new armor piece combined, no change.
//    Armor another = new HeadArmor("awesome head", true, 10);
//    rolePlayer.addArmor(another);
//
//    assertEquals(1, rolePlayer.getAllHeadArmor().size());
//    assertEquals(head, rolePlayer.getAllHeadArmor().get(0));
//
//    // add a new uncombined
//    another = new HeadArmor("awesome head", false, 10);
//    rolePlayer.addArmor(another);
//
//    assertEquals(1, rolePlayer.getAllHeadArmor().size());
//    assertEquals(new HeadArmor("basic, awesome head", true, 12),
//            rolePlayer.getAllHeadArmor().get(0));
//
//    rolePlayer.addArmor(another);
//    assertEquals(1, rolePlayer.getAllHeadArmor().size());
//    assertEquals(new HeadArmor("basic, awesome head", true, 12),
//            rolePlayer.getAllHeadArmor().get(0));
//  }

  @Test
  public void addAdditionalHeadGear() {
    // start with a uncombined head
    Armor head = new HeadArmor("basic head", false, 2);
    rolePlayer.addArmor(head);
    
    // add a new uncombined
    Armor another = new HeadArmor("awesome head", false, 10);
    rolePlayer.addArmor(another);
    
    // add a new uncombined, no changes
    another = new HeadArmor("epic head", false, 15);
    assertEquals(1, rolePlayer.getAllHeadArmor().size());
    assertEquals(new HeadArmor("basic, awesome head", true, 12),
            rolePlayer.getAllHeadArmor().get(0));
    
    // add a new better combined, replace
    another = new HeadArmor("epic head", true, 15);
    rolePlayer.addArmor(another);
    assertEquals(1, rolePlayer.getAllHeadArmor().size());
    assertEquals(another, rolePlayer.getAllHeadArmor().get(0));
  }

  @Test
  public void addHandArmor() {
    Armor hand = new HandArmor("basic hand", false, 2);
    rolePlayer.addArmor(hand);
    assertEquals(hand, rolePlayer.getAllHandArmor().get(0));
  }

  @Test
  public void addFootArmor() {
    Armor foot = new FootArmor("basic foot", false, 2, 5);
    rolePlayer.addArmor(foot);
    assertEquals(foot, rolePlayer.getAllFootArmor().get(0));
  }

  @Test
  public void getAllHeadArmor() {
    // blank
    assertEquals(0, rolePlayer.getAllHeadArmor().size());
    // add one
    Armor head = new HeadArmor("basic head", false, 2);
    rolePlayer.addArmor(head);
    assertEquals(1, rolePlayer.getAllHeadArmor().size());
    assertEquals(head, rolePlayer.getAllHeadArmor().get(0));
  }

  @Test
  public void getAllHandArmor() {
    assertEquals(0, rolePlayer.getAllHandArmor().size());

    Armor hand = new HandArmor("basic hand", false, 2);
    rolePlayer.addArmor(hand);
    assertEquals(1, rolePlayer.getAllHandArmor().size());
    assertEquals(hand, rolePlayer.getAllHandArmor().get(0));
  }

  @Test
  public void getAllFootArmor() {
    assertEquals(0, rolePlayer.getAllFootArmor().size());

    Armor foot = new FootArmor("basic foot", false, 2, 4);
    rolePlayer.addArmor(foot);
    assertEquals(1, rolePlayer.getAllFootArmor().size());
    assertEquals(foot, rolePlayer.getAllFootArmor().get(0));
  }

  @Test
  public void getName() {
    assertEquals("lillith the magnificent", rolePlayer.getAdventurerName());
  }


  @Test
  public void toStringTest() {
    assertEquals("lillith the magnificent, attack = 5, defense = 2.",
            rolePlayer.toString());
  }
}
