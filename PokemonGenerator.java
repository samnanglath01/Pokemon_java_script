import java.io.File;
import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
/** Model of the pokemon
  * @author Samnang lath
  * @version 1.0
  **/
public class PokemonGenerator{
   private HashMap<String, String> pokemonMap;
   private static PokemonGenerator instance = null;
   /** PokemonGenerator Constructor
   * read the file from PokemonList and store it in hasmap as String
   * Constructor is now private to convert generator into singleton
   */
private PokemonGenerator (){
    try 
      {
        pokemonMap = new HashMap<String, String>();
        Scanner read = new Scanner(new File("PokemonList.txt"));
         do {
           String line = read.nextLine();
           String tokens []= line.split(",");
           pokemonMap.put(tokens[0], tokens[1]);
         }while(read.hasNextLine());
     read.close();

   } catch (FileNotFoundException fnf) {
      System.out.println("File was not found");
   }
} 
/** Get Instance function
    *Create an instance of the PokemonGenerator if it doesn't exist
    *@return the created instance
    */
  public static PokemonGenerator getInstance( ) {
      if( instance == null ) {
         instance = new PokemonGenerator( );
      }
      return instance;
   }
/** 
*generate pokemon  randomly pick a pokémon from 
 the HashMap, and then construct a pokémon of the corresponding
elemental base type. 
@return pokemon from the hashmap base on elements */
public Pokemon generateRandomPokemon (int level){
      Pokemon p;
      Object[] keys= pokemonMap.keySet().toArray();
      Object key= keys[new Random().nextInt(keys.length)];
      String keyconvert= key.toString();
      String element = pokemonMap.get(key);
      if (element.equalsIgnoreCase("Fire"))
      {
        p  = new Fire(keyconvert, 25, 25 );
      }
      else if (element.equalsIgnoreCase("Water")){
        p = new Water ( keyconvert, 22, 22);
         
      }
      else{
         p = new Grass (keyconvert, 20,20);
        
       }
       for(int i = 1; i < level; i++) //decorate pokemon with attack up and hp up for each level
       {
         p = new AttackUp(new HpUp(p));
       } 
       return p; 
      
} 
/** 
*passes in a string with the name of a pokémon and 
 constructs an object of the correct corresponding type.
 @return pokemon base on the name and element.*/
public Pokemon getPokemon (String name){
       String element = pokemonMap.get(name);
       Pokemon p;
      if (element.equalsIgnoreCase("Fire"))
      {
        p  = new Fire(name, 25, 25 );    
      }
      else if (element.equalsIgnoreCase("Water"))
      {
        p = new Water ( name, 22, 22);
         
      }
      else
      {
        p = new Grass (name, 20,20);
      }
  return p;
}
/** randomly chooses from 1 or 2 a buff to apply 
    to the pokémon*/
public Pokemon addRandomBuff(Pokemon p){
    int buff = (int)(Math.random()* 2) + 1;
    if(buff == 1)
    {
      p = new AttackUp(p);  
    }
    if(buff == 2)
    {
      p = new HpUp(p);
    } 
    return p; 
  }
/** randomly chooses from 1 or 2 a debuff to apply 
    to the pokémon*/
public Pokemon addRandomDebuff (Pokemon p){
   int debuff = (int)(Math.random()* 2) + 1;
    if(debuff == 1)
    {
      p = new AttackDown(p);  
    }
    if(debuff == 2)
    {
      p = new HpDown(p);
    } 
    return p; 
}
}