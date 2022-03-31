package za.co.wethinkcode.toyrobot;

import org.jetbrains.annotations.NotNull;

/**
 * Node Class which is used for the A* algorithm to solve a maze
 */
public class Node implements Comparable<Node> {
    private final Position position;
    private final Node parent;
    private final double cost;
    private final double heuristic;

    /**
     * Instantiates a new Node
     *
     * @param position A given position
     * @param parent The previous Node which leads directly to the current Node
     * @param cost The cost of the Node
     * @param heuristic The heuristic value of the Node
     */
    public Node(Position position, Node parent, double cost, double heuristic) {
        this.position = position;
        this.parent = parent;
        this.cost = cost;
        this.heuristic = heuristic;
    }

    /**
     * Instantiates a new Node
     *
     * @param position A given position
     * @param cost The cost of the Node
     * @param heuristic The heuristic value of the Node
     */
    public Node(Position position, double cost, double heuristic) {
        this.position = position;
        this.parent = null;
        this.cost = cost;
        this.heuristic = heuristic;
    }

    /**
     * @return The previous Node which leads directly to the current Node
     */
    public Node getParent() {
        return this.parent;
    }

    /**
     * @return The current position of the Node
     */
    public Position getPosition() {
        return this.position;
    }

    /**
     * @return The cost of the Node
     */
    public double getCost() {
        return this.cost;
    }

    /**
     * @return The total cost of the Node which is the Node's cost plus it's
     * heuristic value
     */
    public double getTotalCost() {
        return this.heuristic + this.cost;
    }

    @Override
    public int compareTo(@NotNull Node o) {
        if (this.getTotalCost() > o.getTotalCost()) {
            return 1;
        }
        return -1;
    }
}
