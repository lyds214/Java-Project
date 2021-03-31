//Lydia Yang
import java.util.Random;

public class MagicalEnemy extends Enemy implements Magical
{
  /**
    Constructor for Magical Enemy.
    @param n: name of a magical enemy.
    @param mHP: maximum health that a magical enemy can hold.
  */
  public MagicalEnemy(String n, int mHP)
  {
    super(n, mHP);
  }

  /**
    A random generator chooses a random number from 1 to 3.
    If it chooses 1, magicMissle() is called. If it chooses
    2, fireball() is called. If it chooses 3, thunderclap() 
    is called.
    @param e: object of Entity
  */
  @Override
  public String attack(Entity e)
  {
    int randomSpell = (int)(Math.random() * 3) + 1;

    if (randomSpell == 1)
    {
      return magicMissile(e);  
    }

    else if (randomSpell == 2)
    {
      return fireball(e);
    }

    else
    {
      return thunderclap(e);
    }
  }

  /**
    A random number is generated so that the magical spell can
    deal damage to the hero at that value. This applies to 
    magicMissile(), fireball(), and thunderclap().
    @param: object of Entity 
    @return: enemy's name and amount of damage the enemy dealt with magical spells.
  */
  public String magicMissile(Entity e)
  {
    int randDamage = (int)(Math.random() * 9) + 1;
    e.takeDamage(randDamage);
    return " launches a Magic Missile at " + e.getName() + " doing " + "" + randDamage + " damage";
  }

  public String fireball(Entity e)
  {
    int randDamage = (int)(Math.random() * 8) + 1;
    e.takeDamage(randDamage);
    return" launches a Fireball at " + e.getName() + " doing " + "" + randDamage + " damage";
  }

  public String thunderclap(Entity e)
  {
    int randDamage = (int)(Math.random() * 7) + 1;
    e.takeDamage(randDamage);
    return " slaps the " + e.getName() + " with thundering hands doing " + "" +randDamage + " damage";
  }
}