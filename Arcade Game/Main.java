//Lydia Yang, Ethan Sandoval, and Zach Brown

import java.util.*;
import java.lang.Math;

class Main 
{
  public static void main(String[] args) 
  {
    EnemyGenerator enemyGenerator = new EnemyGenerator();
    Hero player;
    String name;
    int directionChoice;
    char interaction;
    
    //Asks for the user for their name and their stats are shown.
    System.out.print("What is your name, traveler? ");
    name = CheckInput.getString();
    player = new Hero(name);
    System.out.println(player.toString());  
  
    //Loads and prints out Map 1. Location of player is shown as "*"
    //User can choose which direction they would like to go.
    System.out.print("1. Go North \n2. Go South \n3. Go East \n4. Go West \n5. Quit\n");
    directionChoice = CheckInput.getIntRange(1,5);
 
    //User chooses which direction they want to go to.
    //Depending on the player's location, a monster is generated or a health potion is given.

    while (directionChoice != 5 && player.getHP() != 0)
    {
      if (directionChoice == 1)
      {
        interaction = player.goNorth();
      }
  
      else if (directionChoice == 2)
      {
        interaction = player.goSouth();
      }
  
      else if (directionChoice == 3)
      {
        interaction = player.goEast();
      }
  
      else if (directionChoice == 4)
      {
        interaction = player.goWest();
      }
  
      else
      {
        break;
      }

      //If hero lands on 'm', they will encounter a monster. If hero lands on 'i', they get more HP.
      //If hero lands on 'f', they go to the next map. If hero lands on 'x', they're about to go
      //out of bounds. If hero lands on 's', they're at the start at the map.
      if (interaction == 'm'){
        monsterRoom(player, enemyGenerator.generateEnemy());
      }
      else if (interaction == 'i'){
        System.out.println("You find a Health Potion! You drink it and restore to full health.");
        player.heal(25);
      }
      else if (interaction == 'f'){
        System.out.println("You found the exit. Proceeding to next level. Good Luck!");
        player.levelUp();
      }
      else if (interaction == 'x'){
        System.out.println("Out of bounds, please choose another direction");
      }
      else if (interaction == 's'){
        System.out.println("You're back at the start again!");
      }
      else{
        System.out.println("Nothing, just... nothing.");
      }

      System.out.println(player.toString());  
      System.out.print("1. Go North \n2. Go South \n3. Go East \n4. Go West \n5. Quit\n");
      directionChoice = CheckInput.getIntRange(1,5);
    }
       
  }

  /**
    If the hero encounters a monster, they'll decide whether to fight the monster 
    or run away. If they choose to fight the monster, fight() would be called.
    If they choose to run away, they wil be taken to a different location
    randomly.
    @param h: object of Hero class
    @param el: object of Enemy class
  */
  public static boolean monsterRoom(Hero h, Enemy el)
  {
    int choice;

    System.out.println("You've encountered a " + el.getName());

    //while loop continues to loop until the user's health is 0.
    while (h.getHP() != 0)
    {
      System.out.println(el + "\n1. Fight\n2. Run Away");
      choice = CheckInput.getIntRange(1,2);

      //If the user chooses 1, fight() is called.
      //If the user beats the monster, the first while loop breaks.
      if (choice == 1)
      {
        fight(h, el);

        if (el.getHP() == 0)
        {
          System.out.println("You defeated the " + el.getName() + "!");
          break;
        }
      }

      //If user chooses to run away, their direction randomly changes.
      else
      {
				Random rand = new Random();
        int ranDirec = 0;
				ranDirec = 1 + rand.nextInt(4);
				if (ranDirec == 1){
					System.out.println("You ran away North.");
					h.goNorth();
					break;
				}else if(ranDirec == 2){
					System.out.println("You ran away South.");
					h.goSouth();
					break;
				}else if(ranDirec == 3){
					System.out.println("You ran away East.");
					h.goEast();
					break;
				}else if(ranDirec == 4){
					System.out.println("You ran away West.");
					h.goWest();
					break;
				}
      }
    }  
    return true;     
  }
  
  /**
    Hero can choose to fight the enemy with attack or magic damage. If they choose to attack,
    physical attacks are generated at a random value. If they choose to use magic, a random
    generator is generated to choose a random spell. 
    @param h: object of Hero
    @param e: object of Hero
    @return: return true if hero's health is greater than 0. Return false if not.
  */
  public static boolean fight(Hero h, Enemy e)
  {
    System.out.println("1. Physical Attack\n2. Magic Attack");
    int choice = CheckInput.getIntRange(1, 2);
    if (choice == 1){
      System.out.println(h.getName() + h.attack(e));
      if (e.getHP() != 0){
        System.out.println(e.getName() + e.attack(h));
      }
      if (h.getHP() != 0){
        return true;
      }
    }
    else {
      System.out.println(h.MAGIC_MENU);
      int magicAttack = CheckInput.getIntRange(1, 3);
      if (magicAttack == 1)
      {
        System.out.println(h.getName() + h.magicMissile(e));
      }
      else if (magicAttack == 2)
      {
        System.out.println(h.getName() + h.fireball(e));
      }
      else
      {
        System.out.println(h.getName() + h.thunderclap(e));
      }
      if (e.getHP() != 0){
        System.out.println(e.getName()+ e.attack(h));
      }
      if (h.getHP() != 0){
        return true;
      }
    }
  return false;
  }
}