import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;

public class Zombie extends Character
   {
   private static final int startingHealth = 25;    
       
   public Zombie( int xCord, int yCord, boolean fr, World w)
      {
      super( xCord, yCord, fr, w, startingHealth );
      }//end constructor Character
   }//end class Zombie