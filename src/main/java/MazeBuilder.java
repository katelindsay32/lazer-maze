import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class MazeBuilder {

    private FileReader fileReader;
    public static String FILE_DELIMITER = "--";

    public MazeBuilder(FileReader fileReader) {

        this.fileReader = fileReader;
    }

    public Room[][] build(ArrayList<String> arguments) {
        Room[][] maze = createMaze(arguments);
        
        return maze;
    }

    private Room[][] createMaze(ArrayList<String> arguments) {
        String dimensions = arguments.get(0);
        String[] dimensionArray = dimensions.split(",");

        if (dimensionArray.length != 2) {
            throw new IllegalArgumentException("Should be provided with two dimensions.");
        }

        int x = Integer.parseInt(dimensionArray[0]);
        int y = Integer.parseInt(dimensionArray[1]);

        if (x <= 0 || y <= 0) {
            throw new IllegalArgumentException("Dimensions should be greater than zero.");
        }

        String delimiter = arguments.get(1);
        if (delimiter != FILE_DELIMITER) {
            throw new IllegalArgumentException("Should be provided with two dimensions.");
        }

        return new Room[x][y];
    }

}
