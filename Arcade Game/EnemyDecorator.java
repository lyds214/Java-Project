public abstract class EnemyDecorator extends Enemy 
{
    private Enemy enemy;

    /**EnemyDecorator constructor. Gets the enemy's name and max HP.**/
    public EnemyDecorator(Enemy e)
    {
        super(e.getName(), e.getMaxHP());
        enemy = e;
    }

    /**
     * EnemyDecorator uses Enemy class's attack() to attack Hero.
     * @param e: Entity object where Enemy attacks Hero.
     * @return: value of random damage.
     */
    @Override
    public String attack(Entity e)
    {
        return super.attack(e);
    }
}
