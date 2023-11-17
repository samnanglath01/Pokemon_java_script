public class HpDown extends PokemonDecorator{
  public HpDown(Pokemon p)
  {
    super(p,"-HP", -(int)(Math.random()*(2) + 1));
  }
}