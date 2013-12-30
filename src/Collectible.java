import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;

public abstract class Collectible extends GameObject
   {
   private int x;
   private int y;
   private String name;
   private Image collectible;
   private World world;
   
   public Collectible( int xCord, int yCord, World w )
      {
      name = getClassName();   
      x = xCord;
      y = yCord;
      world = w;
      
      ImageIcon collectibleIcon = new ImageIcon( "Images/" + name +".png" );
      collectible = collectibleIcon.getImage(); 
      }//end constructor Collectible
      
   public Image getImage() 
      {
      return collectible; 
      }//end method getImage
      
   public int getX()
      {
      return x; 
      }//end method getX
      
   public int getY()
      {
      return y; 
      }//end method getY
      
   public void setX( int xCord )
      {
      x = xCord;    
      }//end method setX
      
   public void setY( int yCord )
      {
      y = yCord;        
      }//end method setY
   }//end class Collectible