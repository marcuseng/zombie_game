import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class World extends JPanel implements ActionListener
   {
   private Image background;
   private Image middleground;
   private Survivor player;
   private Timer time;
   private int playerScore;
   private int collisionInterval;
   private boolean collisionDetected;

   private ArrayList<Zombie> horde;
   private ArrayList<Bullet> bullets;
   private ArrayList<Ammo> ammoList;
   
   Label health;
   Label ammo;
   Label score;
   Label nullLabel;

   public World()
      {
      ImageIcon bg = new ImageIcon( "Images/Background.png" );
      ImageIcon mg = new ImageIcon( "Images/Middleground.png" );

      background = bg.getImage();
      middleground = mg.getImage();

      addKeyListener( new Controller() );

      player = new Survivor( 10, 400, true, this, 100 );

      setFocusable( true );
      bullets = new ArrayList<Bullet>();
      horde = new ArrayList<Zombie>();
      ammoList = new ArrayList<Ammo>();
      collisionInterval = 100;
      collisionDetected = false;
      playerScore = 0;

      health = new Label( "Health: " + player.getHealth() );
      ammo = new Label( "Ammo: " + player.getWeaponList().get( player.getWeaponChoice() ).getAmmo() );
      score = new Label( "Score: " + playerScore );
      nullLabel = new Label( " " );
      
      
      horde.add( new Zombie( 500, 380, false, this ) );
      horde.add( new Zombie( 200, 360, true, this ) );
      horde.add( new Zombie( 180, 370, true, this ) );
      horde.add( new Zombie( 270, 390, false, this ) );
      horde.add( new Zombie( 510, 400, true, this ) );
      horde.add( new Zombie( 300, 410, false, this ) );
      horde.add( new Zombie( 190, 420, true, this ) );
      
      ammoList.add( new Ammo( 400, 400, this ) );
      
      time = new Timer( 1, this );
      time.start();
      }//end constructor

   public void actionPerformed( ActionEvent e )
      {
      player.move();
      player.updateImage();
      
      for( int b = 0; b < bullets.size(); b++ )
         {
         while ( bullets.size() > 0 )
            {
            bullets.get( b ).move();
            boolean bulletStillThere = true;

            for( int index = 0; index < horde.size(); index++ )
               { 
               if( bulletStillThere && bullets.size() != 0 )
                  {
                  if( ( bullets.get( b ).getX() == ( 800 - player.getFrameShift() ) + horde.get( index ).getX() ) && ( bullets.get( b ).getY() > ( horde.get( index ).getY() - 40 ) ) && ( bullets.get( b ).getY() < ( horde.get( index ).getY() + 40 ) )   )
                     {
                     //System.out.println( "Bullet hit zombie for " + bullets.get( b ).getDamage() + " damage!" );
                     horde.get( index ).loseHealth( bullets.get( b ).getDamage() ); 
                     //System.out.println( "Zombie now has " + horde.get( index ).getHealth() + " health!" );
                     if( horde.get( index ).dead() ) 
                        {
                        horde.remove( index );
                        playerScore += 25;
                        }//end if
                     index--;
                     bullets.get( b ).removeBulletFromWorld();
                     if( b != 0 )
                        {  
                        b--; 
                        }//end if
                     bulletStillThere = false;
                     }//end if
                  }//end if
               }//end for
            }//end while
         }//end for

      for( int index = 0; index < horde.size(); index++ )
         {  
         horde.get( index ).move();
         if( ( player.getX() > ( ( 800 - player.getFrameShift() ) + horde.get( index ).getX() - 8 ) ) && ( player.getX() < ( ( 800 - player.getFrameShift() ) + horde.get( index ).getX() + 8 ) ) ) 
            {
            if( ( player.getY() > ( horde.get( index ).getY() - 20 ) ) && ( player.getY() < ( horde.get( index ).getY() + 20 ) ) ) 
               {
               if( collisionDetected == false )
                  {
                  player.loseHealth( 10 );
                  if ( player.dead() )
                     {
                     JOptionPane.showMessageDialog( new JFrame(), "You've been eaten!" );
                     System.exit( 10 );
                     }//end if
                  }//end if
               collisionDetected = true;       
               }//end if
            }//end if

         horde.get( index ).updateImage();
         }//end for
      
      for ( int a = 0; a < ammoList.size(); a++ )  
         {
         if ( player.getX() > ( 800 - player.getFrameShift() + ammoList.get( a ).getX() - 25 ) && player.getX() < ( 800 - player.getFrameShift() + ammoList.get( a ).getX() + 25 ) && player.getY() > ( ammoList.get( a ).getY() - 25 ) && player.getY() < ( ammoList.get( a ).getY() + 25 ) )
            {
            if( player.getWeaponList().get( player.getWeaponChoice() ) instanceof Pistol )
               {
               player.getWeaponList().get( player.getWeaponChoice() ).addAmmo( 10 );
               ammoList.remove( a );
               }

            else if( player.getWeaponList().get( player.getWeaponChoice() ) instanceof Shotgun )
               {
               player.getWeaponList().get( player.getWeaponChoice() ).addAmmo( 60 );
               ammoList.remove( a );
               }

            if ( ammoList.size() > 0 )
               {
               a--;  
               }//end if
            }//end if
         }//end for
         
      if ( collisionDetected ) 
         {
         collisionInterval--;
         //System.out.println( "interval started... collision int = " + collisionInterval );
         if ( collisionInterval == 0 )
            {
            collisionInterval = 100;
            //System.out.println( "interval reset... collision int = " + collisionInterval );
            collisionDetected = false;
            }//end if
         }//end if

      health.setText( "Health: " + player.getHealth() );
      ammo.setText( "Ammo: " + player.getWeaponList().get( player.getWeaponChoice() ).getAmmo() );
      score.setText( "Score: " + playerScore );
      
      int spawnZombie = ( int ) ( 1 + Math.random() * 1000 );
      int ammoSpawn = ( int ) ( Math.random() * 1000 );
      int randomDir = ( int ) ( Math.random() * 2 );
      boolean dir = true;

      if( randomDir == 0 )
         {
         dir = true;
         }//end if

      else
         {
         dir = false;
         }//end else
      
      if ( ammoSpawn == 1 ) 
         {
         int ammoX = ( int ) ( 1 + Math.random() * 800 );
         int ammoY = ( int ) ( 350 + Math.random() * 100 );
             
         ammoList.add( new Ammo( ammoX, ammoY, this ) ); 
         }//end if
      
      if ( spawnZombie < 8 )
         {
         int newX = ( int ) ( 1 + Math.random() * 800 );
         int newY = ( int ) ( 350 + Math.random() * 100 );
             
         horde.add( new Zombie( newX, newY, dir, this ) );  
         }//end if
      
      repaint();
      }//end method actionPerformed
   
   public void addBullet( Bullet b )
      {
      bullets.add( b );  
      //System.out.println( "Bullet added at... (" + b.getX() + "," + b.getY() + ")" );
      }//end method addBullet
   
   public void removeBullet( Bullet b )
      {
      bullets.remove( b ); 
      //System.out.println( "Bullet removed..." );      
      }//end method removeBullet

   public void paint( Graphics g )
      {
      super.paint( g );
      Graphics2D graphics = ( Graphics2D ) g;

      graphics.drawImage( background, 0, 0, null );
      graphics.drawImage( middleground, 800 - player.getFrameShift(), 0, null);

      if( player.getFrameShift() > 2400 )
         {
         graphics.drawImage( middleground, 3200 - player.getFrameShift(), 0, null);

         if( player.getFrameShift() == 3200 )
            {
            player.setFrameShift( 800 );
            }//end if
         }//end if

      for( int index = 0; index < horde.size(); index++ )
         {
         graphics.drawImage( horde.get( index ).getImage(), ( 800 - player.getFrameShift() ) + horde.get( index ).getX(), horde.get( index ).getY(), null );
         }//end for
         
      for( int index = 0; index < ammoList.size(); index++ )
         {
         graphics.drawImage( ammoList.get( index ).getImage(), ( 800 - player.getFrameShift() ) + ammoList.get( index ).getX(), ammoList.get( index ).getY(), null );
         }//end for

      graphics.drawImage( player.getImage(), player.getX(), player.getY(), null );
      graphics.drawImage( player.getWeapon(), player.getWeaponX(), player.getWeaponY(), null );
      graphics.drawImage( player.getFlash(), player.getFlashX(), player.getWeaponY() - 3, null );
      }//end method paint

   private class Controller extends KeyAdapter
      {
      public void keyReleased( KeyEvent e )
         {
         player.keyReleased( e );
         }//end method keyReleased

      public void keyPressed( KeyEvent e )
         {
         player.keyPressed( e );
         }//end method keyReleased
      }//end class Controller
   }//end class Board