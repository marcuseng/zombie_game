public class Bullet
   {
   private String direction;
   private Weapon weapon;
   
   private int damage;
   private int posX;
   private int posY;
   
   public Bullet( String dir, int dam, int x, int y, Weapon w )
      {
      direction = dir; 
      damage = dam;
      posX = x;
      posY = y;
      weapon = w;
      addBulletToWorld();
      }//end constructor Bullet
   
   public void move()
      {
      if ( direction.equals( "RIGHT" ) )
         {
         posX++; 
         //System.out.println( "Bullet moved to... (" + getX() + "," + getY() + ")" );
         }//end if
      else if ( direction.equals( "LEFT" ) )
         {
         posX--;   
         //System.out.println( "Bullet moved to... (" + getX() + "," + getY() + ")" );
         }//end else if

      if ( getX() == 0 || getX() == 800 )
         {
         removeBulletFromWorld(); 
         }//end if
      }//end method move
   
   private void addBulletToWorld()
      {
      weapon.getOwner().getWorld().addBullet( this );     
      }//end method addBulletToWorld
      
   public void removeBulletFromWorld()
      {
      weapon.getOwner().getWorld().removeBullet( this );
      weapon = null;
      }//end method removeBulletFromWorld
      
   public void setDirection( String d )
      {
      direction = d;
      }//end method setDirection

   public String getDirection()
      {
      return direction;
      }//end method getDirection
   public Weapon getWeapon()
      {
      return weapon;
      }//end method getWeapon
   
   public void setWeapon( Weapon w )
      {
      weapon = w;
      }//end method setWorld      
      
   public void setDamage( int d )
      {
      damage = d;
      }//end method setDamage

   public int getDamage()
      {
      return damage;
      }//end method getDamage

   public void setX( int x )
      {
      posX = x;
      }//end method setX

   public int getX()
      {
      return posX;    
      }//end method getX

   public void setY( int y )
      {
      posY = y;
      }//end method setY

   public int getY()
      {
      return posY;  
      }//end method getY
   }//end class Bullet