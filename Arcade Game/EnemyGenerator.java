import java.io.*;

public class EnemyGenerator
{

  /**
   * Generates a random enemy from the base classes: Froglok, Goblin, Troll, and Orc. As Hero's level increases, 
   * each of the enemy's HP and attack frequency increases.
   * @param level: Hero's level
   * @return: Enemy object.
   */
  public static Enemy generateEnemy(int level)
  {
    Enemy enemy;
    int randEnemy = (int)(Math.random() * 4) + 1;
    int enemyType = (int)(Math.random() * 2) + 1;

    if (randEnemy == 1)
    {
      enemy = new Froglok();  
    }

    else if (randEnemy == 2)
    {
      enemy = new Goblin();
    }

    else if (randEnemy == 3)
    {
      enemy = new Troll();
    }

    else
    {
      enemy = new Orc();
    }

    for (int i = 1; i< level; i++)
    {
      if (enemyType == 1)
      {
        enemy = new Warlock(enemy);
      }

      else
      {
        enemy = new Warrior(enemy);
      }
      
    }
    
    return enemy;
  }   
}