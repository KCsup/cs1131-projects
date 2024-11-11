import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class TextCommand extends Command {

    private Pane root;
    
    public TextCommand(
        GraphicsInterpreter interpreter,
        String[] args,
        Pane root
    ) throws IllegalArgumentException {
        super(interpreter, args, 3);

        this.root = root;
    }

    @Override
    public void execute() {
        GraphicsInterpreter interpreter = getInterpreter();

        Text text = new Text(getDoubleArg(0), getDoubleArg(1), getArgsString(2));
        text.setFill(interpreter.getFill());
        text.setStroke(interpreter.getStroke());

        root.getChildren().add(text);
    }
    
}
