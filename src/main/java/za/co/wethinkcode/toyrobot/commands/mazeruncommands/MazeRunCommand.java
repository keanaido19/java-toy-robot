package za.co.wethinkcode.toyrobot.commands.mazeruncommands;

import za.co.wethinkcode.toyrobot.Direction;
import za.co.wethinkcode.toyrobot.Node;
import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.Robot;
import za.co.wethinkcode.toyrobot.commands.Command;
import za.co.wethinkcode.toyrobot.commands.NullCommand;
import za.co.wethinkcode.toyrobot.commands.movementcommands.BackCommand;
import za.co.wethinkcode.toyrobot.commands.movementcommands.ForwardCommand;
import za.co.wethinkcode.toyrobot.commands.movementcommands.LeftCommand;
import za.co.wethinkcode.toyrobot.commands.movementcommands.RightCommand;
import za.co.wethinkcode.toyrobot.maze.MazeRunner;
import za.co.wethinkcode.toyrobot.world.IWorld;

import java.util.*;

/**
 * Maze Run Command Class
 */
public abstract class MazeRunCommand extends Command implements MazeRunner {
    private final ArrayList<Position> path = new ArrayList<>();
    private ArrayList<Command> commands = new ArrayList<>();
    private final List<String> robotStatus = new ArrayList<>();
    private int cost = 0;
    private Robot target;

    /**
     * Instantiates a new Maze run command.
     *
     * @param argument The command argument
     */
    public MazeRunCommand(String argument) {
        super("mazerun", argument);
    }

    /**
     * @return The privately stored ArrayList of commands
     */
    public ArrayList<Command> getCommandArrayList() {
        return this.commands;
    }

    /**
     * Adds a status message to the robot output status
     *
     * @param status A robot status message
     */
    public void addToRobotStatus(String status) {
        this.robotStatus.add(status);
    }

    /**
     * @return The output robot status of the robot
     */
    public List<String> getRobotStatus() {
        return this.robotStatus;
    }

    /**
     * Gets the heuristic value of the Node based on its current position
     * the edge the robot wants to maze run to
     *
     * @param currentPosition The current position
     * @param edgeDirection   The edge the robot wants to maze run to
     * @return The heuristic value of the Node
     */
    public double getHeuristic(Position currentPosition, IWorld.Direction
            edgeDirection) {
        switch (edgeDirection) {
            case UP:
                return target.getWorld().getMaxY() - currentPosition.getY();
            case RIGHT:
                return target.getWorld().getMaxX() - currentPosition.getX();
            case LEFT:
                return Math.abs(target.getWorld().getMinX()) +
                        currentPosition.getX();
            case DOWN:
            default:
                return Math.abs(target.getWorld().getMinY()) +
                        currentPosition.getY();
        }
    }

    /**
     * Checks if the robot has completed the maze run
     *
     * @param position      The given position
     * @param edgeDirection The edge the robot wants to maze run to
     * @return 'true' if the robot is at the edge it wants to be at
     */
    public boolean checkCompleted(Position position,
                                  IWorld.Direction edgeDirection) {
        switch (edgeDirection) {
            case UP:
                return position.getY() == target.getWorld().getMaxY();
            case RIGHT:
                return position.getX() == target.getWorld().getMaxX();
            case LEFT:
                return position.getX() == target.getWorld().getMinX();
            case DOWN:
            default:
                return position.getY() == target.getWorld().getMinY();
        }
    }

    @Override
    public abstract boolean execute(Robot target);

    @Override
    public abstract Command clone();

    /**
     * Adds a given (adjacent) position to a list of (adjacent) positions
     * @param newPosition The given (adjacent) position
     * @param adjacentPositions A list of (adjacent) positions
     */
    private void addAdjacentPosition(
            Position newPosition,
            ArrayList<Position> adjacentPositions) {
        if (target.getWorld().checkPositionAllowed(newPosition)) {
            adjacentPositions.add(newPosition);
        }
    }

    /**
     * @param currentPosition A given current position
     * @return A list of adjacent positions to the given position
     */
    private ArrayList<Position> getAdjacentPositions(Position currentPosition) {
        ArrayList<Position> adjacentPositions = new ArrayList<>();
        Position newPosition;
        int x = currentPosition.getX();
        int y = currentPosition.getY();

        for (int adjacentX : new int[] {x - 1, x + 1}) {
            newPosition = new Position(adjacentX, y);
            addAdjacentPosition(newPosition, adjacentPositions);
        }

        for (int adjacentY : new int[] {y - 1, y + 1}) {
            newPosition = new Position(x, adjacentY);
            addAdjacentPosition(newPosition, adjacentPositions);
        }

        return adjacentPositions;
    }

    /**
     * Processes a Node to get a path of positions from the current Node to
     * its Parent Nodes
     * @param node A given Node
     */
    private void nodeToPath(Node node) {
        Node currentNode = node;
        ArrayList<Position> reversedPath =
                new ArrayList<>(List.of(currentNode.getPosition()));
        while (currentNode.getParent() != null) {
            currentNode = currentNode.getParent();
            reversedPath.add(currentNode.getPosition());
        }

        Collections.reverse(reversedPath);
        path.addAll(reversedPath);
    }

    /**
     * @return A copied list of commands
     */
    private ArrayList<Command> copyCommands() {
        ArrayList<Command> commandCopy = new ArrayList<>();
        for (Command command : commands) {
            commandCopy.add(command.clone());
        }
        return commandCopy;
    }

    /**
     * Simplifies a list of commands by combining consecutive forward/back
     * commands
     */
    private void simplifyRobotCommandSequence() {
        ArrayList<Command> simplifiedCommandSequence = new ArrayList<>();
        ArrayList<Command> commandSequenceCopy = copyCommands();
        commandSequenceCopy.add(new NullCommand("end"));
        int startIndex;

        for (int i = 0; i < commandSequenceCopy.size(); i++) {
            if (commandSequenceCopy.get(i) instanceof ForwardCommand) {
                startIndex = i;
                while (commandSequenceCopy.get(i) instanceof ForwardCommand) {
                    i++;
                }
                simplifiedCommandSequence.add(
                        new ForwardCommand(String.valueOf(i - startIndex)));
                i--;
            } else if (commandSequenceCopy.get(i) instanceof BackCommand) {
                startIndex = i;
                while (commandSequenceCopy.get(i) instanceof BackCommand) {
                    i++;
                }
                simplifiedCommandSequence.add(
                        new BackCommand(String.valueOf(i - startIndex)));
                i--;
            } else if (commandSequenceCopy.get(i) instanceof RightCommand ||
                    commandSequenceCopy.get(i) instanceof LeftCommand) {
                simplifiedCommandSequence.add(commandSequenceCopy.get(i));
            }
        }
        commands = simplifiedCommandSequence;
        this.cost = simplifiedCommandSequence.size();
    }

    /**
     * Sets the command sequence the robot will need to execute in order to
     * do the maze run based on the path it gets from the given Node
     * @param node A given Node which represents the goal position
     */
    private void setRobotCommandSequence(Node node) {
        nodeToPath(node);
        Direction currentDirection = target.getCurrentDirection();
        for (int i = 0; i < path.size() - 1; i++) {
            Position currentPosition = path.get(i);
            Position nextPosition = path.get(i + 1);
            switch (currentDirection) {
                case NORTH:
                    if (currentPosition.getX() < nextPosition.getX()) {
                        commands.add(new RightCommand());
                        commands.add(new ForwardCommand("1"));
                        currentDirection = Direction.EAST;
                    } else if (currentPosition.getX() > nextPosition.getX()) {
                        commands.add(new LeftCommand());
                        commands.add(new ForwardCommand("1"));
                        currentDirection = Direction.WEST;
                    } else if (currentPosition.getY() > nextPosition.getY()) {
                        commands.add(new BackCommand("1"));
                    } else {
                        commands.add(new ForwardCommand("1"));
                    }
                    break;
                case SOUTH:
                    if (currentPosition.getX() < nextPosition.getX()) {
                        commands.add(new LeftCommand());
                        commands.add(new ForwardCommand("1"));
                        currentDirection = Direction.EAST;
                    } else if (currentPosition.getX() > nextPosition.getX()) {
                        commands.add(new RightCommand());
                        commands.add(new ForwardCommand("1"));
                        currentDirection = Direction.WEST;
                    } else if (currentPosition.getY() < nextPosition.getY()) {
                        commands.add(new BackCommand("1"));
                    } else {
                        commands.add(new ForwardCommand("1"));
                    }
                    break;
                case EAST:
                    if (currentPosition.getX() < nextPosition.getX()) {
                        commands.add(new ForwardCommand("1"));
                    } else if (currentPosition.getX() > nextPosition.getX()) {
                        commands.add(new BackCommand("1"));
                    } else if (currentPosition.getY() < nextPosition.getY()) {
                        commands.add(new LeftCommand());
                        commands.add(new ForwardCommand("1"));
                        currentDirection = Direction.NORTH;
                    } else {
                        commands.add(new RightCommand());
                        commands.add(new ForwardCommand("1"));
                        currentDirection = Direction.SOUTH;
                    }
                    break;
                case WEST:
                    if (currentPosition.getX() > nextPosition.getX()) {
                        commands.add(new ForwardCommand("1"));
                    } else if (currentPosition.getX() < nextPosition.getX()) {
                        commands.add(new BackCommand("1"));
                    } else if (currentPosition.getY() < nextPosition.getY()) {
                        commands.add(new RightCommand());
                        commands.add(new ForwardCommand("1"));
                        currentDirection = Direction.NORTH;
                    } else {
                        commands.add(new LeftCommand());
                        commands.add(new ForwardCommand("1"));
                        currentDirection = Direction.SOUTH;
                    }
                    break;
            }
        }
        simplifyRobotCommandSequence();
    }

    private void setInitialRobotStatus() {
        target.setStatus("Starting maze run..");
        robotStatus.add(target.toString());
    }

    @Override
    public boolean mazeRun(Robot target, IWorld.Direction edgeDirection) {
        this.target = target;
        setInitialRobotStatus();
        Position start = target.getPosition();

        TreeSet<Node> frontier = new TreeSet<>();
        frontier.add(new Node(start, 0.0,
                getHeuristic(start, edgeDirection)));

        Map<String, Double> explored = new HashMap<>();
        explored.put(start.toString(), getHeuristic(start, edgeDirection));

        while (!frontier.isEmpty()) {
            Node currentNode = frontier.pollFirst();
            Position currentPosition = currentNode.getPosition();

            if (checkCompleted(currentPosition, edgeDirection)) {
                setRobotCommandSequence(currentNode);
                return true;
            }

            for (Position child : getAdjacentPositions(currentPosition))
            {
                double costNewNode = currentNode.getCost() + 1;
                double newHeuristic = getHeuristic(child, edgeDirection);
                double newTotalCost = costNewNode + newHeuristic;

                if (!explored.containsKey(child.toString()) ||
                        explored.get(child.toString()) > newTotalCost)
                {
                    explored.put(child.toString(), costNewNode);
                    frontier.add(new Node(child, currentNode, costNewNode,
                            newHeuristic));
                }
            }
        }
        return false;
    }

    @Override
    public int getMazeRunCost() {
        return this.cost;
    }
}
