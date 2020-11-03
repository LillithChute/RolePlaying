import armor.Armor;
import armor.FootArmor;
import armor.HandArmor;
import armor.HeadArmor;
import fight.AdventurerCombat;
import fight.ArmorList;
import fight.Combat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import roleplayer.Adventurer;
import roleplayer.RolePlayer;

public class Driver {

  /**
   * Driver's main method.
   *
   * @param args arguments
   */
  public static void main(String[] args) {

    final Combat currentCombat = new AdventurerCombat();
    final RolePlayer adventurerOne;
    final RolePlayer adventurerTwo;
    final ArmorList armorSelectionOne = createArmorListPotter();
    final ArmorList armorSelectionTwo = createArmorListVoldemort();

    System.out.println("- Begin Combat -");
    System.out.println("- Character 1: Harry Potter, attack = 6, defense = 5");
    System.out.println("- Character 2: Voldemort, attack = 6 defense = 4");
    System.out.println("");
    System.out.println("- Player 1 select a character...(enter 1 or 2)");

    Scanner scanner = new Scanner(System.in);
    String choiceOne = scanner.next();

    while (!choiceOne.equals("1") && !choiceOne.equals("2")) {
      System.out.println("Please try again.");
      choiceOne = scanner.next();
    }

    adventurerOne = createPlayer(choiceOne);

    System.out.println("- Player 2 chooses a character...(enter 1 or 2)");
    String choiceTwo;
    choiceTwo = scanner.next();
    
    while ((!choiceTwo.equals("1") && !choiceTwo.equals("2"))) {
      if (choiceOne.equals(choiceTwo)) {
        System.out.printf("Character %s has already been selected\n", choiceOne);
      }
      System.out.println("Please enter again...");
      choiceTwo = scanner.next();
    }
    
    adventurerTwo = createPlayer(choiceTwo);

    currentCombat.addAdventurer(adventurerOne);
    currentCombat.addAdventurer(adventurerTwo);

    System.out.printf("- %s VS %s -%n\n",
            adventurerOne.getAdventurerName(), adventurerTwo.getAdventurerName());
    System.out.println("- Armor 1");
    System.out.println(armorSelectionOne);
    System.out.println("- Armor 2");
    System.out.println(armorSelectionTwo);
    System.out.println("- Player 2 chooses a Armor set...(enter 1 or 2)");

    choiceOne = scanner.next();
    while (!choiceOne.equals("1") && !choiceOne.equals("2")) {
      System.out.println("Please enter again...");
      choiceOne = scanner.next();
    }
    
    // Set up the armor
    if (choiceOne.equals("1")) {
      currentCombat.addArmorList(adventurerTwo.getAdventurerName(), armorSelectionOne);
      currentCombat.addArmorList(adventurerOne.getAdventurerName(), armorSelectionTwo);
    } else {
      currentCombat.addArmorList(adventurerOne.getAdventurerName(), armorSelectionOne);
      currentCombat.addArmorList(adventurerTwo.getAdventurerName(), armorSelectionTwo);
    }
    
    currentCombat.putArmorOn();
    
    System.out.println("- Adventurer Stats -");
    System.out.println(currentCombat.getAdventurerState());

    System.out.println("- Combat Begins -");
    System.out.println("");
    System.out.println(currentCombat.getCombatResult());
    System.out.println("");
    System.out.println("- Combat Ends -");
  }

  private static RolePlayer createPlayer(String selection) {

    return switch (selection) {
      case "2" -> new Adventurer("Voldemort", 6, 4);
      default -> new Adventurer("Harry Potter", 6, 5);
    };
  }

  private static ArmorList createArmorListPotter() {
    Armor head1 = new HeadArmor(
            "Immaculate Helmet", true, 15);
    Armor head2 = new HeadArmor(
            "Golden Helm", false, 6);
    Armor head3 = new HeadArmor(
            "Frumpy Cap", false, 5);
    Armor hand1 = new HandArmor(
            "Kingly glove", true, 16);
    Armor hand2 = new HandArmor(
            "Noble glove", true, 9);
    Armor hand3 = new HandArmor(
            "Holey mitten", false, 6);
    Armor foot1 = new FootArmor(
            "Beastly boot", false, 5, 2);
    Armor foot2 = new FootArmor(
            "Tough boot", false, 2, 5);
    Armor foot3 = new FootArmor(
            "Useless boot", false, 3, 2);
    Armor foot4 = new FootArmor(
            "Soggy foot", false, 1, 3);

    ArrayList<Armor> gearList = new ArrayList<>(
            Arrays.asList(head1, head2, head3, hand1, hand2, hand3,
                    foot1, foot2, foot3, foot4)
    );

    return new ArmorList(gearList);
  }

  private static ArmorList createArmorListVoldemort() {
    Armor head1 = new HeadArmor(
            "Supreme helm", true, 10);
    Armor head2 = new HeadArmor(
            "Sturdy helmet", false, 6);
    Armor head3 = new HeadArmor(
            "Cracked helmet", false, 5);
    Armor hand1 = new HandArmor(
            "Crushing glove", false, 10);
    Armor hand2 = new HandArmor(
            "Sturdy glove", true, 8);
    Armor hand3 = new HandArmor(
            "Ratty glove", false, 6);
    Armor foot1 = new FootArmor(
            "Destructive boot", true, 10, 5);
    Armor foot2 = new FootArmor(
            "Protective boot", false, 0, 10);
    Armor foot3 = new FootArmor(
            "Smelly boot", false, 2, 0);
    Armor foot4 = new FootArmor(
            "Chewed boot", false, 5, 3);

    ArrayList<Armor> gearList = new ArrayList<>(
            Arrays.asList(head1, head2, head3, hand1, hand2, hand3,
                    foot1, foot2, foot3, foot4)
    );

    return new ArmorList(gearList);
  }
}
