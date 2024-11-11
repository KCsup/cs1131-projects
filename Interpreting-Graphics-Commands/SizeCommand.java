import javafx.stage.Stage;

public class SizeCommand extends Command {

    private Stage stage;
    
    public SizeCommand(
        GraphicsInterpreter interpreter,
        String[] args,
        Stage stage
    ) throws IllegalArgumentException {
        super(interpreter, args, 2);

        this.stage = stage;
    }
    
    @Override
    public void execute() {
        int[] args = getIntegerArgs();
        stage.setWidth(args[0]);
        stage.setHeight(args[1]);
    }
}
