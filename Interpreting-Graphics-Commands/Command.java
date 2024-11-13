public abstract class Command {
    
    public abstract void execute();

    private GraphicsInterpreter interpreter;
    private String[] args;
    private int minArgs;
    
    public Command(
        GraphicsInterpreter interpreter,
        String[] args,
        int minArgs
    ) throws IllegalArgumentException {
        if(args.length < minArgs) throw new IllegalArgumentException();

        this.interpreter = interpreter;
        this.args = args;
        this.minArgs = minArgs;
    }

    public GraphicsInterpreter getInterpreter() { return interpreter; }

    public String[] getArgs() { return args; }

    public String getArgsString(int startIndex) {
        String out = "";

        for(int i = startIndex; i < args.length; i++)
            out  += args[i] + " ";

        return out.trim();
    }

    public int getIntegerArg(int index) {
        return Integer.valueOf(args[index]);
    }
    
    public double getDoubleArg(int index) {
        return Double.valueOf(args[index]);
    }

    public double[] getDoubleArgs() {
        double[] out = new double[args.length];

        for(int i = 0; i < args.length; i++)
            out[i] = getDoubleArg(i);

        return out;
    }

}
