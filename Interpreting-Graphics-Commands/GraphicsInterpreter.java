import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.Group;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GraphicsInterpreter extends AbstractGraphicsInterpreter {

    private Color fill = Color.BLACK;
    private Color stroke = Color.BLACK;

    public void setFill(Color color) { fill = color; }
    public void setStroke(Color color) { stroke = color; }
    public Color getFill() { return fill; }
    public Color getStroke() { return stroke; }
    
    @Override
    public WritableImage loadCommandFile(
        Stage stage,
        String filename
    ) throws FileNotFoundException, IOException {
        Pane root = (Pane) stage.getScene().getRoot();
        List<Command> commands = new ArrayList<Command>();

        Files.lines(Paths.get(filename)).forEach(line -> {
            String[] splitLine = line.split(" ");

            if(splitLine.length < 1) return;
            
            String command = splitLine[0];
            String[] args = new String[splitLine.length - 1];
            System.arraycopy(splitLine, 1, args, 0, args.length);
            
            switch(command) {
                case "SIZE":
                    commands.add(new SizeCommand(this, args, stage));
                    break;
                case "LINE":
                    commands.add(new LineCommand(this, args, root));
                    break;
                case "CIRCLE":
                    commands.add(new CircleCommand(this, args, root));
                    break;
                case "RECTANGLE":
                    commands.add(new RectangleCommand(this, args, root));
                    break;
                case "POLYGON":
                    commands.add(new PolygonCommand(this, args, root));
                    break;
                case "TEXT":
                    commands.add(new TextCommand(this, args, root));
                    break;
                case "FILL":
                case "STROKE":
                    commands.add(new ColorCommand(this, args, command));
                    break;
                default:
                    break;
            }
        });

        for(Command command : commands) command.execute();
        
        return null;
    }

    @Override
    public void saveImageFile(
        WritableImage image,
        String filename
    ) throws FileNotFoundException, IOException {
        
    }

    public static void main(String[] args) {
        // List<Command> commands = new ArrayList<Command>() {{
        //     add(new SizeCommand(new String[]{"test", "arg2"}));
        // }};

        
        // for(Command c : commands) c.execute();

        launch(args);
    }
}
