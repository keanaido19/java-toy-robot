package za.co.wethinkcode.toyrobot.commands.movementcommands;

import za.co.wethinkcode.toyrobot.Robot;
import za.co.wethinkcode.toyrobot.world.IWorld;

import java.util.ArrayList;
import java.util.List;

/**
 * Sprint Command Class
 */
public class SprintCommand extends MovementCommand {
    private final List<String> robotStatus = new ArrayList<>();

    /**
     * Instantiates a new Sprint command.
     *
     * @param commandArgs The command argument
     */
    public SprintCommand(String commandArgs) {
        super("sprint", commandArgs);
    }

    @Override
    public SprintCommand clone() {
        return new SprintCommand(getArgument());
    }

    @Override
    public boolean execute(Robot target) {
        int nrSteps = Integer.parseInt(getArgument());
        IWorld.UpdateResponse response =
                target.getWorld().updatePosition(nrSteps);
        switch (response) {
            case SUCCESS:
                target.updatePosition(nrSteps);
                target.setStatus("Moved forward by " + getArgument() +
                        " steps.");
                robotStatus.add(target.toString());
                if (!(nrSteps == 1 || nrSteps == 0)) {
                    setArgument(String.valueOf(nrSteps - 1));
                    execute(target);
                }
                break;
            case FAILED_OBSTRUCTED:
                target.setStatus("Sorry, there is an obstacle in the way.");
                robotStatus.add(target.toString());
                break;
            case FAILED_OUTSIDE_WORLD:
                target.setStatus("Sorry, I cannot go outside my safe zone.");
                robotStatus.add(target.toString());
                break;
        }
        target.setRobotStatus(robotStatus);
        return true;
    }
}
