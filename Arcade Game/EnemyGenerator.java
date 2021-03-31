//Ethan Sandoval
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
import java.util.Random;
public class EnemyGenerator{

  private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();

  /**
  Constructor that reads a text file and saves a enemy's name and hp into two seperate arrayLists from the text file. Then uses the name and hp to create enemies to put in an arrayList.
  */
  public EnemyGenerator(){
    ArrayList<String> enemyName = new ArrayList<String>();
    ArrayList<Integer> enemyHp = new ArrayList<Integer>();
    try{
      Scanner read = new Scanner(new File("Enemies.txt"));
      while(read.hasNext()){
        String line = read.nextLine();
        String [] name = line.split(",");
        enemyHp.add(Integer.parseInt(name[1]));
        enemyName.add(name[0]);
      }
      read.close();
    }
    catch(FileNotFoundException e){
      System.out.println("File not found");
    }
    for (int i = 0; i < enemyName.size(); i++){
      enemyList.add(new Enemy(enemyName.get(i),enemyHp.get(i)));
    }
  }

  /**
  Method that randomly chooses an enemy from the arrayList, then randomly chooses whether that enemy is magical or not. Then makes a copy of the enemy from the arrayList to return.
  @return anotherEnemy, returns a copy of an enemy from the enemy arrayList
  @param magicEnemy, returns a copy of an enemy from the enemy arrayList with Magical in the name to signify that it is a magical enemy
  */
  public Enemy generateEnemy(){
    int randEnemy= (int)(Math.random() * enemyList.size());
    int randType = (int)(Math.random() * 2) + 1;
    Enemy newEnemy = enemyList.get(randEnemy);
    Enemy anotherEnemy = new Enemy(newEnemy.getName(), newEnemy.getMaxHP());
    if (randType == 1){
      return anotherEnemy;
    }
    else {
      Enemy tempEnemy = enemyList.get(randEnemy);
      int hp = tempEnemy.getMaxHP();
      String name = "Magical " + tempEnemy.getName();
      MagicalEnemy magicEnemy = new MagicalEnemy(name, hp);
      return magicEnemy;
    }
  }
}