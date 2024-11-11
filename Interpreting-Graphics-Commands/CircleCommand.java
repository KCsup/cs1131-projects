import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class CircleCommand extends Command {

    private Pane root;
    
    public CircleCommand(
        GraphicsInterpreter interpreter,
        String[] args,
        Pane root
    ) throws IllegalArgumentException {
        super(interpreter, args, 3);

        this.root = root;
    }

    @Override
    public void execute() {
        int[] args = getIntegerArgs();
        GraphicsInterpreter interpreter = getInterpreter();
        
        Circle circle = new Circle(args[0], args[1], args[2]);
        circle.setFill(interpreter.getFill());
        circle.setStroke(interpreter.getStroke());

        root.getChildren().add(circle);
    }
}
