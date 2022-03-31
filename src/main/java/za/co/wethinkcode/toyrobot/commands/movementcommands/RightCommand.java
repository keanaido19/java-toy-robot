package za.co.wethinkcode.toyrobot.commands.movementcommands;

import za.co.wethinkcode.toyrobot.Direction;
import za.co.wethinkcode.toyrobot.Robot;

import java.util.List;

/**
 * Right Command Class
 */
public class RightCommand extends MovementCommand {

    /**
     * Instantiates a new Right command.
     */
    public RightCommand() {
        super("right");
    }

    @Override
    public RightCommand clone() {
        return new RightCommand();
    }

    @Override
    public boolean execute(Robot target) {
        target.getWorld().updateDirection(true);
        switch (target.getCurrentDirection()) {
            case NORTH:
                target.setCurrentDirection(Direction.EAST);
                break;
            case EAST:
                target.setCurrentDirection(Direction.SOUTH);
                break;
            case SOUTH:
                target.setCurrentDirection(Direction.WEST);
                break;
            case WEST:
                target.setCurrentDirection(Direction.NORTH);
                break;
        }
        target.setStatus("Turned right.");
        target.setRobotStatus(List.of(target.toString()));
        return true;
    }
}
