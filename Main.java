import java.net.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.text.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.util.*;
import javafx.scene.paint.*;
import javafx.geometry.*;
import javafx.scene.image.*;

import java.io.*;

import java.util.*;
import java.text.*;
import java.io.*;
import java.lang.*;
import javafx.application.*;
import javafx.event.*;
import javafx.stage.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.animation.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import java.net.*;
import javafx.geometry.*;


public class Main extends Application
{
   FlowPane fp;
   
   Canvas theCanvas = new Canvas(600,600);

   public void start(Stage stage)
   {
      
   
      fp = new FlowPane();
      fp.getChildren().add(theCanvas);
      gc = theCanvas.getGraphicsContext2D();
      drawBackground(300,300,gc);
      
      fp.setOnKeyPressed(new KeyListenerDown());
      fp.setOnKeyReleased(new KeyListenerUp());
      
      Scene scene = new Scene(fp, 600, 600);
      stage.setScene(scene);
      stage.setTitle("Project :)");
      stage.show();
      
      fp.requestFocus();
      ah.start();
      
   }
   
   GraphicsContext gc;
   Random rand = new Random();
   
   
   Image background = new Image("stars.png");
   Image overlay = new Image("starsoverlay.png");
   Random backgroundRand = new Random();
   
   
   //this piece of code doesn't need to be modified
   public void drawBackground(float playerx, float playery, GraphicsContext gc)
   {
	  //re-scale player position to make the background move slower. 
      playerx*=.1;
      playery*=.1;
   
	//figuring out the tile's position.
      float x = (playerx) / 400;
      float y = (playery) / 400;
      
      int xi = (int) x;
      int yi = (int) y;
      
	  //draw a certain amount of the tiled images
      for(int i=xi-3;i<xi+3;i++)
      {
         for(int j=yi-3;j<yi+3;j++)
         {
            gc.drawImage(background,-playerx+i*400,-playery+j*400);
         }
      }
      
	  //below repeats with an overlay image
      playerx*=2f;
      playery*=2f;
   
      x = (playerx) / 400;
      y = (playery) / 400;
      
      xi = (int) x;
      yi = (int) y;
      
      for(int i=xi-3;i<xi+3;i++)
      {
         for(int j=yi-3;j<yi+3;j++)
         {
            gc.drawImage(overlay,-playerx+i*400,-playery+j*400);
         }
      }
   }
   
   
   Player thePlayer = new Player(300, 300);
   Mine theMine = new Mine(200, 200);
   AnimationHandler ah = new AnimationHandler();
   int cgridx; 
   int cgridy;
   int mineSpawnx;
   int mineSpawny;
   
   public class AnimationHandler extends AnimationTimer
   {
      public void handle(long currentTimeInNanoSeconds) 
      {
         gc.clearRect(0,0,600,600);
         thePlayer.forceCalc();
         cgridx = ((int)thePlayer.getX())/100;
         cgridy = ((int)thePlayer.getY())/100;
         mineSpawnx = cgridx * (rand.nextInt(1) + 3);
         mineSpawny = cgridy * (rand.nextInt(1) + 3);
         
         System.out.println("X is "+mineSpawnx);
         System.out.println("Y is "+mineSpawny);

         
         //USE THIS CALL ONCE YOU HAVE A PLAYER
         drawBackground(thePlayer.getX(),thePlayer.getY(),gc); 


	      //example calls of draw - this should be the player's call for draw
         thePlayer.draw(300,300,gc,true); //all other objects will use false in the parameter.

         //example call of a draw where m is a non-player object. Note that you are passing the player's position in and not m's position.
         theMine.draw(thePlayer.getX(),thePlayer.getY(),gc,false);
         
         callNewMine();
         
         
         
      }
      public void callNewMine()
      {
      int mineCap = 5;
      for(int i = 0; i < mineCap; i++)
      {
         theMine.draw(mineSpawnx,mineSpawny,gc,false);
      }
      }
   }

      
  

      public class KeyListenerDown implements EventHandler<KeyEvent>  
    {
        public void handle(KeyEvent event) 
        {
        
            //For key press W
            if(event.getCode() == KeyCode.W)
            {
               thePlayer.act("W");
            }
            
            //For key press A
            if(event.getCode() == KeyCode.A)
            {
               thePlayer.act("A");
            }
            
            //For key press S
            if(event.getCode() == KeyCode.S)
            {
               thePlayer.act("S");
            }
            
            //For key press D
            if(event.getCode() == KeyCode.D)
            {
               thePlayer.act("D");
            }
        }
    }

       public class KeyListenerUp implements EventHandler<KeyEvent>  
    {
        public void handle(KeyEvent event) 
        {
         thePlayer.act("X");
        }
    }

   public static void main(String[] args)
   {
      launch(args);
   }
}

