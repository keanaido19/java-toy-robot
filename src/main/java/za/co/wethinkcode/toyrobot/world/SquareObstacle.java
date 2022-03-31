package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.Position;

/**
 * Square Obstacle Class
 */
public class SquareObstacle implements Obstacle{
    private final int size;
    private final Position bottomLeft;
    private final Position topLeft;
    private final Position bottomRight;

    /**
     * Instantiates a new Square obstacle.
     *
     * @param x The starting x coordinate of the square obstacle
     * @param y The starting y coordinate of the square obstacle
     */
    public SquareObstacle(int x, int y) {
        this.size = 5;
        this.bottomLeft = new Position(x, y);
        this.topLeft = new Position(x, y + getSize() - 1);
        this.bottomRight = new Position(x + getSize() - 1, y);
    }

    /**
     * Instantiates a new Square obstacle.
     *
     * @param x    The starting x coordinate of the square obstacle
     * @param y    The starting y coordinate of the square obstacle
     * @param size The size of the square obstacle
     */
    public SquareObstacle(int x, int y, int size) {
        this.size = size;
        this.bottomLeft = new Position(x, y);
        this.topLeft = new Position(x, y + getSize() - 1);
        this.bottomRight = new Position(x + getSize() - 1, y);
    }

    @Override
    public int getBottomLeftX() {
        return this.bottomLeft.getX();
    }

    @Override
    public int getBottomLeftY() {
        return this.bottomLeft.getY();
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean blocksPosition(Position position) {
        return position.isIn(this.topLeft, this.bottomRight);
    }

    @Override
    public boolean blocksPath(Position a, Position b) {
        int aX = a.getX();
        int aY = a.getY();
        int bX = b.getX();
        int bY = b.getY();
        if (aX == bX) {
            for (int i = (Math.min(aY, bY)); i <= Math.max(aY, bY); i++) {
                if (new Position(aX, i).isIn(this.topLeft, this.bottomRight)) {
                    return true;
                }
            }
        } else {
            for (int i = (Math.min(aX, bX)); i <= Math.max(aX, bX); i++) {
                if (new Position(i, aY).isIn(this.topLeft, this.bottomRight)) {
                    return true;
                }
            }
        }
        return false;
    }
}
