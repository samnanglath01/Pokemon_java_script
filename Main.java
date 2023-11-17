/*
* Name:  Samnang lath
* Date: 10/25/2021
* Program description: This program is a simulation of a 2D pokemon game. we hop that you enter the game!
*/
import java.awt.Point;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Random;
public class Main{
    public static void main(String [] args)
    {
      String name;
      int pokenum;
      Scanner input = new Scanner (System.in);
      Map map= Map.getInstance();
      int mapNum = 1;
      map.loadMap(mapNum);
      boolean terminate = false;
      ArrayList <Point> undefWPokemonLoc = new ArrayList<Point>(); //points of undefeated pokemon
      ArrayList <Pokemon> undefWPokemon = new ArrayList<Pokemon>(); //undefeated wild pokemon
      PokemonGenerator pg = PokemonGenerator.getInstance();

      System.out.println("Prof. Oak: Hello there new trainer, what is your name?");
      name = CheckInput.getString();
      System.out.println("Great to meet you, "+ name+" Choose your first pokemon:\n1. Charmander\n2. Bulbasaur\n3. Squirtle");
      pokenum= CheckInput.getIntRange(1,3);
      Pokemon startPokemon;
      switch(pokenum){
        case 1:
          startPokemon = pg.getPokemon("Charmander");
          break;
        case 2:
          startPokemon = pg.getPokemon("Bulbasaur");
          break;
        default:
          startPokemon = pg.getPokemon("Squirtle");
          break;
      }
      Trainer player = new Trainer(name, startPokemon);
      char encounter;
      while(!terminate)
      {
        System.out.println(player);
        switch(mainMenu())
        {
          case 1:
            encounter = player.goNorth(); //return char
            break;
          case 2:
            encounter = player.goSouth();
            break;
          case 3:
            encounter = player.goEast();
            break;
          case 4:
            encounter = player.goWest();
            break;
          default:
            System.out.println("Game over!");
            encounter = 'a';
            terminate = true;
        }
        if(encounter == 'w')
        {
          Pokemon wildPokemon;
          
          if(undefWPokemonLoc.contains(player.getLocation()))
          {
            wildPokemon = undefWPokemon.get(undefWPokemonLoc.indexOf(player.getLocation())); 
          }
          else
          {
            Random rand = new Random();
            int wildLevel = rand.nextInt(3)+1;
            wildPokemon = pg.generateRandomPokemon(wildLevel); 
    
          }
          boolean flag = true;
          boolean caught = false;
          System.out.println("A wild "+ wildPokemon.getName() + " has appeared,");
          do{
            
            
            System.out.println(wildPokemon);
            System.out.println("What would you like to do?");
            System.out.println("1. Fight");
            System.out.println("2. Use Potion");
            System.out.println("3. Use Pokeball");
            System.out.println("4. Run Away.");
            int wildPEncounterAction = CheckInput.getIntRange(1,4);
            switch(wildPEncounterAction)
            {
              case 1:
                wildPokemon = trainerAttack(player, wildPokemon);
                System.out.println();
                break;
              case 2:
                System.out.println("Which pokemon do you want to use your potion on?");
                System.out.println(player.getPokemonList());
                int potionChoice = CheckInput.getIntRange(1, player.getNumPokemon())-1;
                player.usePotion(potionChoice);
                System.out.println();
                break;
              case 3:
                if(player.hasPokeball())
                {
                  System.out.println("You throw a pokeball.");
                  System.out.println("Wk wk wk wk wk wk");
                }
                caught = player.catchPokemon(wildPokemon);
                if(caught)
                {
                  System.out.println("Congratulation you caught a wild " + wildPokemon.getName() + ".");
                  flag = false;
                }
                break;
              default:
                System.out.println("You ran away.");
                if(undefWPokemon.contains(wildPokemon))
                {
                }
                else
                {
                  undefWPokemon.add(wildPokemon);
                  Point tempLoc = new Point(player.getLocation());
                  undefWPokemonLoc.add(tempLoc);
                }
                //Handle the random direction run
                Random rand = new Random();
                ArrayList <Integer> randomDirection = new ArrayList <Integer>();
                //create ArrayList and add int 0 to 4 for each direction
                randomDirection.add(0);
                randomDirection.add(1);
                randomDirection.add(2); 
                randomDirection.add(3);
                
                //if there's barrier(hit the bound of the array in any direction), remove the int from the ArrayList
                if(player.getLocation().getY() == 0)
                {
                  randomDirection.remove(Integer.valueOf(0)); 
                }
                if(player.getLocation().getY() == 4)
                {
                  randomDirection.remove(Integer.valueOf(1));
                }
                if(player.getLocation().getX() == 4)
                {
                  randomDirection.remove(Integer.valueOf(2)); 
                }
                if(player.getLocation().getX() == 0)
                {
                  randomDirection.remove(Integer.valueOf(3));
                }
                int index = rand.nextInt(randomDirection.size()); //random an index using the ArrayListSize as bound

                //base on the result go to the randomize direction
                //This is done to avoid the trainer.goDirection() function printing you cannot go to this direction over and over in case of //the same random number is randomized
                switch(randomDirection.get(index)){ 
                  case 0:
                    player.goNorth();
                    break;
                  case 1:
                    player.goSouth();
                    break;
                  case 2:
                    player.goEast();
                    break;
                  default:
                    player.goWest();
                    break;
                }
                //End handling the random direction run
                flag = false;
                break;
            }
          }while(flag == true && wildPokemon.getHp() != 0);
          if(wildPokemon.getHp() == 0 || caught)
          {
            map.removeCharAtLoc(player.getLocation());
            undefWPokemonLoc.remove(player.getLocation());
            undefWPokemon.remove(wildPokemon);
          }
        }
        else if(encounter == 'n')
        {
          System.out.println("There is nothing here.");
        }
        else if(encounter == 'p')
        {
          int action = (int)((Math.random()* 10)+1);
          if (action == 1){
            
            System.out.println("You meet Professor Oak.");
            System.out.println("Prof. Oak: Hey " + player.getName() + ", here some money for you to get some stuff.");
            System.out.println("Prof. Oak gave you $50.");
            player.receiveMoney(50);
  
          }else if (action == 2){

            System.out.println("You meet Ashley.");
            System.out.println("Ashley: Hey nice person, here a pokeball for you.");
            player.receivePokeball();
            System.out.println("You've received pokeball from Ashley.");

          }else if (action == 3){
            System.out.println("You meet Team Rocket.");
            System.out.println("Team Rocket: Got your face!!!");
            System.out.println("Team Rocket slaps you in the face and ran away.");
            System.out.println("You took 3 damage from the slap.");
            player.takeDamage(3);
          }
          else if(action == 4)
          {
            System.out.println("You meet Brock, Pewter City Gym Leader.");
            System.out.println("Brock: Hey " + player.getName() + ", nice to see you.");
            System.out.println("Brock: Here's some money for you. Get your pokemon some potions.");
            player.receiveMoney(30);
            System.out.println("You received $30 from Brock.");
          }
          else if(action == 5)
          {
            System.out.println("You meet Misty.");
            System.out.println("Misty: Hey " + player.getName() + ", where's my bike?");
            System.out.println(player.getName()+ ": ...");
            System.out.println("Misty punches you for 2 damage");
            player.takeDamage(2);
            System.out.println(player.getName()+ ": ...");
            System.out.println("Misty walked away.");
          }
           else if(action == 6)
          {
            System.out.println("You encounter MewTwo");
            System.out.println("Mewtwo lifts you to the air and drop you.");
            player.takeDamage(5);
            System.out.println("You took 5 damage from the fall.");
            System.out.println("Mewtwo teleported away.");
            System.out.println(player.getName()+": (Amazed expression)");
          }
           else if(action == 7)
          {
            System.out.println("You encounter Team Rocket Meowth");
            System.out.println("Team Rocket Meowth scratches your face for 4 damage.");
            player.takeDamage(4);
            System.out.println("Team Rocket Meowth: ...in your face!");
            System.out.println("Team Rocket Meowth ran away.");
          }
           else if(action == 8)
          {
            System.out.println("You meet Prof. Burnet");
            System.out.println("Prof. Burnet: Hey "+ player.getName()+ ", I haven't seen you in a while." );
            System.out.println("Prof. Burnet: Where have you been?");
            System.out.println(player.getName() + ": ...");
            System.out.println("Prof. Burnet: Regardless, here's a pokeball for your wild adventure.");
            player.receivePokeball();
            System.out.println("You received a pokeball from Prof.Burnet");
          }
           else if(action == 9)
          {
            System.out.println("You meet Cilan, Striaton City Gym Leader");
            System.out.println("Cilan: Hey "+ player.getName() + ", haven't seen you for a while.");
            System.out.println(player.getName()+ ": ...");
            System.out.println("Cilan: Here's a pokeball for you.");
            System.out.println("Cilan: Let's battle when you catch some strong pokemons");
            player.receivePokeball();
            System.out.println("Cilan has given you a pokeball");
          }
           else if(action == 10)
          {
            System.out.println("You meet Blaine, Cinnabar Gym Leader");
            System.out.println("Blaine: Hey there young trainer, you look promising.");
            System.out.println("Blaine: I got some extra cash.");
            System.out.println("Blaine: Get yourself some pokeball from the Poke Store.");
            System.out.println("Blaine: It will come in handy in the wild.");
            player.receiveMoney(40);
            System.out.println(player.getName()+ ": ...");
            System.out.println("You received $40 from Blaine.");
          }

          map.removeCharAtLoc(player.getLocation());
          

        }
        else if(encounter == 'i')
        {
          Random rand = new Random();
          int pBallOrPotion = rand.nextInt(2);
          if(pBallOrPotion == 0)
          {
            System.out.println("You have found a potion.");
            player.receivePotion();
          }
          else
          {
            System.out.println("You have found a pokeball.");
            player.receivePokeball();
          }
          map.removeCharAtLoc(player.getLocation());
        }
        else if(encounter == 'f')
        { 
          Pokemon gymLeaderPokemon;
          if(mapNum == 1)
          {
            gymLeaderPokemon = pg.generateRandomPokemon(3);
          }
          else if(mapNum == 2)
          {
            gymLeaderPokemon = pg.generateRandomPokemon(4);
          }
          else
          {
            gymLeaderPokemon = pg.generateRandomPokemon(5);
          }
          boolean encounterAble = false; //if player has pokemon that are not fainted then it's true 
          int fainted = 0;
          for(int i = 0 ; i <player.getNumPokemon(); i ++)
          {
            if(player.getPokemon(i).getHp() == 0)
            {
              fainted++;
            }
          }
          if(fainted == player.getNumPokemon())
          {
            encounterAble = false;
            System.out.println("You don't have any available pokemon to challenge the gym leader.");
          }
          else
          {
            encounterAble = true;
            if(mapNum == 1)
            {  
              System.out.println("You encountered Brock,  the Gym Leader of Pewter City Gym");
              System.out.println("Brock: What's up " + player.getName() + "?");
              System.out.println("Brock: Let's see who's the best.");
              System.out.print("Brock throws out a ");
            }
            else if(mapNum == 2)
            {
              System.out.println("You encountered Misty,  the Gym Leader of Cerulean City Gym");
              System.out.println("Misty: What's up " + player.getName() + "?");
              System.out.println("Misty: You maybe a pokemon trainer but I'm the best!");
              System.out.print("Misty throws out a ");
            }
            else
            {
              System.out.println("You encountered Erika,  the Gym Leader of Celadon City Gym");
              System.out.println("Erika: Hey there " + player.getName() + "!");
              System.out.println("Erika: I'll show you who's the best pokemon trainer!");
              System.out.print("Erika throws out a ");
            }
            System.out.println(gymLeaderPokemon.getName());
          }
          while(encounterAble && gymLeaderPokemon.getHp()!=0)
          { 
              System.out.println(gymLeaderPokemon);
              System.out.println("What would you like to do?");
              System.out.println("1. Fight");
              System.out.println("2. Use Potion");
              int gymLeaderEncounterAction = CheckInput.getIntRange(1,2);
              switch(gymLeaderEncounterAction)
              {
                case 1:
                  gymLeaderPokemon = trainerAttack(player, gymLeaderPokemon);
                  System.out.println();
                  
                  break;
                default:
                  System.out.println("Which pokemon do you want to use your potion on?");
                  System.out.println(player.getPokemonList());
                  int potionChoice = CheckInput.getIntRange(1, player.getNumPokemon())-1;
                  player.usePotion(potionChoice);
                  System.out.println();
                  break;
              }
              fainted = 0;
              for(int i = 0 ; i <player.getNumPokemon(); i ++)
              {
                if(player.getPokemon(i).getHp() == 0)
                {
                  fainted++;
                }
              }
            if(fainted == player.getNumPokemon())
            {
              encounterAble = false;
              System.out.println("You don't have any available pokemon to challenge the gym leader.");
              System.out.println("You lost. Try again when you have enough pokemon to fight.");
            }
          }
          if(gymLeaderPokemon.getHp() == 0)
          {
            System.out.println("You've deafeated the gym leader.");
            if(mapNum == 1 || mapNum == 2)
            {
              mapNum++;
              map.loadMap(mapNum);
              undefWPokemon.clear();
              undefWPokemonLoc.clear();
            }
            else
            {
              mapNum = 1;
              map.loadMap(mapNum);
              undefWPokemon.clear();
              undefWPokemonLoc.clear();
            }
            player.buffAllPokemon();
          }
          else
          {
            Random rand = new Random();
            ArrayList <Integer> randomDirection = new ArrayList <Integer>();
              //create ArrayList and add int 0 to 4 for each direction
            randomDirection.add(0);
            randomDirection.add(1);
            randomDirection.add(2); 
            randomDirection.add(3);
              
            //if there's barrier(hit the bound of the array in any direction), remove the int from the ArrayList
            if(player.getLocation().getY() == 0)
            {
              randomDirection.remove(Integer.valueOf(0)); 
            }
            if(player.getLocation().getY() == 4)
            {
              randomDirection.remove(Integer.valueOf(1));
            }
            if(player.getLocation().getX() == 4)
            {
              randomDirection.remove(Integer.valueOf(2)); 
            }
            if(player.getLocation().getX() == 0)
            {
              randomDirection.remove(Integer.valueOf(3));
            }
            int index = rand.nextInt(randomDirection.size()); //random an index using the ArrayListSize as bound

              //base on the result go to the randomize direction
              //This is done to avoid the trainer.goDirection() function printing you cannot go to this direction over and over in case of //the same random number is randomized
            switch(randomDirection.get(index))
            { 
              case 0:
                player.goNorth();
                break;
              case 1:
                player.goSouth();
                break;
              case 2:
                player.goEast();
                break;
              default:
                player.goWest();
                break;
            }
          }
        }
        else if(encounter == 'c')
        {
          System.out.println("You've entered the city.");
          System.out.println("Where would you like to go?.");
          System.out.println("1. Poke Store");
          System.out.println("2. Pokemon Hospital");
          int cityChoice = CheckInput.getIntRange(1,2);
          switch(cityChoice){
            case 1:
              store(player);
              break;
            default:
              System.out.println("Pokemon Nurse: Welcome to the Pokemon Hospital.");
              System.out.println("Pokemon Nurse: Let me heal your Pokemon for you.");
              System.out.println(".");
              System.out.println("..");
              System.out.println("...");
              player.healAllPokemon();
              System.out.println("Pokemon Nurse: I have healed your pokemon(s).");
              System.out.println("Pokemon Nurse: Please come again! See you soon!");
          }
        }
        else
        {
          //do nth if the encounter is 's'
        }

        if(player.getHp()==0)
        {
          terminate = true;
        }
      }
      
    }

     /*
* This method interracts with the user giving him different options in a store
*there are two actions that the user can do, either buy potions or pokeballs
* @params t is the trainer that arrives at the store who can stock potions and pokeballs
*/
    
  public static void store (Trainer t)
  {
     int action;
     do{
       System.out.println("Hello! What can i help you with? \n1. Buy potion - $5\n2. Buy Pokeball - $3\n3. Exist");
       action = CheckInput.getIntRange(1,3);
       if(action ==1&& t.spendMoney(5) != false){
          System.out.println("Here is your Potion.");
          t.receivePotion();
              }
       else if( action ==2 && t.spendMoney(3)!= false){
           System.out.println("Here is your Pokball");
           t.receivePokeball();
           }
      }while(action!=3);
    System.out.println("Thank you, Pls come again");
   }

  /**This method print out the main menu choices and return the choice that the player choose
    @return the choice chosen by the player
  */
  public static int mainMenu()
  {
    System.out.println("Main Menu");
    System.out.println("1. Go North");
    System.out.println("2. Go South");
    System.out.println("3. Go East");
    System.out.println("4. Go West");
    System.out.println("5. Quit");
    int choice;
    choice = CheckInput.getIntRange(1, 5);
    return choice;
  }
  /*
* This method get the user to pick a pokemon from his list and then attack the wild pokemon. He can use a basic or a special attack. However, if the pokemon that he choose has 0 Hp the wild pokemon will attack him.
*@params t : trainer that is attacking the wild pokemon 
*@params w : wild pokemond that is receiving damage
*/
  public static Pokemon trainerAttack(Trainer t, Pokemon wild){
    System.out.println("Choose a Pokemon");
    System.out.println(t.getPokemonList());
    int index = CheckInput.getIntRange(1,t.getNumPokemon());


    //chance of random wild pokemon debuffing trainer's pokemon
    Random rand = new Random();
    double chance = rand.nextDouble();
    PokemonGenerator pg = PokemonGenerator.getInstance();
    Pokemon trained_pokemon = t.getPokemon(index-1);


    if(trained_pokemon.getHp() == 0)
    {
      System.out.println("Your Pokemon has no more health. You take 5 damages.");
      t.takeDamage(5);
    }
    else
    {
        if(chance <= 0.1)
      {
        t.debuffPokemon(index-1);
        System.out.println("Your pokemon has been debuffed by " + wild.getName());
      }
      trained_pokemon = t.getPokemon(index-1);


      chance = rand.nextDouble();
      if(chance <=0.25)
      {
        wild = pg.addRandomDebuff(wild);
        System.out.println(wild.getName() + " has been debuffed by your pokemon " + trained_pokemon.getName());
      }
      System.out.println(trained_pokemon.getAttackTypeMenu());
      int atkType = CheckInput.getIntRange(1,2);
      System.out.println(trained_pokemon.getAttackMenu(atkType));
      int move = CheckInput.getIntRange(1,3);
      System.out.println(trained_pokemon.attack(wild, atkType, move));
      if(wild.getHp()!=0)
      {
        atkType = rand.nextInt(2)+1; //random from 0 to 1 + 1 =  1 to 2
        move = rand.nextInt(3)+1;
        System.out.println(wild.attack(trained_pokemon, atkType, move));
        
        System.out.println(trained_pokemon);
        System.out.println();
      }
      else
      {
      System.out.println("Wild "+ wild.getName() + " is fainted.");
      }
    }
    return wild;
  }
 
}
