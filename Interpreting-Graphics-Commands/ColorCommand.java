import javafx.scene.paint.Color;

public class ColorCommand extends Command {

    private String command;
    
    public ColorCommand(
        GraphicsInterpreter interpreter,
        String[] args,
        String command
    ) throws IllegalArgumentException {
        super(interpreter, args, 4);

        this.command = command;
    }

    @Override
    public void execute() {
        switch(command) {
            case "FILL":
                getInterpreter().setFill(Color.rgb(
                    getIntegerArg(0),
                    getIntegerArg(1),
                    getIntegerArg(2),
                    getDoubleArg(3)
                ));
                break;
            case "STROKE":
                getInterpreter().setStroke(Color.rgb(
                    getIntegerArg(0),
                    getIntegerArg(1),
                    getIntegerArg(2),
                    getDoubleArg(3)
                ));
                break;
            default:
                break;
        }
    }
    
}
