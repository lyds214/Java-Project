public class Warlock extends EnemyDecorator implements Magical
{
    public Warlock(Enemy e)
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

        if (randDamage == 1)
        {
            return magicMissile(e);
        }
        
        else if (randDamage == 2)
        {
            return fireball(e);
        }

        else
        {
            return thunderclap(e);
        }
    }

    /**
     * Get's Warlock's name.
     * @return: Enemy's name that's attached to "Warlock".
     */
    @Override 
    public String getName()
    { 
        return super.getName() + " Warlock";
    }

    /**
     * Get's Warlock's HP.
     * @return: Enemy's current HP that's added by 1.
     */
    @Override
    public int getHP()
    {
      return super.getHP() + 1; 
    }

    /**
     * Get's Warlock's max HP.
     * @return: Enemy's curreny max HP that's added by 1.
     */
    @Override
    public int getMaxHP() 
    {
      return super.getMaxHP() + 1;
    }

    /**
     Method of the magical enemy's magicMissile attack which does random damage in a range and applies that damage to the entity passed in.
    @param: object of Entity
    @return: a string indicating the attack and the damage they did to the entity with their attack
    */
    @Override
    public String magicMissile(Entity e)
    {
        int randDamage = (int)(Math.random() * 2);
        e.takeDamage(randDamage);
        return " launches a Magic Missile at " + e.getName() + " doing " + "" + randDamage + " damage";
    }

    /**
     Method of the magical enemy's fireball attack which does random damage in a range and applies that damage to the entity passed in.
    @param: object of Entity
    @return: a string indicating the attack and the damage they did to the entity with their attack
    */
    @Override
    public String fireball(Entity e)
    {
        int randDamage = (int)(Math.random() * 3);
        e.takeDamage(randDamage);
        return " launches a Fireball at " + e.getName() + " doing " + "" + randDamage + " damage";
    }

    /**
     Method of the magical enemy's thunderclap attack which does random damage in a range and applies that damage to the entity passed in.
    @param: object of Entity
    @return: a string indicating the attack and the damage they did to the entity with their attack
    */
    @Override
    public String thunderclap(Entity e)
    {
        int randDamage = (int)(Math.random() * 3) + 1;
        e.takeDamage(randDamage);
        return " slaps the " + e.getName() + " with thundering hands doing " + "" + randDamage + " damage";
    }
  
}
