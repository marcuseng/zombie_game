import javax.swing.*;
import java.awt.*;

public class Frame
   {
   private JFrame frame;
   private JFrame stats;
   private World w;

   public Frame()
      {
      frame = new JFrame( "ZOMBIEZ!" );
      frame.setSize( 800, 580 );
      stats = new JFrame( "Stats" );
      stats.setSize( 320, 200 );
      w = new World();
      
      Font f = new Font("Arial Bold", Font.PLAIN, 24 );

      w.health.setFont( f );
      w.ammo.setFont( f );
      w.score.setFont( f );
      
      w.health.setBounds( 0, 10, 320, 60 );
      w.ammo.setBounds( 0, 70, 320, 60 );
      w.score.setBounds( 0, 130, 320, 60 );

      w.health.setAlignment( Label.CENTER );
      w.ammo.setAlignment( Label.CENTER );      
      w.score.setAlignment( Label.CENTER );
      
      stats.add( w.health );
      stats.add( w.ammo );
      stats.add( w.score );
      stats.add( w.nullLabel );
      
      frame.add( w );
      
      frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      frame.setResizable( false );
      frame.setVisible( true );
      stats.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      stats.setVisible( true );
      
      }//end constructor Frame
   }//end class Frame