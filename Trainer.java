/** Model of the pokemon trainer
  * Inherit  Entity class
  * @author Samnang Lath
  * @version 1.0
  */
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
public class Trainer extends Entity
{
  private int money;
  private int potions;
  private int pokeballs;
  private Point loc;
  ArrayList <Pokemon> pokemon = new ArrayList<Pokemon>();
  /** Constructor for trainer 
    *@param n is trainer's name
    *@param p is the trainer's startingpokemon
    *@param m is the trainer's map
   */
  public Trainer(String n, Pokemon p)
  {
    super(n, 100, 100);
    pokemon.add(p);
    loc = Map.getInstance().findStart();
    Map.getInstance().reveal(loc);
    money = 25;
    pokeballs = 5;
    potions = 1;
  }

  /** Get the amount of potions the trainer has
    * @return number of potion
    */
  public int getPotion(){
    return potions;
  }
  
  
  /** Get the amount of money the trainer has
    * @return the amount number of money
    */
  public int getMoney()
  {
    return money;
  }
   
   
  /** Check if the trainer can spend the amount of the money passed in
    *@params amt is the amount that is needed to be spent 
    *@return boolean value of whether the trainer can spend the amount
    */
  public boolean spendMoney(int amt)
  {
    if(money-amt>=0)
    {
      money -= amt;
      return true;
    }
    else
    {
      return false;
    }
  }
   
  /** Trainer receives money 
    * @param amt is the amount of money received
    */
  public void receiveMoney(int amt)
  {
    money += amt;
  }
  
  /** Check if the trainer has any potion left
    * @return boolean if 0
    */
  public boolean hasPotion()
  {
    if(potions>0)
    {
      return true;
    }
    else
    {
      return false;
    }
  }
  
  /** Trainer receive a potion
    * increment the amount of potion the trainer has
    */
  public void receivePotion()
  {
    potions++;
  }
   
   
  /** Use potion on the pokemon at the passed in index
    * @param pokeindex is the index of the pokemon in the ArrayList
    * check the condition with hasPotion
    * Cannot be used if the pokemon is full HP or fainted HP
    */
  public void usePotion(int pokeindex)
  {
    if(hasPotion())
    {
      if(pokemon.get(pokeindex).getHp() == 0)
      {
        System.out.println("You cannot use potion on a fainted pokemon.");
      }
      else if(pokemon.get(pokeindex).getHp() == pokemon.get(pokeindex).getMaxHp())
      {
        System.out.println(pokemon.get(pokeindex).getName() + " is already at full HP.");
      }
      else
      {
        getPokemon(pokeindex).heal();
        System.out.println("You used a potion on "+ pokemon.get(pokeindex).getName() + ".");
        System.out.println(pokemon.get(pokeindex).getName() + " is now heal to full.");
      }
    }
    else
    {
      System.out.println("You have ran out of potion.");
    }
  }


  /** Check if the trainer still has any pokeball left
    * @return true if so, false if not
    */
  public boolean hasPokeball()
  {
    if(pokeballs>0)
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  /** The trainer receive a pokeball
    * Increment the amount of pokeball the trainer has by 1
    */
  public void receivePokeball()
  {
    pokeballs++;
  }
  
  /** Catch the passed in pokemon
    * Throw a pokeball at the pokemon and catch it
    * Success chance depends on pokemonH)
    * @param p is the pokemon to be caught
    *return true if caught, false if pokemon break free
    */
  public boolean catchPokemon(Pokemon p)
  {
    
    if(hasPokeball())
    {
      pokeballs--;
      double hpDouble = (double)p.getHp();
      double maxHpDouble = (double)p.getMaxHp();
      double chance = 1-(hpDouble/maxHpDouble);
      Random rand = new Random();
      double range = rand.nextDouble();
      if(range<=chance)
      {
        pokemon.add(p);
        if(getNumPokemon() == 7)
        {
          System.out.println("You can only carry 6 pokemons at a time.");
          System.out.println("Choose a Pokemon to remove from your list.");
          System.out.println(getPokemonList());
          int choice = CheckInput.getInt();
          pokemon.remove(choice-1);
        }
        return true;
      }
      else
      {
        System.out.println(p.getName() + "is too strong.");
        System.out.println(p.getName() + "broke free from the pokeball.");
        return false;
      }
    }
    else{
      System.out.println("You don't have any pokeball left.");
      return false;
    }
      //System.out.println("You have too many pokemon, you can't add another one.");
  }
  /** Get the trainer current location
    * @return point of trainer location
    */
  public Point getLocation()
  {
    return loc;
  }
/** Move player to north 
  * Player cannot go out of the bound of the map
  * @return char at moved location
  */
  public char goNorth()
  {
    Point tempLoc = new Point(loc);
    tempLoc.translate(0, -1);
    if(tempLoc.getY()<0)
    {
      System.out.println("You cannot go that way.");
      return 'a';
    }
    else
    {
      loc.setLocation(tempLoc);
      Map.getInstance().reveal(loc);
      return Map.getInstance().getCharAtLoc(loc); 
    }
  }
/** Move player to south 
  * Player cannot go out of the bound of the map
  * @return char at moved location
  */
  public char goSouth()
  {
    Point tempLoc = new Point(loc);
    tempLoc.translate(0, 1);
    if(tempLoc.getY()>4)
    {
      System.out.println("You cannot go that way.");
      return 'a';
    }
    else
    {
      loc.setLocation(tempLoc);
      Map.getInstance().reveal(loc);
      return Map.getInstance().getCharAtLoc(loc);
    }
  }
/** Move player to East
  * Player cannot go out of the bound of the map
  * @return char at moved location
  */
  public char goEast()
  {
    Point tempLoc = new Point(loc);
    tempLoc.translate(+1, 0);
    if(tempLoc.getX()>4)
    {
      System.out.println("You cannot go that way.");
      return 'a';
    }
    else
    {
      loc.setLocation(tempLoc);
      Map.getInstance().reveal(loc);
      return Map.getInstance().getCharAtLoc(loc);
    }
  }
/** Move player to west
  * Player cannot go out of the bound of the map
  * @return char at moved location
  */
  public char goWest()
  {
    Point tempLoc = new Point(loc);
    tempLoc.translate(-1, 0);
    if(tempLoc.getX()<0)
    {
      System.out.println("You cannot go that way.");
      return 'a';
    }
    else
    {
      loc.setLocation(tempLoc);
      Map.getInstance().reveal(loc);
      return Map.getInstance().getCharAtLoc(loc);
    }
  }


 /** Give the amount of pokemon the trainer has
  * @return array size of pokemon
  */
  public int getNumPokemon()
  {
    return pokemon.size();
  }
 
 /** Heal all the pokemon the trainer has
  *heal all pokemon for hospital
  */
  public void healAllPokemon()
  {
    for(Pokemon p : pokemon)
    {
      p.heal();
    }
  }
  
  /** Access trainer pokemon
    * @param index is the index of the pokemon in the ArrayList
    * @return pokemon at the index
    */
  public Pokemon getPokemon(int index)
  {
    return pokemon.get(index);
  }
  
  public Pokemon removePokemon(int index)
  {

    return pokemon.remove(index);
  }
public void buffAllPokemon(){
  for(int i = 0; i < pokemon.size(); i++){
    pokemon.set(i, PokemonGenerator.getInstance().addRandomBuff(pokemon.get(i)));
  }
}


public void debuffPokemon(int index){
  pokemon.set(index,PokemonGenerator.getInstance().addRandomDebuff(pokemon.get(index)));
}
  
  /**Display all the pokemon trainer has
    * @return string of pokemon name
    */
  public String getPokemonList()
  {
    int i = 1;
    String pList="--------------------\n";
    for(Pokemon p : pokemon)
    {
      pList+=i+"."+p+"\n";
      i++;
    }
    pList+="--------------------\n";
    return pList;
  }
  /** String representation of trainer class;
    * @return string of Trainer info: name, hp, money, potion...
    */
  @Override
  public String toString()
  {
    String S = getName()+ " HP: "+ getHp()+"/"+getMaxHp()+ "\n";
    S += "Money: "+ money + "\n";
    S += "Potions:"+ potions + " \n";
    S += "Pokeballs: "+ pokeballs + "\n";
    S += "Pokemon\n";
    S += getPokemonList();
    S += "\n";
    S += Map.getInstance().mapToString(loc);
    return S;
  }
}