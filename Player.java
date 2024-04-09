import javafx.scene.paint.*;
import javafx.scene.canvas.*;


public class Player extends DrawableObject
{

   private double xSpot;
   private double ySpot;

	//takes in its position
   public Player(float x, float y)
   {
      super(x,y);
      xSpot = x;
      ySpot = y;
   }
   //draws itself at the passed in x and y.
   public void drawMe(float x, float y, GraphicsContext gc)
   {
      gc.setFill(Color.BLACK);
      gc.fillOval(x-14,y-14,27,27);
      gc.setFill(Color.GREY);
      gc.fillOval(x-13,y-13,25,25);
   }
   
   
}