import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import java.util.ArrayList;

public abstract class Character extends GameObject
   {
   private String name;
   private Image character;
   private World world;

   private int x;
   private int y;
   private int imageCounter;
   private int moveCounter;
   private int health;

   private boolean facingRight;
   private boolean imageChange;

   public Character( int xCord, int yCord, boolean fr, World w, int h )
      {
      name = getClassName();
      x = xCord;
      y = yCord;
      imageCounter = 0;
      imageChange = false;
      facingRight = fr;
      world = w;
      health = h;
      
      if( fr )
         {
         ImageIcon player = new ImageIcon( "Images/Character/" + name + "/" + name + "A.png" );
         character = player.getImage();
         }//end if

      else
         {
         ImageIcon player = new ImageIcon( "Images/Character/" + name + "/flip_" + name + "A.png" );
         character = player.getImage();
         }//end else
      }//end constructor Character

   public String getName()
      {
      return name;
      }//end method getName

   public Image getImage()
      {
      return character;
      }//end method getImage

   public void setImage( Image i )
      {
      character = i;
      }//end method getImage
   
   public World getWorld()
      {
      return world;
      } //wend method getWorld
   
   public void setWorld( World w )
      {
      world = w;
      }//end method setWorld   
      
   public int getX()
      {
      return x;
      }//end method getX

   public void setX( int xCord )
      {
      x = xCord;
      }//end method setX

   public int getY()
      {
      return y;
      }//end method getY

   public void setY( int yCord )
      {
      y = yCord;
      }//end method setX

   public boolean getFacingRight()
      {
      return facingRight;
      }//end method getDirection

   public void setFacingRight( boolean fr )
      {
      facingRight = fr;
      }//end method setDirection
   
   public void loseHealth( int h )
      {
      health -= h;
      }//end method loseHealth
      
   public void setHealth( int h )
      {
      health = h;    
      }//end method setHealth
      
   public int getHealth()
      {
      return health;
      }//end method getHealth
      
   public boolean dead()
      {
      if ( health <= 0 )
         {
         return true;
         }//end if

      return false;
      }//end method dead
      
   public boolean getImageChange()
      {
      return imageChange;
      }//end method getImageChange

   public void setImageChange( boolean ic )
      {
      imageChange = ic;
      }//end method setImageChange

   public int getImageCounter()
      {
      return imageCounter;
      }//end method getImageCounter

   public void setImageCounter( int i )
      {
      imageCounter = i;
      }//end method getImageCounter

   public void updateImage()
      {
      if( imageCounter > 70 )
         {
         if( facingRight )
            {
            if( !imageChange )
               {
               ImageIcon i = new ImageIcon( "Images/Character/" + name + "/" + name + "A.png" );
               character = i.getImage();
               imageChange = true;
               }//end if

            else if( imageChange )
               {
               ImageIcon i = new ImageIcon( "Images/Character/" + name + "/" + name + "B.png" );
               character = i.getImage();
               imageChange = false;
               }//end else if
            }//end if

         else if( !facingRight )
            {
            if( !imageChange )
               {
               ImageIcon i = new ImageIcon( "Images/Character/" + name + "/flip_" + name + "A.png" );
               character = i.getImage();
               imageChange = true;
               }//end if
            else if( imageChange )
               {
               ImageIcon i = new ImageIcon( "Images/Character/" + name + "/flip_" + name + "B.png" );
               character = i.getImage();
               imageChange = false;
               }//end else if
            }//end else if

         imageCounter = 0;
         }//end if

      imageCounter++;
      }//end method updateImage

   public void move()
      {
      if( moveCounter > 20 )
         {
         if( facingRight == true )  
            {
            x++;
            }//end if
         else if( facingRight == false )
            {
            x--;
            }//end else if

         moveCounter = 0;
         }//end if

      moveCounter++;
      }//end method move
   }//end class Character