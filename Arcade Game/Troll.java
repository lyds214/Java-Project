public class Troll extends Enemy
{
    public Troll()
    {
        super("Troll", 5);
    }

   /**
    A random value is generated for the enemy to attack hero.
    @param: object of Entity
    @return: value of random damage.
  */
  @Override
  public String attack(Entity e) 
  {
    int randDamage = (int)(Math.random() * 5);
    e.takeDamage(randDamage);
    return " attacks " + e.getName() + " doing " + randDamage + " damage";
  }
}
