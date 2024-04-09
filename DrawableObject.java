import javafx.scene.paint.*;
import javafx.scene.canvas.*;

public abstract class DrawableObject
{
   public DrawableObject(float x, float y)
   {
      this.x = x;
      this.y = y;
   }

   //positions
   private float x;
   private float y;
   
   //takes the position of the player and calls draw me with appropriate positions
   public void draw(float playerx, float playery, GraphicsContext gc, boolean isPlayer)
   {
      //the 300,300 places the player at 300,300, if you want to change it you will have to modify it here
      
      if(isPlayer)
         drawMe(playerx,playery,gc);
      else
         drawMe(-playerx+300+x,-playery+300+y,gc);
   }
   
   //this method you implement for each object you want to draw. Act as if the thing you want to draw is at x,y.
   //NOTE: DO NOT CALL DRAWME YOURSELF. Let the the "draw" method do it for you. I take care of the math in that method for a reason.
   public abstract void drawMe(float x, float y, GraphicsContext gc);
   
   private String lastKey = "";
   private float forceX;
   private float forceY;
   public void act(String check)
   {
   lastKey = check;
   }
   
   public void forceCalc()
   {
    //Moves character in the direction in the direction of the last key press
      switch(lastKey){
         case "W":
            forceY +=-0.1;
            break;
         case "S":
            forceY += 0.1;
            break;
         case "D":
            forceX +=0.1;
            break;
         case "A":
            forceX += -0.1;
            break;
      }

         if(lastKey.equals("W") || lastKey.equals("S") || lastKey.equals("X")) {
            if(forceX > 0)
               forceX+= -0.025;
            else if(forceX < 0)
               forceX += 0.025;
         } 

         if(lastKey.equals("A") || lastKey.equals("D") || lastKey.equals("X")) {
            if(forceY > 0)
               forceY+= -0.025;
            else if(forceY < 0)
               forceY += 0.025;
         } 
               
         if((forceX > -0.25 && forceX < 0.25) && (lastKey.equals("W") || lastKey.equals("S") || lastKey.equals("X")))
            forceX = 0;
         else if(forceX > 5)
            forceX = 5; 
         else if (forceX < -5)
            forceX = -5;
         
         if(forceY > -0.25 && forceY < 0.25 && (lastKey.equals("A") || lastKey.equals("D") || lastKey.equals("X")))      
            forceY = 0;
         else if(forceY > 5)
            forceY = 5;
         else if(forceY < -5)
            forceY = -5; 

         x+= forceX;
         y+= forceY;    
         }
   
   public float getX(){return x;}
   public float getY(){return y;}
   public void setX(float x_){x = x_;}
   public void setY(float y_){y = y_;}
   
   public double distance(DrawableObject other)
   {
      return (Math.sqrt((other.x-x)*(other.x-x) +  (other.y-y)*(other.y-y)   ));
   }
}