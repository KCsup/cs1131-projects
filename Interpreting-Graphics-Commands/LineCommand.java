import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class LineCommand extends Command {

    private Pane root;
    
    public LineCommand(
        GraphicsInterpreter interpreter,
        String[] args,
        Pane root
    ) throws IllegalArgumentException {
        super(interpreter, args, 4);

        this.root = root;
    }

    @Override
    public void execute() {
        int[] args = getIntegerArgs();
        GraphicsInterpreter interpreter = getInterpreter();
        
        Line line = new Line(args[0], args[1], args[2], args[3]);
        line.setFill(interpreter.getFill());
        line.setStroke(interpreter.getStroke());

        root.getChildren().add(line);
    }
}
