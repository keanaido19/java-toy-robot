package za.co.wethinkcode.toyrobot.commands.movementcommands;

import za.co.wethinkcode.toyrobot.Direction;
import za.co.wethinkcode.toyrobot.Robot;

import java.util.List;

/**
 * Left Command Class
 */
public class LeftCommand extends MovementCommand {
    /**
     * Instantiates a new Left command.
     */
    public LeftCommand() {
        super("left");
    }

    @Override
    public LeftCommand clone() {
        return new LeftCommand();
    }

    @Override
    public boolean execute(Robot target) {
        target.getWorld().updateDirection(false);
        switch (target.getCurrentDirection()) {
            case NORTH:
                target.setCurrentDirection(Direction.WEST);
                break;
            case EAST:
                target.setCurrentDirection(Direction.NORTH);
                break;
            case SOUTH:
                target.setCurrentDirection(Direction.EAST);
                break;
            case WEST:
                target.setCurrentDirection(Direction.SOUTH);
                break;
        }
        target.setStatus("Turned left.");
        target.setRobotStatus(List.of(target.toString()));
        return true;
    }
}
