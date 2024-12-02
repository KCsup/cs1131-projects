/**
 * Week 11: Graphics Interpreter Program
 * This program parses graphics commands in a text file, and outputs
 * the commands as a PNG image.
 *
 * Date Last Modified: 11/15/2024
 *
 * @author Josh Fernandez
 *
 * CS1131, Fall 2024
 * Lab Section 3
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

import javax.imageio.ImageIO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.naming.NamingException;
import java.text.ParseException;

public class GraphicsInterpreter extends AbstractGraphicsInterpreter {

    private Color fill = Color.BLACK;
    private Color stroke = Color.BLACK;

    public void setFill(Color color) { fill = color; }
    public void setStroke(Color color) { stroke = color; }
    public Color getFill() { return fill; }
    public Color getStroke() { return stroke; }

    public Week9ExtraCredit extraCredit = new Week9ExtraCredit();
    
    @Override
    public WritableImage loadCommandFile(
        Stage stage,
        String filename
    ) throws FileNotFoundException, Exception {
        Pane root = (Pane) stage.getScene().getRoot();
        root.setBackground(new Background(new BackgroundFill(
            Color.WHITE, null, null
        )));
        

        List<String> lines = Files.readAllLines(Paths.get(filename));
        List<Command> commands = getCommands(lines, stage);


        for(int i = 0; i < commands.size(); i++) {
            Command command = commands.get(i);
            try {
                command.execute();
            }
            catch(NumberFormatException e) {
                throw new ParseException("Unable to parse arguments for " +
                    "command " + (i + 1) + ".", 0);
            }
        }

        WritableImage outImg = new WritableImage(
            (int) stage.getWidth(),
            (int) stage.getHeight()
        );
        stage.getScene().snapshot(outImg);

        return outImg;
    }

    public List<Command> getCommands(
        List<String> lines,
        Stage stage
    ) throws ParseException {
        List<Command> commands = new ArrayList<Command>();
        Pane root = (Pane) stage.getScene().getRoot();
        
        commandLines:
        for(int lineI = 0; lineI < lines.size(); lineI++) {
            String line = lines.get(lineI);
            String[] splitLine = line.split(" ");

            if(splitLine.length < 1) break;
            
            String command = splitLine[0];
            String[] args = new String[splitLine.length - 1];
            System.arraycopy(splitLine, 1, args, 0, args.length);
            
            try {
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
                    case "END":
                        break commandLines;
                    case "":
                    case "//":
                        break;
                    case "SET":
                        commands.add(new SetCommand(this, args));
                        break;
                    default:
                        throw new NamingException(command);
                }
            } catch(IllegalArgumentException e) {
                throw new ParseException("Invalid Arguments supplied" + 
                    " to command at line " + (lineI + 1) + ".", 0);
            } catch(NamingException e) {
                throw new ParseException("Invalid Command '" +
                    e.getExplanation() + "' at line " +
                    (lineI + 1) + " while parsing file.", 0);
            }
        }

        return commands;
    }

    @Override
    public void saveImageFile(
        WritableImage image,
        String filename
    ) throws FileNotFoundException, IOException {
        File outputFile = Paths.get(filename).toFile();
        ImageIO.write(SwingFXUtils.fromFXImage(
            image, null
        ), "png", outputFile);
        System.out.println("Image written to " + filename);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
