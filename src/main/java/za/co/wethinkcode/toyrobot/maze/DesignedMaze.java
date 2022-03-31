package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.world.Obstacle;
import za.co.wethinkcode.toyrobot.world.SquareObstacle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Designed Maze Class
 */
public class DesignedMaze extends AbstractMaze{

    /**
     * Instantiates a new Designed maze.
     */
    public DesignedMaze() {
        super(addObstaclesFromFile());
    }

    /**
     * Reads a text file consisting of x and y coordinates on each line, the
     * x and y coordinates are then used to create obstacles and are added to
     * a list
     *
     * @return A list of obstacles which creates the designed maze
     */
    private static List<Obstacle> addObstaclesFromFile() {
        List<Obstacle> obstacles = new ArrayList<>() {};
        try {
            String fileName = "src/main/java/za/co/wethinkcode/toyrobot/maze/" +
                    "DesignedMaze.txt";
            List<String> coordinates;
            coordinates = Files.readAllLines(Path.of(fileName));
            for (String coordinate : coordinates) {
                String[] xyCoordinates = coordinate.split(",");
                int x = Integer.parseInt(xyCoordinates[0]);
                int y = Integer.parseInt(xyCoordinates[1]);
                obstacles.add(new SquareObstacle(x, y, 3));
            }

        } catch (IOException e) {
            System.out.println("DesignedMaze failed to load.");
        }

        return obstacles;
    }
}
