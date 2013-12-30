import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import java.util.ArrayList;

public class Survivor extends Character
   {
   private static int xBoundaryA;
   private static int xBoundaryB;
   private static int yBoundaryA;
   private static int yBoundaryB;

   private int deltaX;
   private int deltaY;
   private int frameShift;
   private int weaponChoice;

   private ArrayList<Weapon> weaponList;

   public Survivor( int xCord, int yCord, boolean fr, World w, int h )
      {
      super( xCord, yCord, fr, w, h );

      xBoundaryA = 600;
      xBoundaryB = 10;
      yBoundaryA = 330;
      yBoundaryB = 490;
      deltaX = 0;
      deltaY = 0;
      frameShift = 800;

      weaponList = new ArrayList<Weapon>();
      weaponList.add( new Pistol( this ) );
      weaponList.add( new Shotgun( this ) );

      weaponChoice = 0;
      }//end constructor Character

   public Image getWeapon()
      {
      for( int index = 0; index < weaponList.size(); index++ )
         {
         weaponList.get( index ).setFacingRight( getFacingRight() );
         }//end for

      return weaponList.get( weaponChoice ).getWeaponImage();
      }//end method getWeapon

   public Image getFlash()
      {
      return weaponList.get( weaponChoice ).getFlashImage();
      }//end method getFlash

   public int getWeaponX()
      {
      return getX() + weaponList.get( weaponChoice ).getX();
      }//end method getWeaponX

   public int getWeaponY()
      {
      return getY() + weaponList.get( weaponChoice ).getY();
      }//end method getWeaponY

   public int getFlashX()
      {
      return getX() + weaponList.get( weaponChoice ).getX() + weaponList.get( weaponChoice ).getFlashX();
      }//end method getFlashX

   public int getFrameShift()
      {
      return frameShift;
      }//end method getFrameShift

   public void setFrameShift( int m )
      {
      frameShift = m;
      }//end method setFrameShift
      
   public ArrayList<Weapon> getWeaponList()
      {
      return weaponList;    
      }
   
   public int getWeaponChoice()
      {
      return weaponChoice;
      }

   public void updateImage()
      {
      if( getImageCounter() > 30 && deltaX != 0 || deltaY != 0 )
         {
         if( getFacingRight() == true )
            {
            if( getImageChange() == false )
               {
               ImageIcon i = new ImageIcon( "Images/Character/" + getName() + "/" + getName() + "A.png" );
               setImage( i.getImage() );
               setImageChange( true );
               }//end if
            else if( getImageChange() == true )
               {
               ImageIcon i = new ImageIcon( "Images/Character/" + getName() + "/" + getName() + "B.png" );
               setImage( i.getImage() );
               setImageChange( false );
               }//end else if
            }//end if

         else if( getFacingRight() == false )
            {
            if( getImageChange() == false )
               {
               ImageIcon i = new ImageIcon( "Images/Character/" + getName() + "/flip_" + getName() + "A.png" );
               setImage( i.getImage() );
               setImageChange( true );
               }//end if
            else if( getImageChange() == true )
               {
               ImageIcon i = new ImageIcon( "Images/Character/" + getName() + "/flip_" + getName() + "B.png" );
               setImage( i.getImage() );
               setImageChange( false );
               }//end else if
            }//end else if

         setImageCounter( 0 );
         }//end if

      setImageCounter( getImageCounter() + 1 );
      }//end method updateImage

   public void move()
      {
      //Creation of X-Boundary
      if( getX() < xBoundaryA && getX() > xBoundaryB )
         {
         setX( getX() + deltaX );
         }//end if
      else
         {
         if( getX() == xBoundaryA )
            {
            if( deltaX < 0 )
               {
               setX( getX() + deltaX );
               }//end if

            else if( deltaX > 0 )
               {
               frameShift += deltaX;
               }//end else
            }//end if

         else if( getX() == xBoundaryB && deltaX > 0 )
            {
            setX( getX() + deltaX );
            }//end else if
         }//end else

      //Creation of Y-Boundary
      if( getY() > yBoundaryA && getY() < yBoundaryB )
         {
         setY( getY() - deltaY );
         }//end if
      else
         {
         if( getY() == yBoundaryA && deltaY < 0)
            {
            setY( getY() - deltaY );
            }//end if
         else if( getY() == yBoundaryB && deltaY > 0 )
            {
            setY( getY() - deltaY );
            }//end else if
         }//end else
      }//end method move

   public void keyPressed( KeyEvent e )
      {
      int key = e.getKeyCode();

      if( key == 65 ) //Checks if keypress is "A"
         {
         setFacingRight( false );
         deltaX = -1;
         }//end if

      else if( key == 68 )//Checks if keypress is "D"
         {
         setFacingRight( true );
         deltaX = 1;
         }//end if

      else if ( key == 87 )//Checks if keypress is "W"
         {
         deltaY = 1;
         }//end else if

      else if ( key == 83 )//Checks if keypress is "S"
         {
         deltaY = -1;
         }//end else if
      }//end method keyPressed

   public void keyReleased( KeyEvent e )
      {
      int key = e.getKeyCode();

      if( key == 65 )//Checks if keypress is "A"
         {
         deltaX = 0;
         }//end if

      else if( key == 68 )//Checks if keypress is "D"
         {
         deltaX = 0;
         }//end else if

      else if ( key == 87 )//Checks if keypress is "W"
         {
         deltaY = 0;
         }//end else if

      else if ( key == 83 )//Checks if keypress is "S"
         {
         deltaY = 0;
         }//end else if

      else if( key == 32 )//Checks if keypress is "Space"
         {
         weaponList.get( weaponChoice ).shoot();
         }//end else if 

      else if( key == 49 )// Checks if keypress is "1"
         {
         weaponChoice = 0;
         }//end else if

      else if( key == 50 )// Checks if keypress is "2"
         {
         weaponChoice = 1;
         }//end else if
      }//end method keyReleased
   }//end class Character