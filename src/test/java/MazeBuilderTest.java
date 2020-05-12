import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

        ArrayList<String> mazeArguments = new ArrayList<String>(Arrays.asList(
                "1,1",
                "-1"));
        Room[][] maze = mazeBuilder.build(mazeArguments);
        assertEquals(1, maze.length);
        assertEquals(1, maze[0].length);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateDimensionsAreNumbers() {
        ArrayList<String> mazeArguments = new ArrayList<String>(Arrays.asList(
                "-asdf",
                "-1"));
        Room[][] maze = mazeBuilder.build(mazeArguments);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateThereAreTwoDimensions() {
        ArrayList<String> mazeArguments = new ArrayList<String>(Arrays.asList(
                "-3,3,3",
                "-1"));
        Room[][] maze = mazeBuilder.build(mazeArguments);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validatexDimensionGreaterThanZero() {
        ArrayList<String> mazeArguments = new ArrayList<String>(Arrays.asList(
                "1,0",
                "-1"));
        Room[][] maze = mazeBuilder.build(mazeArguments);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateyDimensionIsGreaterThanZero() {
        ArrayList<String> mazeArguments = new ArrayList<String>(Arrays.asList(
                "0,1",
                "-1"));
        Room[][] maze = mazeBuilder.build(mazeArguments);
    }

}
