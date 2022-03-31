package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.world.Obstacle;
import za.co.wethinkcode.toyrobot.world.SquareObstacle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Random Maze Class
 */
public class RandomMaze extends AbstractMaze{
    private static final Random random = new Random();

    /**
     * Instantiates a new Random maze.
     */
    public RandomMaze() {
        super(generateObstacles());
    }

    /**
     * Generates a list of random obstacles
     * @return A list of obstacles
     */
    private static List<Obstacle> generateObstacles() {
        List<Obstacle> obstacles = new ArrayList<>();
        int randomObstacleCount = random.nextInt(19) + 1;
        for (int i = 0; i < randomObstacleCount; i++) {
            int[] randomCoordinates = getRandomCoordinates(obstacles);
            if (Arrays.equals(randomCoordinates, new int[] {0, 0})) {
                continue;
            }
            int randomX = randomCoordinates[0];
            int randomY = randomCoordinates[1];
            obstacles.add(new SquareObstacle(randomX, randomY));
        }
        return obstacles;
    }

    /**
     * Generates a random x coordinate between -101 and 101
     * @return A random x coordinate between -101 and 101
     */
    private static int getRandomXCoordinate() {
        return random.nextInt(196) - 100;
    }

    /**
     * Generates a random y coordinate between -201 and 201
     * @return A random y coordinate between -201 and 201
     */
    private static int getRandomYCoordinate() {
        return random.nextInt(396) - 200;
    }

    /**
     * Checks if the given starting x coordinate of the obstacle will result
     * in the full obstacle to overlap the origin
     *
     * @param x Starting x coordinate of the obstacle
     * @param size Size of the obstacle
     * @return 'true' if the given starting x coordinate of the obstacle
     * results in the full obstacle to overlap the origin
     */
    private static boolean checkXCoordinateOverlapOrigin(int x, int size) {
        return -(size - 1) <= x && x <= 0;
    }

    /**
     * Checks if the given starting y coordinate of the obstacle will result
     * in the full obstacle to overlap the origin
     *
     * @param y Starting y coordinate of the obstacle
     * @param size Size of the obstacle
     * @return 'true' if the given starting y coordinate of the obstacle
     * results in the full obstacle to overlap the origin
     */
    private static boolean checkYCoordinateOverlapOrigin(int y, int size) {
        return -(size - 1) <= y && y <= 0;
    }

    /**
     * Checks if the given starting x and starting y coordinate of the obstacle
     * will result in the full obstacle to overlap the origin
     *
     * @param x Starting x coordinate of the obstacle
     * @param y Starting y coordinate of the obstacle
     * @param size Size of the obstacle
     * @return 'true' if the given starting x and starting y coordinate of the
     * obstacle results in the full obstacle to overlap the origin
     */
    private static boolean checkOverlapOrigin(int x, int y, int size) {
        return checkXCoordinateOverlapOrigin(x, size) &&
                checkYCoordinateOverlapOrigin(y, size);
    }

    /**
     * Checks if the given starting x coordinate of the new obstacle will result
     * in the full obstacle to overlap the given obstacle
     *
     * @param x Starting x coordinate of the obstacle
     * @param obstacle An obstacle
     * @return 'true' if the given starting x coordinate of the new obstacle
     * results in the full obstacle to overlap the given obstacle
     */
    private static boolean checkXCoordinateOverlap(int x, Obstacle obstacle) {
        int size = obstacle.getSize();
        return obstacle.getBottomLeftX() - (size - 1) <= x &&
                x <= obstacle.getBottomLeftX() + (size - 1);
    }

    /**
     * Checks if the given starting y coordinate of the new obstacle will result
     * in the full obstacle to overlap the given obstacle
     *
     * @param y Starting y coordinate of the obstacle
     * @param obstacle An obstacle
     * @return 'true' if the given starting y coordinate of the new obstacle
     * results in the full obstacle to overlap the given obstacle
     */
    private static boolean checkYCoordinateOverlap(int y, Obstacle obstacle) {
        int size = obstacle.getSize();
        return obstacle.getBottomLeftY() - (size - 1) <= y &&
                y <= obstacle.getBottomLeftY() + (size - 1);
    }

    /**
     * Checks if the given starting x and starting y coordinate of the new
     * obstacle will result in the full obstacle to overlap the given obstacle
     *
     * @param x Starting x coordinate of the obstacle
     * @param y Starting y coordinate of the obstacle
     * @param obstacle An obstacle
     * @return 'true' if the given starting x and starting y coordinate of the
     * new obstacle results in the full obstacle to overlap the given obstacle
     */
    private static boolean checkCoordinateOverlap(int x, int y,
                                                  Obstacle obstacle) {
        return checkXCoordinateOverlap(x, obstacle) &&
                checkYCoordinateOverlap(y, obstacle);
    }

    /**
     * Checks if the given x and y coordinate will create an obstacle that
     * does not overlap the origin or another obstacle
     *
     * @param x The given x coordinate which will be the starting point of
     *          the new obstacle
     * @param y The given y coordinate which will be the starting point of
     *          the new obstacle
     * @param obstacles A list of obstacles
     * @return 'true' if the given x and y coordinate will create an obstacle
     * that will overlap the origin or another obstacle
     */
    private static boolean isInvalidCoordinates(int x, int y,
                                                List<Obstacle> obstacles) {
        for (Obstacle obstacle : obstacles) {
            int size = obstacle.getSize();
            if (checkOverlapOrigin(x, y, size) ||
                    checkCoordinateOverlap(x, y,obstacle)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Generates an x and y coordinate that will create a new obstacle in a
     * valid location
     *
     * @param obstacles A list of obstacles
     * @return x and y coordinate that will create a new obstacle in a
     */
    private static int[] getRandomCoordinates(List<Obstacle> obstacles) {
        int counter = 0;
        while (true) {
            if (counter == 10) {
                return new int[] {0, 0};
            }
            int randomX = getRandomXCoordinate();
            int randomY = getRandomYCoordinate();
            if (!isInvalidCoordinates(randomX, randomY, obstacles)) {
                return new int[] {randomX, randomY};
            }
            counter++;
        }
    }
}
