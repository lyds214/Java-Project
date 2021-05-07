public class Goblin extends Enemy 
{
    public Goblin()
    {
        super("Goblin", 2);
    }

   /**
    A random value is generated for the enemy to attack hero.
    @param: object of Entity
    @return: value of random damage.
  */
  @Override
  public String attack(Entity e) 
  {
    int randDamage = (int)(Math.random() * 2) + 1;
    e.takeDamage(randDamage);
    return " attacks " + e.getName() + " doing " + randDamage + " damage";
  }
}
