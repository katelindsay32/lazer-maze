import org.junit.Before;
import org.junit.Test;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class MazeBuilderTest {

    MazeBuilder mazeBuilder;
    FileReader fileReader;

    @Before
    public void Setup() {
        fileReader = mock(FileReader.class);
        mazeBuilder = new MazeBuilder(fileReader);
    }

    @Test
    public void buildMazeFromDimensions() {

        ArrayList<String> mazeArguments = createArguments("1,1");
        Room[][] maze = mazeBuilder.build(mazeArguments);
        assertEquals(1, maze.length);
        assertEquals(1, maze[0].length);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateDimensionsAreNumbers() {
        ArrayList<String> mazeArguments = createArguments("-asdf");
        Room[][] maze = mazeBuilder.build(mazeArguments);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateThereAreTwoDimensions() {
        ArrayList<String> mazeArguments = createArguments("-3,3,3");
        Room[][] maze = mazeBuilder.build(mazeArguments);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validatexDimensionGreaterThanZero() {
        ArrayList<String> mazeArguments = createArguments("1,0");
        Room[][] maze = mazeBuilder.build(mazeArguments);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateyDimensionIsGreaterThanZero() {
        ArrayList<String> mazeArguments = createArguments("0,1");
        Room[][] maze = mazeBuilder.build(mazeArguments);
    }

    private ArrayList<String> createArguments(String dimensions) {
        return new ArrayList<String>(Arrays.asList(
                dimensions,
                "-1"));
    }

}
