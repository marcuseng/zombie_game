public class Shotgun extends Weapon
   {
   public Shotgun( Character c )
      {
      super( 26, 30, -25, 43, -12, c );

      setFireRate( 6 );
      setAmmo( 400 );
      setDamage( 30 );
      }//end constructor Shotgun
   }//end class Shotgun