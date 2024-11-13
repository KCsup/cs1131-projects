import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class RectangleCommand extends Command {

    private Pane root;
    
    public RectangleCommand(
        GraphicsInterpreter interpreter,
        String[] args,
        Pane root
    ) throws IllegalArgumentException {
        super(interpreter, args, 4);

        this.root = root;
    }

    @Override
    public void execute() {
        double[] args = getDoubleArgs();
        GraphicsInterpreter interpreter = getInterpreter();
        
        Rectangle rectangle = new Rectangle(args[0], args[1], args[2], args[3]);
        rectangle.setFill(interpreter.getFill());
        rectangle.setStroke(interpreter.getStroke());

        root.getChildren().add(rectangle);
    }
}
