import MirrorAngle.MirrorAngle;
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
        ArrayList<String> mazeArguments = createArgsWithDimensions("1,1");
        Room[][] maze = mazeBuilder.build(mazeArguments);
        assertEquals(1, maze.length);
        assertEquals(1, maze[0].length);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateDimensionsAreNumbers() {
        ArrayList<String> mazeArguments = createArgsWithDimensions("-asdf");
        Room[][] maze = mazeBuilder.build(mazeArguments);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateThereAreTwoDimensions() {
        ArrayList<String> mazeArguments = createArgsWithDimensions("-3,3,3");
        Room[][] maze = mazeBuilder.build(mazeArguments);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validatexDimensionGreaterThanZero() {
        ArrayList<String> mazeArguments = createArgsWithDimensions("1,0");
        Room[][] maze = mazeBuilder.build(mazeArguments);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateyDimensionIsGreaterThanZero() {
        ArrayList<String> mazeArguments = createArgsWithDimensions("0,1");
        Room[][] maze = mazeBuilder.build(mazeArguments);
    }

    @Test(expected = IllegalArgumentException.class)
    public void dimensionsShouldBeFollowedByDelimiter() {
        ArrayList<String> badArgs = new ArrayList<String>(Arrays.asList(
                "1,1",
                "-notdash"));
        Room[][] maze = mazeBuilder.build(badArgs);
    }

    private ArrayList<String> createArgsWithDimensions(String dimensions) {
        return new ArrayList<String>(Arrays.asList(
                dimensions,
                MazeBuilder.FILE_DELIMITER));
    }

    @Test
    public void oneMirrorGoesInCorrectLocation() {
        ArrayList<String> mazeArguments = createArgsWithDimensionsAndMirrors("5,5", new ArrayList<String>(Arrays.asList("1,1")));
        Room[][] maze = mazeBuilder.build(mazeArguments);
        Room room = maze[1][1];
        assertEquals(MirrorAngle.Right, room.getMirrorAngle());
    }

    private ArrayList<String> createArgsWithDimensionsAndMirrors(String dimensions, ArrayList<String> mirrorLocs) {
        ArrayList list = createArgsWithDimensions(dimensions);
        list.add(mirrorLocs);
        return list;
    }
}
