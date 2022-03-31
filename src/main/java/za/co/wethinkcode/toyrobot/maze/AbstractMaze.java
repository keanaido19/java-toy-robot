package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.world.Obstacle;

import java.util.List;

/**
 * Abstract Maze Class
 */
public abstract class AbstractMaze implements Maze{
    private final List<Obstacle> obstacles;

    /**
     * Instantiates a new Abstract maze.
     *
     * @param obstacles A list of obstacles
     */
    public AbstractMaze(List<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    @Override
    public List<Obstacle> getObstacles() {
        return this.obstacles;
    }

    @Override
    public boolean blocksPath(Position a, Position b) {
        for (Obstacle obstacle : getObstacles()) {
            if (obstacle.blocksPath(a, b)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean blocksPosition(Position position) {
        for (Obstacle obstacle : getObstacles()) {
            if (obstacle.blocksPosition(position)) {
                return true;
            }
        }
        return false;
    }
}
