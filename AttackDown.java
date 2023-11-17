import java.util.Random;
/** AttackDown Decorator
  * @author Sakol Bun
  * @version 1.0
  * @since 2021-30-11
  */
public class AttackDown extends PokemonDecorator
{
  public AttackDown(Pokemon p)
  {
    super(p, "-ATK", 0 );
  }

  @Override
  public int getAttackBonus()
  {
    Random rand = new Random();
    int bonusDamage = -(rand.nextInt(1)+1);
    return bonusDamage;
  }
}