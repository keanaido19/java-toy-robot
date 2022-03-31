package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.world.SquareObstacle;

import java.util.List;

/**
 * Simple Maze Class
 */
public class SimpleMaze extends AbstractMaze{

    /**
     * Instantiates a new Simple maze.
     */
    public SimpleMaze() {
        super(List.of(new SquareObstacle(1, 1)));
    }
}
