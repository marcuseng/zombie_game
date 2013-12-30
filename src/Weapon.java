import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;

 abstract class Weapon extends GameObject
   {
   private int x;
   private int rx;
   private int y;
   private int flashX;
   private int rFlashX;
   private int fireRate;
   private int ammo;
   private int damage;

   private boolean weaponFired;
   private boolean facingRight;
   private String name;
   private Image weapon;
   private Image muzzleFlash;
   private Character owner;

   public Weapon( int xCord, int yCord, int reverseX, int flashXCord, int reverseFlashX, Character c )
      {
      x = xCord;
      rx = reverseX;
      y = yCord;
      flashX = flashXCord;
      rFlashX = reverseFlashX;
      owner = c;

      weaponFired = false;
      facingRight = true;
      name = getClassName();
      }//end class Weapon

   public int getX()
      {
      if( facingRight )
         {
         return x;
         }//end if
      else
         {
         return rx;
         }//end else
      }//end method getX

   public int getY()
      {
      return y;
      }//end method getY

   public int getFlashX()
      {
      if( facingRight )
         {
         return flashX;
         }//end if
      else
         {
         return rFlashX;
         }//end else
      }//end method getFlashX

   public void setFacingRight( boolean fr )
      {
      facingRight = fr;
      }//end setFacingRight

   public Image getWeaponImage()
      {
      if( facingRight )
         {
         ImageIcon equippedWeapon = new ImageIcon( "Images/Weapon/" + name + "/" + name +".png" );
         weapon = equippedWeapon.getImage();
         }//end if

      else
         {
         ImageIcon equippedWeapon = new ImageIcon( "Images/Weapon/" + name + "/flip_" + name +".png" );
         weapon = equippedWeapon.getImage();
         }//end else

      return weapon;
      }//end method getWeapon Image

   public Image getFlashImage()
      {
      if( weaponFired )
         {
         weaponFired = false;

         return muzzleFlash;
         }//end if

      else if( !weaponFired )
         {
         return null;
         }//end else

      else
         {
         return muzzleFlash;
         }//end else
      }//end method getMuzzleFlashImage

   public int getFireRate()
      {
      return fireRate;
      }//end method getFireRate

   public int getAmmo()
      {
      return ammo;
      }//end method getAmmo

   private int getDamage()
      {
      return damage;
      }//end method getDamage

   public void setFireRate( int x )
      {
      fireRate = x;
      }//end method setFireRate

   public void setAmmo( int a )
      {
      ammo = a;
      }//end method setAmmo
      
   public void addAmmo( int a )
      {
      ammo += a;
      }

   public void setDamage( int d )
      {
      damage = d;
      }//end method setDamage
      
   public Character getOwner()
      {
      return owner;
      }//end method getDamage

   public void setOwner( Character c )
      {
      owner = c;
      }//end method setFireRate

   public void shoot()
      {
      if( ammo > 0 )
         {
         weaponFired = true;

         if( facingRight )
            {
            ImageIcon flash = new ImageIcon( "Images/Weapon/MuzzleFlash.png" );
            muzzleFlash = flash.getImage();
            new Bullet( "RIGHT", damage, owner.getX(), owner.getY(), this );
            ammo -= fireRate;
            }//end if
         else if( !facingRight )
            {
            ImageIcon flash = new ImageIcon( "Images/Weapon/flip_MuzzleFlash.png" );
            muzzleFlash = flash.getImage();
            new Bullet( "LEFT", damage, owner.getX(), owner.getY(), this );
            ammo -= fireRate;
            }//end else

         if( ammo < 0 )
            {
            ammo = 0;
            }//end if
         }//end if
      }//end method shoot
   }//enc class Weapon
