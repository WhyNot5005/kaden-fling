import javafx.animation.FillTransition;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Mine extends DrawableObject {
    private FillTransition fillTransition;

    public Mine(float x, float y) {
        super(x, y);

        Circle circle = new Circle(x, y, 14); // create a circle with the same center position as the mine
        fillTransition = new FillTransition(Duration.seconds(1), circle, Color.RED, Color.WHITE);
        fillTransition.setCycleCount(FillTransition.INDEFINITE);
        fillTransition.setAutoReverse(true);
        fillTransition.play();
    }

    @Override
    public void drawMe(float x, float y, GraphicsContext gc) {
        gc.save();

        //gc.setFill(fillTransition.getCurrentFill());
        gc.fillOval(x - 14, y - 14, 27, 27);

        gc.restore();
    }
    
    
}