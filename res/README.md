# RolePlaying
Design a role-playing game where characters go into battle with some degree of attack and defense capabilities (represented as numerical values).

## General Rules
* For simplicity there are 2 characters (Harry Potter and Voldemort) with appropriate base stats.
* A character can be chosen by the first person and then the second.  The same character can't be taken by both people.
* After character selection, each person chooses a set of armor to equip.  
* Once armor has been chosen, then the algorithm will sort and combine gear to maximize that 
  set of armors stats.
* Once the character's have equipped the combat begins and whomever received lower damage wins.

## Running the Game
* The first person chooses a character by entering 1 or 2.
* The second person chooses the other character.
* The second person chooses armor first.
* The first person gets the remaining armor.
* Combat begins automagically and the results are displayed.

## Armor Rules
* The rules for how armor is equipped are:
    * Put uncombined items before combined items.
    * If armor has equal defense but varying attack, pick higher attack.
    * Same attack stat but different defense, take higher defense.
    * Attack, above all, trumps (no pun intended) defense.
    * Combine armor that can be.
    * Take the higher combined armor. 
* Armor is then sorted.
    * If a character has an available slot, equip a piece of armor.
 
