//Lydia Yang and Ethan Sandoval
import java.util.Random;
import java.util.Scanner;

public class Hero extends Entity implements Magical
{
  private Point position;
  private int level = 1;
  private int gold = 0;
  private int key = 0;

  /**
  Constructor that creates a hero using the Entity's contructor then creates a map and puts the hero on the map
  @param n, name of the hero
  */
  public Hero(String n)
  {
    super(n, 25);
    Map.getInstance().loadMap(level);
    position = Map.getInstance().findStart();
    Map.getInstance().reveal(position);
  }

  /**
    When hero completes a map, their level increases by one and a new map loads.
    This will also find the 's' in the map and reveals the map on screen.
  */
  public void levelUp()
  {
    level++;
    Map.getInstance().loadMap(level % 3);
    position = Map.getInstance().findStart();
    Map.getInstance().reveal(position);
   
  }

  /**
   * Gets Hero's current level.
   * @return: Hero's current level
   */
  public int getLevel() {
      return level;
  }

  /**
  Method of the hero's physical attack which does random damage in a range and applies that damage to the entity.
  @param: object of Entity
  @return: a string indicating the attack and the damage they did to the enemy with their attack
  */
  @Override
  public String attack(Entity e){
    int randDamage = (int)(Math.random() * 4) + 1;
    e.takeDamage(randDamage);
    return " slashes " + e.getName() + " with his sword doing " + "" + randDamage + " damage";
  }
  
  /**
  Method of the hero's magicMissile attack which does random damage in a range and applies that damage to the entity.
  @param: object of Entity
  @return: a string indicating the attack and the damage they did to the enemy with their attack
  */
  public String magicMissile(Entity e)
  {
    int randDamage = (int)(Math.random() * 7) + 1;
    e.takeDamage(randDamage);
    return " launches a Magic Missile at " + e.getName() + " doing " + "" + randDamage + " damage";
  }

  /**
  Method of the hero's fireball attack which does random damage in a range and applies that damage to the entity.
  @param: object of Entity
  @return: a string indicating the attack and the damage they did to the enemy with their attack
  */
  public String fireball(Entity e)
  {
    int randDamage = (int)(Math.random() * 6) + 1;
    e.takeDamage(randDamage);
    return " launches a Fireball at " + e.getName() + " doing " + "" + randDamage + " damage";
  }

  /**
  Method of the hero's thunderclap attack which does random damage in a range and applies that damage to the entity.
  @param: object of Entity
  @return: a string indicating the attack and the damage they did to the enemy with their attack
  */
  public String thunderclap(Entity e)
  {
    int randDamage = (int)(Math.random() * 5) + 1;
    e.takeDamage(randDamage);
    return " slaps the " + e.getName() + " with thundering hands doing " + "" + randDamage + " damage";
  }
  
  /**
    Displays hero's name, hp, level, and map.
    @return: hero's name, hp, level, and map as a string.
  */
  @Override
  public String toString()
  {   
    return super.toString() + "\nLevel: " + level + '\n' + "Gold: " + gold + "\n" + "Key: " + key + "\n\n" + Map.getInstance().mapToString(position);
  }

  /**
    Method that moves the hero up 1 on the map. When the hero moves on the specified area,
    a character would be shown, Point() would get the character
    at that location, and then the character is removed.
    @return: character of the map that the hero moved to
  */
  public char goNorth()
  {
    char charLocation;
    int yPos = position.y - 1;
    position.setY(yPos);
    charLocation = Map.getInstance().getCharAtLoc(position);
    if (charLocation == 'x')
    {
      position.setY(yPos + 1);
      return 'x';
    }
    return charLocation;
  }

  /**
    Method that moves the hero down 1 on the map. When the hero moves on the specified area,
    a character would be shown, Point() would get the character
    at that location, and then the character is removed.
    @return: character of the map that the hero moved to
  */
  public char goSouth()
  {
    char charLocation;
    int yPos = position.y + 1;
    position.setY(yPos);
    charLocation = Map.getInstance().getCharAtLoc(position);
    if (charLocation == 'x')
    {
      position.setY(yPos - 1);
      return 'x';
    }
    return charLocation;
  }

  /**
    Method that moves the hero to the right 1 on the map. When the hero moves on the specified area,
    a character would be shown, Point() would get the character
    at that location, and then the character is removed.
    @return: character of the map that the hero moved to
  */
  public char goEast()
  {
    char charLocation;
    int xPos = position.x + 1;
    position.setX(xPos);
    charLocation = Map.getInstance().getCharAtLoc(position);
    if (charLocation == 'x')
    {
      position.setX(xPos - 1);
      return 'x';
    }
    return charLocation;
  }

  /**
    Method that moves the hero to the left 1 on the map. When the hero moves on the specified area,
    a character would be shown, Point() would get the character
    at that location, and then the character is removed.
    @return: character of the map that the hero moved to
  */
  public char goWest()
  {
    char charLocation;
    int xPos = position.x - 1;
    position.setX(xPos);
    charLocation = Map.getInstance().getCharAtLoc(position);
    if (charLocation == 'x')
    {
      position.setX(xPos + 1);
      return 'x';
    }
    return charLocation;
  }

  public Point getLoc()
  {
    return position;
  }

  /**
   * Method that returns the amount of gold in the hero's inventory.
   * @return amount of gold that the hero has.
   */
  public int getGold()
  {
    return gold;
  }

  /**
   * Method that adds the amount of gold passed into the hero's inventory.
   * @param g: value of gold.
   */
  public void collectGold(int g)
  {
    gold += g;
  }

  /**
   * Method that the hero spends.
   * @param g: value of gold.
   */
  public void spendGold(int g)
  {
    gold -= g;
  }

  /**
   * Method that checks to see if the hero has a key. 
   * @return: returns true if the hero has a key. Returns false if the hero does not.
   */
  public boolean hasKey()
  {
    if (key > 0)
    {
      return true;
    }
    return false;
  }

/**
 * Method that adds the key into the hero's inventory.
 */
  public void pickUpKey()
  {
    key++;
  }

  /**
   * Method that takes a key away from the hero's inventory.
   * @return: returns true if the hero has a key after it's taken away. Returns false if the hero does not have a key.
   */
  public boolean useKey()
  {
    key--;

    if (key > 0)
    {
      return true;
    }

    else if (key < 0)
    {
      key = 0;
    }
    return false;
  }
}