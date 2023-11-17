/** Represent the entity in the game
  * @author Samnang Lath
  * @version 1.0
  * @since 2021-21-10
  */

public abstract class Entity{ 
    private String name;
    private int hp;
    private int maxHp;

    public Entity( String n, int h, int m){
      name=n;
      hp=h;
      maxHp=m;
    }
    /**
    @return number of hp
    */
    public int getHp(){
      return hp;
    }
    /** 
     @return number of maxHp
    */
    public int getMaxHp(){
        return maxHp;
    }
    /**
    damage update
    */
    public void takeDamage (int d){
      hp=hp-d;
      if (hp<d){
        hp=0;
        }
      } 
  
    /** heal pokemon instantly to maxHp;
    */
    public void heal(){
     hp=maxHp;
    }
    /** 
      *@return name of trainer
    */
    public String getName(){
        return name;
    }
   /**
    * @return name of trainer with hp
   */
    public String toString(){
        return getName()+" HP: "+hp+"/"+getMaxHp();
    }
}