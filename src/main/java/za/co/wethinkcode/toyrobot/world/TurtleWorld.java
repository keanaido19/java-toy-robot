package za.co.wethinkcode.toyrobot.world;

import org.turtle.StdDraw;
import za.co.wethinkcode.toyrobot.maze.Maze;

import java.awt.*;
import java.util.List;

/**
 * Turtle World Class
 */
public class TurtleWorld extends AbstractWorld{

    /**
     * Instantiates a new Turtle world.
     *
     * @param maze The given Maze
     */
    public TurtleWorld(Maze maze) {
        super(maze);
        setUpTurtleWorld();
    }

    /**
     * Draws the border for the world based on the world's constraints
     */
    public void drawBorder() {
        StdDraw.setPenColor(Color.RED);
        StdDraw.polygon(
                new double[] {getMinX(), getMinX(), getMaxX(), getMaxX()},
                new double[] {getMinY(), getMaxY(), getMaxY(), getMinY()});

    }

    /**
     * Sets up turtle world.
     */
    public void setUpTurtleWorld() {
        StdDraw.setScale(-256, 256);
        StdDraw.enableDoubleBuffering();
        drawBorder();
    }

    /**
     * Draws a square obstacle.
     *
     * @param x    The starting x coordinate of the obstacle
     * @param y    The starting y coordinate of the obstacle
     * @param size The size of the obstacle
     */
    public void drawSquareObstacle(int x, int y, int size) {
        StdDraw.setPenColor(Color.gray);
        double halfLength = Double.parseDouble(size + "") / 2;
        StdDraw.filledSquare(x + halfLength, y + halfLength, halfLength);
    }

    /**
     * Draws square obstacles.
     */
    public void drawSquareObstacles() {
        StdDraw.enableDoubleBuffering();
        List<Obstacle> obstacles = getObstacles();
        if (!obstacles.isEmpty()) {
            for (Obstacle obstacle : obstacles) {
                int size = obstacle.getSize();
                int obstacleX = obstacle.getBottomLeftX();
                int obstacleY = obstacle.getBottomLeftY();
                drawSquareObstacle(obstacleX, obstacleY, size);
            }
        }
    }

    /**
     * Draws the turtle shape which represents the robot - in the correct
     * orientation given the direction the robot is facing
     */
    public void drawTurtle() {
        StdDraw.setPenColor(Color.blue);
        int positionX = getPosition().getX();
        int positionY = getPosition().getY();
        double[] x = new double[] {};
        double[] y = new double[] {};
        switch (getCurrentDirection()) {
            case UP:
                x = new double[] {positionX - 5, positionX, positionX + 5};
                y = new double[] {positionY - 7, positionY, positionY - 7};
                break;
            case RIGHT:
                x = new double[] {positionX - 7, positionX - 7, positionX};
                y = new double[] {positionY + 5, positionY - 5, positionY};
                break;
            case DOWN:
                x = new double[] {positionX - 5, positionX, positionX + 5};
                y = new double[] {positionY + 7, positionY, positionY + 7};
                break;
            case LEFT:
                x = new double[] {positionX, positionX + 7, positionX + 7};
                y = new double[] {positionY, positionY - 5, positionY + 5};
                break;
        }
        StdDraw.filledPolygon(x, y);
    }

    /**
     * Draws the turtle screen
     */
    private void drawTurtleScreen() {
        StdDraw.clear();
        drawBorder();
        drawSquareObstacles();
        drawTurtle();
        StdDraw.show();
    }

    @Override
    public UpdateResponse updatePosition(int nrSteps) {
        UpdateResponse response = super.updatePosition(nrSteps);
        drawTurtleScreen();
        return response;
    }

    @Override
    public void updateDirection(boolean turnRight) {
        super.updateDirection(turnRight);
        drawTurtleScreen();
    }

    @Override
    public void reset() {
        super.reset();
        drawTurtleScreen();
    }

    @Override
    public void showObstacles() {
        List<Obstacle> obstacles = getObstacles();
        if (!obstacles.isEmpty()) {
            System.out.println("There are some obstacles:");
            for (Obstacle obstacle : obstacles) {
                int size = obstacle.getSize();
                int obstacleX = obstacle.getBottomLeftX();
                int obstacleY = obstacle.getBottomLeftY();
                System.out.println(
                        " - At " + obstacleX + ", " + obstacleY + " to (" +
                                (obstacleX + size - 1) + ", " +
                                (obstacleY + size - 1) + ")");
                drawSquareObstacle(obstacleX, obstacleY, size);
            }
        }
        drawTurtle();
        StdDraw.show();
    }

    @Override
    public void destroyWorld() {
        System.exit(0);
    }
}
