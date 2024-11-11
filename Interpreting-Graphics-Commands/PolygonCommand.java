import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;

public class PolygonCommand extends Command {

    private Pane root;
    
    public PolygonCommand(
        GraphicsInterpreter interpreter,
        String[] args,
        Pane root
    ) throws IllegalArgumentException {
        super(interpreter, args, 4);

        this.root = root;
    }

    @Override
    public void execute() {
        GraphicsInterpreter interpreter = getInterpreter();
        
        Polygon polygon = new Polygon(getDoubleArgs());
        polygon.setFill(interpreter.getFill());
        polygon.setStroke(interpreter.getStroke());

        root.getChildren().add(polygon);
    }
}
