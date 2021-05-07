public class Warrior extends EnemyDecorator
{
    public Warrior(Enemy e)
    {
        super(e);
    }

    /**
        A random value is generated for the enemy to attack hero.
        @param: object of Entity
        @return: value of random damage.
    */
    @Override
    public String attack(Entity e) 
    {
        int randDamage = (int)(Math.random() * 3) + 1;
        e.takeDamage(randDamage);
        return " attacks " + e.getName() + " doing " + randDamage + " damage";
    }

    /**
     * Get's Warrior's name.
     * @return: Enemy's name that's attached to "Warrior"
     */
    @Override 
    public String getName()
    { 
        return super.getName() + " Warrior";
    }

    /**
     * Get's Warrior's current HP.
     * @return: Enemy's current HP that's added by 2.
     */
    @Override
    public int getHP()
    {   
      return super.getHP() + 2;    
    }

    /**
     * Gets Warrior's max HP.
     * @return: Enemy's max HP that's added by 2.
     */
    @Override
    public int getMaxHP() {

        return super.getMaxHP() + 2;
    }


  
}
