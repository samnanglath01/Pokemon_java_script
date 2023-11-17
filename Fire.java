/** Fire interface represent fire type pokemon
  * Samnang Lath
  * @version 1.1
  * @since 2021-30-11
  */

import java.util.Random;
public class Fire extends Pokemon{
  
  public Fire(String n, int h, int m){
    super(n, h, m);
  }
  /**
  * This method output th ebasic menu 
*@params atkType is the type of the attack, it can be basic or special
*@return the menu with all of the basic attacks 
*/
@Override 
  public String getAttackMenu(int atkType){
    String t = "";
    if(atkType == 1){
      t = super.getAttackMenu(atkType);
    }else if(atkType == 2){
      t = "1. Ember \n2. Fire Blast \n3. Fire Punch";
    }
    return t;
  }
  /**
  * This method return the number of items in the menu
*@params atkType is the type of the attack, it can be basic or special
*@return the number of choice in the menu 
*/
@Override 
  public int getNumAttackMenuItems(int atkType){
    return 3;
    }
      /*
* This method return a partial string includig the action that the attack perform 
*@params atkType is the type of the attack, it can be basic or special and move chosen from the menu
*@return the partial string depending on the move chosen  
*/
@Override 
  public String getAttackString(int atkType, int move)
  {
    if(atkType == 1)
    {
      return super.getAttackString(atkType, move);
    }
    else
    {
      if (move == 1)
      {
        return "Ember";
      }
      else if (move == 2)
      {
        return "Fire Blast";
      }
      else if (move == 2)
      {
        return "Fire Punch";
      }
    }
    return "";
  }
  public int getAttackDamage (int atkType, int move){
      Random rand = new Random();
      int damage = 0;
        if (atkType==1){
          return super.getAttackDamage(atkType, move);
        }
        else if (atkType==2){
          if (move ==1)
          {
            damage = rand.nextInt(4);
          }
          else if (move ==2)
          {
            damage= rand.nextInt(4)+1;
          }
          else
          {
            damage = rand.nextInt(3)+1;
          }
      }
      return damage;
    }
    /**
    * This method return the attack multiplier from battle tabble  
*@params p is a pokemon type,atkType is the type of the attack
*@return the attack multiplier depending on the type of the pokemon p  
*/
@Override
    public double getAttackMultiplier(Pokemon p, int atkType){
          return super.getAttackMultiplier(p, atkType);
    }
}