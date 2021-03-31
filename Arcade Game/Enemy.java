//Lydia Yang

public class Enemy extends Entity
{
  /**
  Contructor that creates an enemy using the Entity's contructor
  @param n: name of the Enemy
  @param mHP: maximum HP that enemy holds.
  */
  public Enemy(String n, int mHP)
  { 
    super(n, mHP);
  }

  /**
    A random value is generated for the enemy to attack hero.
    @param: object of Entity
    @return: value of random damage.
  */
  @Override
  public String attack(Entity e) 
  {
    int randDamage = (int)(Math.random() * 6) + 1;
    e.takeDamage(randDamage);
    return " attacks " + e.getName() + " doing " + randDamage + " damage";
  }
}