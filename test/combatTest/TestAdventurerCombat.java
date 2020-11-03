package combatTest;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import armor.Armor;
import armor.HeadArmor;
import armor.HandArmor;
import armor.FootArmor;
import fight.AdventurerCombat;
import fight.ArmorList;
import fight.Combat;
import roleplayer.RolePlayer;
import roleplayer.Adventurer;

import static org.junit.Assert.assertEquals;

/**
 * This tests the combat simulator.
 */
public class TestAdventurerCombat {

  private RolePlayer readyPlayerOne;
  private RolePlayer readyPlayerTwo;
  private Combat combat;
  private ArmorList armorListOne;
  private ArmorList armorListTwo;

  @Before
  public void setup() {
    combat = new AdventurerCombat();
    readyPlayerOne = new Adventurer("skywalker", 6, 4);
    readyPlayerTwo = new Adventurer("vader", 5, 4);

    armorListOne = new ArmorList(armorListSetOne());
    armorListTwo = new ArmorList(gearListBuilder2());
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullPlayerTest() {
    combat.addAdventurer(null);
  }

  @Test
  public void addAdventurersTest() {
    combat.addAdventurer(readyPlayerOne);
    combat.addAdventurer(readyPlayerTwo);
    assertEquals("- skywalker: \n" +
                    "\tattack power = 6, defense = 4\n" +
                    "\tfinal attack power = 6, final defense = 0\n" +
                    "- head armor\n" +
                    "\tThere is no armor.\n" +
                    "- hand armor\n" +
                    "\tThere is no armor.\n" +
                    "- foot armor\n" +
                    "\tThere is no armor.\n" +
                    "\n" +
                    "- vader: \n" +
                    "\tattack power = 5, defense = 4\n" +
                    "\tfinal attack power = 5, final defense = 0\n" +
                    "- head armor\n" +
                    "\tThere is no armor.\n" +
                    "- hand armor\n" +
                    "\tThere is no armor.\n" +
                    "- foot armor\n" +
                    "\tThere is no armor.\n" +
                    "\n",
            combat.getAdventurerState());
  }

  @Test (expected = IllegalStateException.class)
  public void tooManyAdventurersTest() {
    RolePlayer newPlayer = new Adventurer("s", 2, 5);
    combat.addAdventurer(readyPlayerOne);
    combat.addAdventurer(readyPlayerTwo);
    combat.addAdventurer(newPlayer);
  }

  @Test (expected = IllegalArgumentException.class)
  public void duplicateAdventurerTest() {
    combat.addAdventurer(readyPlayerOne);
    combat.addAdventurer(readyPlayerOne);
  }

  @Test (expected = IllegalArgumentException.class)
  public void addNoArmorListTest() {
    combat.addArmorList("skywalker", null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void addNoNameArmorListTest() {
    combat.addArmorList(null, armorListOne);
  }

  @Test (expected = IllegalArgumentException.class)
  public void addArmorBeforeAdventurerTest() {
    combat.addArmorList("skywalker", armorListOne);
  }

  @Test (expected = IllegalStateException.class)
  public void addTooMuchArmorTest() {
    combat.addAdventurer(readyPlayerOne);
    combat.addAdventurer(readyPlayerTwo);
    combat.addArmorList("skywalker", armorListOne);
    combat.addArmorList("vader", armorListOne);
    combat.addArmorList("s", armorListOne);
  }

  @Test (expected = IllegalArgumentException.class)
  public void addArmorWithIncorrectNameTest() {
    combat.addAdventurer(readyPlayerOne);
    combat.addAdventurer(readyPlayerTwo);
    combat.addArmorList("skywalker", armorListOne);
    combat.addArmorList("j", armorListOne);
  }

  @Test
  public void addArmorList() {
    combat.addAdventurer(readyPlayerOne);
    combat.addAdventurer(readyPlayerTwo);
    combat.addArmorList("skywalker", armorListOne);
    combat.addArmorList("vader", armorListOne);
    assertEquals("skywalker:\n"
                    + "strong helmet, attack 0, defense 10\n"
                    + "stalwart helmet, attack 0, defense 6\n"
                    + "noodley helmet, attack 0, defense 5\n"
                    + "strong glove, attack 10, defense 0\n"
                    + "stalwart glove, attack 8, defense 0\n"
                    + "noodley glove, attack 6, defense 0\n"
                    + "attack boot, attack 10, defense 0\n"
                    + "defense boot, attack 0, defense 10\n"
                    + "wimpyattack boot, attack 2, defense 0\n"
                    + "saddefense boot, attack 0, defense 5\n"
                    + "vader:\n"
                    + "strong helmet, attack 0, defense 10\n"
                    + "stalwart helmet, attack 0, defense 6\n"
                    + "noodley helmet, attack 0, defense 5\n"
                    + "strong glove, attack 10, defense 0\n"
                    + "stalwart glove, attack 8, defense 0\n"
                    + "noodley glove, attack 6, defense 0\n"
                    + "attack boot, attack 10, defense 0\n"
                    + "defense boot, attack 0, defense 10\n"
                    + "wimpyattack boot, attack 2, defense 0\n"
                    + "saddefense boot, attack 0, defense 5\n",
            combat.getArmorList());
  }

  @Test (expected = IllegalStateException.class)
  public void getBlankArmorTest() {
    combat.getArmorList();
  }

  @Test
  public void getArmorForAdventurerOne() {
    combat.addAdventurer(readyPlayerOne);
    combat.addArmorList("skywalker", armorListOne);
    assertEquals("skywalker:\n"
                    + "strong helmet, attack 0, defense 10\n"
                    + "stalwart helmet, attack 0, defense 6\n"
                    + "noodley helmet, attack 0, defense 5\n"
                    + "strong glove, attack 10, defense 0\n"
                    + "stalwart glove, attack 8, defense 0\n"
                    + "noodley glove, attack 6, defense 0\n"
                    + "attack boot, attack 10, defense 0\n"
                    + "defense boot, attack 0, defense 10\n"
                    + "wimpyattack boot, attack 2, defense 0\n"
                    + "saddefense boot, attack 0, defense 5\n",
            combat.getArmorList());
  }

  @Test
  public void getArmorForAdventurerTwo() {
    combat.addAdventurer(readyPlayerOne);
    combat.addAdventurer(readyPlayerTwo);
    combat.addArmorList("skywalker", armorListOne);
    combat.addArmorList("vader", armorListOne);
    assertEquals("skywalker:\n"
                    + "strong helmet, attack 0, defense 10\n"
                    + "stalwart helmet, attack 0, defense 6\n"
                    + "noodley helmet, attack 0, defense 5\n"
                    + "strong glove, attack 10, defense 0\n"
                    + "stalwart glove, attack 8, defense 0\n"
                    + "noodley glove, attack 6, defense 0\n"
                    + "attack boot, attack 10, defense 0\n"
                    + "defense boot, attack 0, defense 10\n"
                    + "wimpyattack boot, attack 2, defense 0\n"
                    + "saddefense boot, attack 0, defense 5\n"
                    + "vader:\n"
                    + "strong helmet, attack 0, defense 10\n"
                    + "stalwart helmet, attack 0, defense 6\n"
                    + "noodley helmet, attack 0, defense 5\n"
                    + "strong glove, attack 10, defense 0\n"
                    + "stalwart glove, attack 8, defense 0\n"
                    + "noodley glove, attack 6, defense 0\n"
                    + "attack boot, attack 10, defense 0\n"
                    + "defense boot, attack 0, defense 10\n"
                    + "wimpyattack boot, attack 2, defense 0\n"
                    + "saddefense boot, attack 0, defense 5\n",
            combat.getArmorList());
  }

  @Test (expected = IllegalStateException.class)
  public void putArmorOnTooSoonTest() {
    combat.putArmorOn();
  }

  @Test
  public void putArmorOn() {
    combat.addAdventurer(readyPlayerOne);
    combat.addAdventurer(readyPlayerTwo);
    combat.addArmorList("skywalker", armorListOne);
    combat.addArmorList("vader", armorListTwo);
    combat.putArmorOn();
    assertEquals("- skywalker: \n" +
                    "\tattack power = 6, defense = 4\n" +
                    "\tfinal attack power = 40, final defense = 32\n" +
                    "- head armor\n" +
                    "\tstalwart, noodley helmet, attack = 0, defense = 11\n" +
                    "- hand armor\n" +
                    "\tstrong, noodley glove, attack = 16, defense = 0\n" +
                    "\tstalwart glove, attack = 8, defense = 0\n" +
                    "- foot armor\n" +
                    "\tattack boot, attack = 10, defense = 0\n" +
                    "\tsaddefense boot, attack = 0, defense = 5\n" +
                    "\n" +
                    "- vader: \n" +
                    "\tattack power = 5, defense = 4\n" +
                    "\tfinal attack power = 37, final defense = 54\n" +
                    "- head armor\n" +
                    "\trighteous helmet, attack = 0, defense = 15\n" +
                    "- hand armor\n" +
                    "\tnoodley glove, attack = 6, defense = 0\n" +
                    "\tstrong glove, attack = 15, defense = 0\n" +
                    "- foot armor\n" +
                    "\tattack, wimpyattack boot, attack = 8, defense = 4\n" +
                    "\tdefense, saddefense boot, attack = 3, defense = 8\n" +
                    "\n",
            combat.getAdventurerState());
  }


  @Test (expected = IllegalStateException.class)
  public void getAdventurerStateEmptyTest() {
    assertEquals("", combat.getAdventurerState());
  }

  @Test
  public void getFirstAdventurerStateTest() {
    combat.addAdventurer(readyPlayerOne);
    assertEquals("- skywalker: \n" +
                    "\tattack power = 6, defense = 4\n" +
                    "\tfinal attack power = 6, final defense = 0\n" +
                    "- head armor\n" +
                    "\tThere is no armor.\n" +
                    "- hand armor\n" +
                    "\tThere is no armor.\n" +
                    "- foot armor\n" +
                    "\tThere is no armor.\n" +
                    "\n",
            combat.getAdventurerState());
  }

  @Test
  public void getSecondAdventurerStateTest() {
    combat.addAdventurer(readyPlayerOne);
    combat.addAdventurer(readyPlayerTwo);
    assertEquals("- skywalker: \n" +
                    "\tattack power = 6, defense = 4\n" +
                    "\tfinal attack power = 6, final defense = 0\n" +
                    "- head armor\n" +
                    "\tThere is no armor.\n" +
                    "- hand armor\n" +
                    "\tThere is no armor.\n" +
                    "- foot armor\n" +
                    "\tThere is no armor.\n" +
                    "\n" +
                    "- vader: \n" +
                    "\tattack power = 5, defense = 4\n" +
                    "\tfinal attack power = 5, final defense = 0\n" +
                    "- head armor\n" +
                    "\tThere is no armor.\n" +
                    "- hand armor\n" +
                    "\tThere is no armor.\n" +
                    "- foot armor\n" +
                    "\tThere is no armor.\n" +
                    "\n",
            combat.getAdventurerState());
  }

  @Test (expected = IllegalStateException.class)
  public void getCombatResultTooSoonTest() {
    combat.getCombatResult();
  }

  @Test (expected = IllegalStateException.class)
  public void getCombatResultTooSoonAdventurerOneTest() {
    combat.addAdventurer(readyPlayerOne);
    combat.getCombatResult();
  }

  @Test
  public void getCombatResult() {
    combat.addAdventurer(readyPlayerOne);
    combat.addAdventurer(readyPlayerTwo);
    combat.addArmorList("skywalker", armorListOne);
    combat.addArmorList("vader", armorListTwo);
    combat.putArmorOn();
    assertEquals("skywalker gets 5 damage, vader gets -14 damage. vader wins.",
            combat.getCombatResult());
  }

  private ArrayList<Armor> armorListSetOne() {
    Armor head1 = new HeadArmor(
            "strong helmet", true, 10);
    Armor head2 = new HeadArmor(
            "stalwart helmet", false, 6);
    Armor head3 = new HeadArmor(
            "noodley helmet", false, 5);
    Armor hand1 = new HandArmor(
            "strong glove", false, 10);
    Armor hand2 = new HandArmor(
            "stalwart glove", true, 8);
    Armor hand3 = new HandArmor(
            "noodley glove", false, 6);
    Armor foot1 = new FootArmor(
            "attack boot", true, 10, 0);
    Armor foot2 = new FootArmor(
            "defense boot", false, 0, 10);
    Armor foot3 = new FootArmor(
            "wimpyattack boot", false, 2, 0);
    Armor foot4 = new FootArmor(
            "saddefense boot", false, 0, 5);

    ArrayList<Armor> ArmorList = new ArrayList<>(
            Arrays.asList(head1, head2, head3, hand1, hand2, hand3,
                    foot1, foot2, foot3, foot4));
    return ArmorList;
  }

  private ArrayList<Armor> gearListBuilder2() {
    Armor head1 = new HeadArmor(
            "righteous helmet", true, 15);
    Armor head2 = new HeadArmor(
            "stalwart helmet", false, 6);
    Armor head3 = new HeadArmor(
            "noodley helmet", false, 5);
    Armor hand1 = new HandArmor(
            "strong glove", true, 15);
    Armor hand2 = new HandArmor(
            "stalwart glove", true, 8);
    Armor hand3 = new HandArmor(
            "noodley glove", false, 6);
    Armor foot1 = new FootArmor(
            "attack boot", false, 5, 2);
    Armor foot2 = new FootArmor(
            "defense boot", false, 2, 5);
    Armor foot3 = new FootArmor(
            "wimpyattack boot", false, 3, 2);
    Armor foot4 = new FootArmor(
            "saddefense boot", false, 1, 3);

    ArrayList<Armor> ArmorList = new ArrayList<>(
            Arrays.asList(head1, head2, head3, hand1, hand2, hand3,
                    foot1, foot2, foot3, foot4)
    );
    return ArmorList;
  }

  @Test
  public void toStringTest() {
    assertEquals("Combat.", combat.toString());
  }
}
