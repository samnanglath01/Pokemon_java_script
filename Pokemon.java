import java.util.Random;

/** Model of the pokemon
  * @author Samnang Lath
  * @version 1.0
  **/
public abstract class Pokemon extends Entity
{
  //battltable with t
  protected static final double [][] battleTable = {{1,.5,2},{2,1,5},{.5,2,1}};
  /*
* constructor of Pokemon class, it gives a name and a maximum HP to a pokemon
*@params n is the name of the pokemon
@return the constructor method from the super class
*/
  public Pokemon(String n, int h, int m)
  {
    super(n, h, m);
  }

/*
* This method output the two different types of menu  
*@return the menu with the two different types in form of a string
*/

public String getAttackTypeMenu(){
  return "1. basic attack \n2. special attack";
}
/*
* This method return the number of items in the menu
*@return the number of choice in the menu 
*/
public int getNumAttackTypeMenuItems(){
  return 2;
} 
/*
* This method output th ebasic menu 
*@params atkType is the type of the attack, it can be basic or special
*@return the menu with all of the basic attacks 
*/
public String getAttackMenu(int atkType){
  return "1. slams \n2. tackles \n3. punch";
}
/*
* This method return the number of items in the menu
*@params atkType is the type of the attack, it can be basic or special
*@return the number of choice in the menu 
*/
public int getNumAttackMenuItems(int atkType){
  return 3;
}
/*
* This method return a partial string includig the action that the attack perform 
*@params atkType is the type of the attack, it can be basic or special and move chosen from the menu
*@return the partial string depending on the move chosen  
*/
public String getAttackString(int atkType, int move){
  if(move == 1){
    return "SLAMMED";
  }else if(move == 2){
    return "TACKLED";
  }else if(move == 3){
    return "PUNCHED";
  }
  return "";
}
/*
* This method return a partial string includig the action that the attack perform 
*@params atkType is the type of the attack, it can be basic or special and move chosen from the menu
*@return the partial string depending on the move chosen  
*/
public int getAttackDamage(int atkType, int move){
  Random rand = new Random();
  if(move == 1){
    return rand.nextInt(5);
  }else if(move == 2){
    return rand.nextInt(3)+2;
  }else{
    return rand.nextInt(4)+1;
  }
}
/*
* This method return the attack multiplier from battle tabble  
*@params p is a pokemon type,atkType is the type of the attack
*@return the attack multiplier depending on the type of the pokemon p  
*/
public double getAttackMultiplier(Pokemon p, int atkType){
  if(atkType == 2)
  {
      if(this.getType() == 0){
        switch(p.getType()){
          case 0:
          return battleTable[0][0];
          case 1:
          return battleTable[0][1];
          default:
          return battleTable[0][2];
        }
      }else if(this.getType() == 1){
        switch(p.getType()){
          case 0:
          return battleTable[1][0];
          
          case 1:
          return battleTable[1][1];
          
          default:
          return battleTable[1][2];
          
        }
      }else if(this.getType() == 2){
        switch(p.getType()){
          case 0:
          return battleTable[2][0];
         
          case 1:
          return battleTable[2][1];
         
          default:
          return battleTable[2][2];
    
        }
      }
    }
  return 1;
}
/*
*@return the attack bonus that will either increase or decrease the amount of damage that a pokemon gives 
*/
public int getAttackBonus(){
  return 0;
}
/** This method return a string includig the action that the attack perform with the amount of damage and its name
*@params p is a pokemon type , atkType is the type of the attack, it can be basic or special and move chosen from the menu
*@return the partial string depending on the move chosen  
*/
public String attack(Pokemon p, int atkType, int move){
  int damage =(int)(getAttackDamage(atkType, move)*getAttackMultiplier(p, atkType));
  if(damage+getAttackBonus()<0)
  {
    damage = damage;
  }
  else
  {
    damage += getAttackBonus();
  }
  p.takeDamage(damage);
  return p.getName() +" is " + getAttackString(atkType, move) + " by " + getName() + " and takes " + damage + " damages.";
}

/*
* This method return the type of the pokemon whether it is fire, water or grass 
*@return the type of the pokemon in form of a integer
*/
  int getType(){
    int Type = 3;
    if(this instanceof Fire){
      Type = 0;
    }else if(this instanceof Water){
      Type = 1;
    }else if(this instanceof Grass){
      Type = 2;
    }
    return Type;
    
  }
  /*  
* Override the toString function 
*@return String containing the informations of this entity
*/
  @Override 
  public String toString(){
    return super.toString();
  }
}