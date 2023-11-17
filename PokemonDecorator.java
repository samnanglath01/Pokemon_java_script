/** Pokemon Decorator to decorate pokemon with buff and debuff
  * @author Samnang Lath
  * @version 1.0
  * 
**/
public abstract class PokemonDecorator extends Pokemon
{
  
  private Pokemon pokemon;

  /** constructor of PokemonDecorator
  * decorate pokemon
  *@param p is the pokemon to be decorated
  *@param extraName is the name to be added depend on the buff or debuff
  *@param extraHp is the extra health to be added to the pokemon depend on the buff or debuff
  */
  public PokemonDecorator(Pokemon p, String extraName, int extraHp)
  {
    
    super(p.getName()+ " " + extraName, p.getHp() + extraHp, p.getMaxHp() + extraHp);
    pokemon = p;
  }

  /** Get the attack menu string of the pokemon
  *@param atkType is the type of attack move of the pokemon
  
  *@return the attack menu
  */
  @Override
  public String getAttackMenu(int atkType)
  {
    return pokemon.getAttackMenu(atkType);
  }

  /** Get the number of attack menu items of the pokemon
  *@param atkType is the type of attack move of the pokemon
  *@return the attack menu items number
  */
  @Override
  public int getNumAttackMenuItems(int atkType)
  {
    return pokemon.getNumAttackMenuItems(atkType);
  }

  /** Get the attack string of the pokemon
  *@param atkType is the type of attack move of the pokemon
  *@param move is the attack move of the pokemon
  *@return the attack string
  */
  @Override
  public String getAttackString(int atkType, int move)
  {
    return pokemon.getAttackString(atkType, move);
  }

  /** Get the attack string of the pokemon
  *@param atkType is the type of attack move of the pokemon
  *@param move is the attack move of the pokemon
  *@return the attack string
  */
  @Override
  public int getAttackDamage(int atkType, int move)
  {
    return pokemon.getAttackDamage(atkType, move);
  }

  /** Get the attack multiplier of the pokemon
  *@param p is the pokemon to be attacked
  *@param atkType is the type of attack move of the pokemon
  *@return the attack multiplier 
  */
  @Override
  public double getAttackMultiplier(Pokemon p, int atkType)
  {
    return pokemon.getAttackMultiplier(p, atkType);
  }

  /** Get the attack bonus that the pokemon gained from buff or debuff
  *@param atkType is the type of attack move of the pokemon
  *@return the attack bonus 
  */
  @Override
  public int getAttackBonus()
  {
    return pokemon.getAttackBonus();
  }

  /** Get the type of the pokemon
  *@return the type of the pokemon
  */
  @Override
  public int getType()
  {
    return pokemon.getType();
  }
}