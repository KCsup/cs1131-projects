public class SetCommand extends Command {

    public SetCommand(
        GraphicsInterpreter interpreter,
        String[] args
    ) throws IllegalArgumentException {
        super(interpreter, args, 2);

        
        String value = "";
        for(int i = 1; i < args.length; i++)
            value += args[i] + " ";

        getInterpreter().extraCredit.addVariable(args[0], value.trim());
    }

   
    @Override
    public void execute() { }
}
