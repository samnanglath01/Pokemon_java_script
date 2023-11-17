import java.util.Random;
public class Water extends Pokemon {
public Water (String n, int h, int m){
  super(n,h,m);
} /*
* This method output th ebasic menu 
*@params atkType is the type of the attack, it can be basic or special
*@return the menu with all of the basic attacks 
*/
@Override
    public String getAttackMenu(int atkType){
      String a = "";
      if (atkType==1){
        a=super.getAttackMenu(atkType);
      }
      else if (atkType==2){
        a = "1. WaterGun \n2. Bubblebeam \n3. Waterfall";
      }
         return a;
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
    public String getAttackString (int atkType, int move){
       if(atkType == 1){
        return super.getAttackString(atkType, move);}
       else{
        if (move==1){
          return "Watergun";
        }
        else if (move==2){
          return "bubbleBeam";
        }
        else if(move==3){
          return "WaterFall";
        
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
    public int getAttackDamage (int atkType, int move){
      Random generator = new Random();
      int damage=0;
        if (atkType==1){
          return super.getAttackDamage(atkType, move);
        }
        else if (atkType==2){
         if (move ==1){
        damage=generator.nextInt(3)+3;
        
        }
       else if (move ==2){
         damage=generator.nextInt(2)+2;
        
       }
       else if (move ==3){
          damage=generator.nextInt(2)+3;
        
       }
      }
      return damage;
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
}