package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.commands.Command;
import za.co.wethinkcode.toyrobot.commands.movementcommands.MovementCommand;
import za.co.wethinkcode.toyrobot.maze.EmptyMaze;
import za.co.wethinkcode.toyrobot.world.AbstractWorld;
import za.co.wethinkcode.toyrobot.world.TextWorld;

import java.util.ArrayList;
import java.util.List;

/**
 * Robot Class
 */
public class Robot {
    public static final Position CENTRE = new Position(0, 0);

    private final ArrayList<Command> movementCommandHistory = new ArrayList<>();
    private final AbstractWorld world;
    private final String name;

    private String robotStatus = "";
    private String status;
    private Position position;
    private Direction currentDirection;

    /**
     * Instantiates a new Robot.
     *
     * @param name The name of the robot
     */
    public Robot(String name) {
        this.name = name;
        this.status = "Ready";
        this.position = CENTRE;
        this.currentDirection = Direction.NORTH;
        this.world = new TextWorld(new EmptyMaze());
    }

    /**
     * Instantiates a new Robot.
     *
     * @param name  The name of the robot
     * @param world The given world which the robot will reside in
     */
    public Robot(String name, AbstractWorld world) {
        this.name = name;
        this.status = "Ready";
        this.position = CENTRE;
        this.currentDirection = Direction.NORTH;
        this.world = world;
    }

    @Override
    public String toString() {
        return "[" + this.position.getX() + "," + this.position.getY() + "] "
                + this.name + "> " + this.status;
    }

    /**
     * @return A friendly greeting to the user
     */
    public String greetUser() {
        return "Hello Kiddo!";
    }

    /**
     * @return A message asking the user what instruction they would like the
     * robot to execute next
     */
    public String doNext() {
        return this.name + "> " + "What must I do next?";
    }

    /**
     * Gets the status of the robot
     *
     * @return The status of the robot
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * Sets the status of the robot
     *
     * @param status A given status message
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return The status message of the robot
     */
    public String showRobotStatus() {
        return this.robotStatus;
    }

    /**
     * Sets the output status message of the robot
     *
     * @param robotStatus A list of robot status messages
     */
    public void setRobotStatus(List<String> robotStatus) {
        this.robotStatus = String.join("\n", robotStatus);
    }

    /**
     * Gets the current direction of the robot
     *
     * @return The current direction of the robot
     */
    public Direction getCurrentDirection() {
        return currentDirection;
    }

    /**
     * Sets the current direction of the robot
     *
     * @param direction A given direction
     */
    public void setCurrentDirection(Direction direction) {
        this.currentDirection = direction;
    }

    /**
     * Gets the current position of the robot
     *
     * @return The current position of the robot
     */
    public Position getPosition() {
        return this.position;
    }

    /**
     * Gets the current world the robot resides in
     *
     * @return The current world the robot resides in
     */
    public AbstractWorld getWorld() {
        return this.world;
    }

    /**
     * Updates the command history list of the robot with a movement command
     * that the robot will execute
     * @param command A command that the robot will execute
     */
    private void updateMovementCommandHistory(Command command) {
        if (MovementCommand.class.isAssignableFrom(command.getClass())) {
            this.movementCommandHistory.add(command.clone());
        }
    }

    /**
     * Gets command history for the robot
     *
     * @return The command history for the robot
     */
    public ArrayList<Command> getMovementCommandHistory() {
        return this.movementCommandHistory;
    }

    /**
     * Command handler for the robot
     *
     * @param command The given command
     * @return A boolean value
     */
    public boolean handleCommand(Command command) {
        updateMovementCommandHistory(command);
        return command.execute(this);
    }

    /**
     * Updates the position of the robot given the number of steps the robot
     * needs to travel
     *
     * @param nrSteps The number of steps the robot needs to travel
     */
    public void updatePosition(int nrSteps) {
        int newY = this.position.getY();
        int newX = this.position.getX();

        if (Direction.NORTH.equals(this.currentDirection)) {
            newY = newY + nrSteps;
        } else if (Direction.SOUTH.equals(this.currentDirection)) {
            newY = newY - nrSteps;
        } else if (Direction.EAST.equals(this.currentDirection)) {
            newX = newX + nrSteps;
        } else if (Direction.WEST.equals(this.currentDirection)) {
            newX = newX - nrSteps;
        }
        this.position = new Position(newX, newY);
    }
}
