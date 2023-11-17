/** Model of the map
  * @author Samnang Lath
  * @since 2021-30-11
  **/
import java.io.*;
import java.util.Scanner;
import java.awt.Point;
//import java;
public class Map{
    private char [][] map;
    private  boolean [][] reveal;
    private static Map instance = null;
  /** Map constructor
   *initialize 2 dimentional array for location of map and location where we already been
   * Constructor is now private to convert map into singleton
   */
    private Map(){
       map= new char [5][5];
       reveal= new boolean [5][5];
    }
    
    /** Get Instance function
    *Create an instance of the map if it doesn't exist
    *@return the created instance
    */
    public static Map getInstance(){
      if(instance == null)
      {
        instance = new Map();
      }
      return instance;
    }

    /** Read the map from file and store the characters of map into array map and initilize reveal array to false
     * @params mapNum represent the map number to be loaded
     */
    public void loadMap(int mapNum){
      
      
      try 
      {
        Scanner read = new Scanner(new File("Area" +mapNum+ ".txt"));
        do
        {
          for(int i= 0; i <5; i++ ){
          for ( int j =0 ; j<5;j++ ){
          String s= read.next(); // read from the file 
          map[i][j] = s.charAt(0);
          reveal[i][j] = false;
        }
      }
   }while(read.hasNextLine());
 } 
catch(FileNotFoundException fnf){
      System.out.println("Map "+ mapNum +" was not found");
}   
 /** Give the char at a given location
  *@params p is the point that need to be converted to char
  @return the charecter at a postion
  */
 }
public char getCharAtLoc(Point p) {
    int row = (int) p.getY();
    int col = (int) p.getX();
    return map[row][col];
}

/** Give the string representation of map
  * Handle the hidden points and revealed points
  *@params point p location of the player
  *@return full map by showing X letter for unrevealed location of each map */
public String mapToString( Point p){
       int row = (int) p.getY();
        int col = (int) p.getX();
        //reveal[row][col] = true;
      // map[row][col]= '*';
        String Amap= "";
        for( int i = 0; i<5;i++){
          for(int j = 0;j<5; j++){
              if (reveal[i][j]==true){
                  if (i==row&&j==col){          //print * at start location
                      Amap+="* ";
                    }
                  else{
                    
                      Amap+= map[i][j]+" ";
                }
                   }
                else{
                  if (i==row&&j==col){
                      Amap+="* ";
                  }
                  else{
                      Amap += "x ";
                
                  }
                 }
              }
              Amap+="\n";
            }
            return Amap;
        }     
/** Find the starting point on the map
 *@return start position of player
 */        
public Point findStart(){
    int x=0;
    int y=0;
    for(int i=0;i<map.length;i++){
        
      for(int j=0;j<map[0].length;j++){
        if(map[i][j]=='s') // condition of the map character is s it will get the                             location
        {
          x=j;
          y=i;
        }
      }
    }
    Point start = new Point (x,y);
    return start;
   }
   /** Reveal the point on the map
    *params p is the point that need to be revealed
     */
public void reveal ( Point p){
        int row = (int) p.getY();
        int col = (int) p.getX();
        reveal[row][col]=true;
       }
       
       /*** Replace char at a discovered location(wild pokemon and person) with 'n'
       * @param p is the point that need */
public void removeCharAtLoc (Point p){
     int row = (int) p.getY();
        int col = (int) p.getX();
        map[row][col] = 'n';
   }             
    
}