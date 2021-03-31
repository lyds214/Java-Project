//Lydia Yang and Ethan Sandoval
import java.util.Random;
import java.util.Scanner;

public class Hero extends Entity implements Magical
{
  private Map map = new Map();
  private Point position;
  private int level = 1;

  /**
  Constructor that creates a hero using the Entity's contructor then creates a map and puts the hero on the map
  @param n, name of the hero
  */
  public Hero(String n)
  {
    super(n, 25);
    map.loadMap(level);
    position = map.findStart();
    map.reveal(position);
  }

  /**
    When hero completes a map, their level increases by one and a new map loads.
    This will also find the 's' in the map and reveals the map on screen.
  */
  public void levelUp()
  {
    level++;
    map = new Map();
    map.loadMap(level % 3);
    position = map.findStart();
    map.reveal(position);
  }

/**
  Hero's physical attack value is randomly chosen and returns the random value as a string.
  The Entity object would receive that same amount of damage. This applies to attack(), 
  magicMissle(), fireball(), and thunderClap().
  @param: object of Entity
  @return: a string indicating the attack and the damage they did to the enemy with their attack
*/
  
  @Override
  public String attack(Entity e){
    int randDamage = (int)(Math.random() * 4) + 1;
    e.takeDamage(randDamage);
    return " slashes " + e.getName() + " with his sword doing " + "" + randDamage + " damage";
  }
  
  public String magicMissile(Entity e)
  {
    int randDamage = (int)(Math.random() * 7) + 1;
    e.takeDamage(randDamage);
    return " launches a Magic Missile at " + e.getName() + " doing " + "" + randDamage + " damage";
  }

  public String fireball(Entity e)
  {
    int randDamage = (int)(Math.random() * 6) + 1;
    e.takeDamage(randDamage);
    return " launches a Fireball at " + e.getName() + " doing " + "" + randDamage + " damage";
  }

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
    return super.toString() + "\nLevel: " + level + '\n' + map.mapToString(position);
  }

  /**
    When hero chooses which direction to go, goNorth(), goSouth(),
    goEast(), and goWest() takes the hero to their respective 
    area on the map. When the hero is on the specified area,
    a character would be shown, Point() would get the character
    at that location, and then the character is removed.
    @return: character location
  */
  public char goNorth()
  {
    char charLocation;
    int yPos = position.y - 1;
    position.setY(yPos);
    charLocation = map.getCharAtLoc(position);
    if (charLocation == 'x')
    {
      position.setY(yPos + 1);
      return 'x';
    }
    else{
      map.reveal(position); 
      map.removeCharAtLoc(position);    
    }
    return charLocation;
  }

  public char goSouth()
  {
    char charLocation;
    int yPos = position.y + 1;
    position.setY(yPos);
    charLocation = map.getCharAtLoc(position);
    if (charLocation == 'x')
    {
      position.setY(yPos - 1);
      return 'x';
    }
    else{
      map.reveal(position); 
      map.removeCharAtLoc(position);    
    }
    return charLocation;
  }

  public char goEast()
  {
    char charLocation;
    int xPos = position.x + 1;
    position.setX(xPos);
    charLocation = map.getCharAtLoc(position);
    if (charLocation == 'x')
    {
      position.setX(xPos - 1);
      return 'x';
    }
    else{
      map.reveal(position); 
      map.removeCharAtLoc(position);    
    }
    return charLocation;
  }

  public char goWest()
  {
    char charLocation;
    int xPos = position.x - 1;
    position.setX(xPos);
    charLocation = map.getCharAtLoc(position);
    if (charLocation == 'x')
    {
      position.setX(xPos + 1);
      return 'x';
    }
    else{
      map.reveal(position); 
      map.removeCharAtLoc(position);    
    }
    return charLocation;
  }
}