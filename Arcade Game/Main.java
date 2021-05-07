import java.util.*;
import java.lang.Math;

class Main 
{
  public static void main(String[] args) 
  {
    Enemy enemy;
    Hero player;
    String name;
    int directionChoice;
    int randItem;
    char interaction;
    Map map = Map.getInstance();

    
    
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

    while (directionChoice != 5 && player.getHP() > 0)
    {
      if (player.getHP() <= 0)
      {
        break;
      }
      else if (directionChoice == 1)
      {
        interaction = player.goNorth();
        map.reveal(player.getLoc()); 
        map.removeCharAtLoc(player.getLoc());    
      }
  
      else if (directionChoice == 2)
      {
        interaction = player.goSouth();
        map.reveal(player.getLoc()); 
        map.removeCharAtLoc(player.getLoc());  
      }
  
      else if (directionChoice == 3)
      {
        interaction = player.goEast();
        map.reveal(player.getLoc()); 
        map.removeCharAtLoc(player.getLoc());  
      }
  
      else if (directionChoice == 4)
      {
        interaction = player.goWest();
        map.reveal(player.getLoc()); 
        map.removeCharAtLoc(player.getLoc());  
      }
  
      else
      {
        break;
      }

      //If hero lands on 'm', they will encounter a monster unless the current coordinates   are in the list of defeated monsters. 
      //If hero lands on 'i', they get more HP or get a key with a possible chance.
      //If hero lands on 'f', they go to the next map. 
      //If hero lands on 'x', they're about to go
      //out of bounds. If hero lands on 's', they're at the start at the map and store shows up.
      
      if (interaction == 'm')
      {       
        monsterRoom(player, EnemyGenerator.generateEnemy(player.getLevel()));
      }

      else if (interaction == 'i')
      { 
        randItem= (int)(Math.random() * 2) + 1;
        
        if (randItem == 1)
        {
          player.pickUpKey();
          System.out.println("You find a key!");
        }

        else
        {
          player.heal(25);
          System.out.println("You find a Health Potion! You drink it and restore to full health.");      
        }
        
      }
      else if (interaction == 'f')
      {
        if (player.hasKey())
        {
          System.out.println("You found the exit. Proceeding to next level. Good Luck!");
          player.levelUp();
          player.useKey();
        }

        else
        {
          System.out.println("You don't have enough keys!");
        }
        
      }
      else if (interaction == 'x')
      {
        System.out.println("Out of bounds, please choose another direction");
      }
      else if (interaction == 's')
      {
        System.out.println("STORE !!");
        store(player);  
      }
      else
      {
        System.out.println("Nothing, just... nothing.");
      }
      if (player.getHP() <= 0)
      {
        System.out.println("\nSorry You Died Better Luck Next Time");
        System.exit(0);
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
    int gold;

    System.out.println("You've encountered a " + el.getName());

    //while loop continues to loop until the user's health is 0.
    while (h.getHP() > 0)
    {
      System.out.println(el.getName() + "\nHP: " + el.getHP() + "/" + el.getMaxHP() + "\n1. Fight\n2. Run Away");
      choice = CheckInput.getIntRange(1,2);

      //If the user chooses 1, fight() is called.
      //If the user beats the monster, the first while loop breaks.
      if (choice == 1)
      {

        fight(h, el);
        
        if (el.getHP() <= 0)
        {
          gold = (int)(Math.random() * 10) + 3;
          h.collectGold(gold);
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

				if (ranDirec == 1)
        {
					System.out.println("You ran away North.");
					h.goNorth();
					break;
				}
        else if(ranDirec == 2)
        {
					System.out.println("You ran away South.");
					h.goSouth();
					break;
				}
        else if(ranDirec == 3)
        {
					System.out.println("You ran away East.");
					h.goEast();
					break;
				}
        else if(ranDirec == 4)
        {
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
    if (choice == 1)
    {
      System.out.println(h.getName() + h.attack(e));
      if (e.getHP() > 0)
      {
        int level = h.getLevel();
        for (int i = 0; i< level; i++)
        {
          System.out.println(e.getName() + e.attack(h));
        }
      }
      // if (h.getHP() > 0)
      // {
      //   System.out.println("Hero Hp:" + h.getHP());
      //   return true;
      // }
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
      if (e.getHP() > 0)
      {
        System.out.println(e.getName()+ e.attack(h));
      }
      if (h.getHP() >  0)
      {
        return true;
      }
    }
  return false;
  }

  /**
   * When Hero goes back to 's', a store shows up. They can buy a health potion or key. If they don't have
   * enough gold, then the program would notify the user.
   * @param h: object of Hero
   */
  public static void store(Hero h){
    int input;
    int healthPotion = 25;
    int key = 50;

    System.out.println("1. Health Potion\n2. Key\n3. Exit Store");
    input = CheckInput.getIntRange(1,3);

      if(input == 1 && h.getGold() >= 25){
           h.spendGold(healthPotion);
           h.heal(25);
      }
      else if(input == 2 && h.getGold() >= 50){
           h.spendGold(key);
           h.pickUpKey();
      }
      else if (input == 3){
        System.out.println("Store exit...");
      }
      else{
          System.out.println("You don't have enough gold!");
      }
    }
  
}