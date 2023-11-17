import java.util.Random;
/** Charmander pokemon
  * Inherit  pokemon class
  * Implement fire interface
  * Samnang Lath
  * @version 1.0
  * @since 2021-23-10
  */
public class Grass extends Pokemon{
  


public Grass(String n, int h, int m){
    super(n, h, m);
  }

  /*
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
      t = "1. Vine Whip \n2. Razor Leaf \n3. Solar Beam";
    }
    return t;
  }
  /*
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
  public String getAttackString(int atkType, int move){
    if(atkType == 1){
      return super.getAttackString(atkType, move);
    }else{
      if (move == 1){
        return "Vine Whip";
      }else if (move == 2){
        return "Razor leaf";
      }else if (move == 2){
        return "Solar Beam";
      }
    }
    return "";
  }
  /*
* This method return the attack multiplier from battle tabble  
*@params p is a pokemon type,atkType is the type of the attack
*@return the attack multiplier depending on the type of the pokemon p  
*/
@Override 
  public double getAttackMultiplier(Pokemon p, int atkType){
    return super.getAttackMultiplier(p, atkType);
  }


  public int getAttackDamage(int atkType, int move){
  if(atkType == 1){
    super.getAttackDamage(atkType,move);
  }else{
    Random rand = new Random();
  if(move == 1){
    return rand.nextInt(3)+1;
  }else if(move == 2){
    return rand.nextInt(4)+2;
  }else if(move == 3){
    return rand.nextInt(3);
  }
  }
  return 0;
}

  /** This method return a string includig the action that the attack perform with the amount of damage and its name
*@params p is a pokemon type , atkType is the type of the attack, it can be basic or special and move chosen from the menu
*@return the partial string depending on the move chosen  
*/
@Override 
  public String attack(Pokemon p, int atkType, int move){
    if(atkType == 1){
      return super.attack(p, atkType, move);
    }else{
      return p.getName() +" receives " + getAttackString(atkType, move) + " by " + getName() + " and takes " + getAttackDamage(atkType, move) + " damages.";
    }
  }

  

  
  

  
}