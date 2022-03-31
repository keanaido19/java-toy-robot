package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.maze.Maze;

import java.util.List;

/**
 * Abstract World Class
 */
public abstract class AbstractWorld implements IWorld{
    private final Position TOP_LEFT = new Position(-100, 200);
    private final Position BOTTOM_RIGHT = new Position(100, -200);
    private final Maze maze;

    private Position position;
    private Direction currentDirection;

    /**
     * Instantiates a new Abstract world.
     *
     * @param maze A given Maze
     */
    public AbstractWorld(Maze maze) {
        this.maze = maze;
        this.position = CENTRE;
        this.currentDirection = Direction.UP;
    }

    /**
     * Gets the minimum x coordinate of the world
     *
     * @return The minimum x coordinate of the world
     */
    public int getMinX() {
        return this.TOP_LEFT.getX();
    }

    /**
     * Gets the minimum y coordinate of the world
     *
     * @return The minimum y coordinate of the world
     */
    public int getMinY() {
        return this.BOTTOM_RIGHT.getY();
    }

    /**
     * Gets the maximum x coordinate of the world
     *
     * @return The maximum x coordinate of the world
     */
    public int getMaxX() {
        return this.BOTTOM_RIGHT.getX();
    }

    /**
     * Gets the maximum y coordinate of the world
     *
     * @return The maximum y coordinate of the world
     */
    public int getMaxY() {
        return this.TOP_LEFT.getY();
    }

    @Override
    public UpdateResponse updatePosition(int nrSteps) {
        int newY = this.position.getY();
        int newX = this.position.getX();

        switch (currentDirection) {
            case UP:
                newY = newY + nrSteps;
                break;
            case DOWN:
                newY = newY - nrSteps;
                break;
            case RIGHT:
                newX = newX + nrSteps;
                break;
            case LEFT:
                newX = newX - nrSteps;
        }

        Position newPosition = new Position(newX, newY);

        if (!this.maze.blocksPath(this.position, newPosition)) {
            if (isNewPositionAllowed(newPosition)) {
                this.position = newPosition;
                return UpdateResponse.SUCCESS;
            }
            return UpdateResponse.FAILED_OUTSIDE_WORLD;
        }
        return UpdateResponse.FAILED_OBSTRUCTED;
    }

    @Override
    public void updateDirection(boolean turnRight) {
        switch (getCurrentDirection()) {
            case UP:
                this.currentDirection =
                        turnRight ? Direction.RIGHT: Direction.LEFT;
                break;
            case RIGHT:
                this.currentDirection =
                        turnRight ? Direction.DOWN: Direction.UP;
                break;
            case DOWN:
                this.currentDirection =
                        turnRight ? Direction.LEFT: Direction.RIGHT;
                break;
            case LEFT:
                this.currentDirection =
                        turnRight ? Direction.UP: Direction.DOWN;
                break;
        }
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public Direction getCurrentDirection() {
        return this.currentDirection;
    }

    /**
     * Checks if the given position is allowed in the world
     *
     * @param position The given position
     * @return 'true' if the given position is allowed in the world
     */
    public boolean checkPositionAllowed(Position position) {
        return isNewPositionAllowed(position) &&
                !this.maze.blocksPosition(position);
    }

    @Override
    public boolean isNewPositionAllowed(Position position) {
        return position.isIn(TOP_LEFT,BOTTOM_RIGHT);
    }

    @Override
    public boolean isAtEdge() {
        int positionX = getPosition().getX();
        int positionY = getPosition().getY();
        return (positionX == TOP_LEFT.getX()) ||
                (positionX == BOTTOM_RIGHT.getX()) ||
                (positionY == TOP_LEFT.getY()) ||
                (positionY == BOTTOM_RIGHT.getY());
    }

    @Override
    public void reset() {
        this.currentDirection = Direction.UP;
        this.position = CENTRE;
    }

    @Override
    public List<Obstacle> getObstacles() {
        return this.maze.getObstacles();
    }

    @Override
    public void showObstacles() {
        List<Obstacle> obstacles = getObstacles();
        if (!obstacles.isEmpty()) {
            System.out.println("There are some obstacles:");
            for (Obstacle obstacle : obstacles) {
                int obstacleX = obstacle.getBottomLeftX();
                int obstacleY = obstacle.getBottomLeftY();
                System.out.println(
                        " - At " + obstacleX + ", " + obstacleY + " to (" +
                                (obstacleX + obstacle.getSize() - 1) + ", " +
                                (obstacleY + obstacle.getSize() - 1) + ")");
            }
        }
    }

    /**
     * Destroys the world!!!
     */
    public void destroyWorld() {}
}
