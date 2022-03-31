package za.co.wethinkcode.toyrobot.commands.movementcommands;

import za.co.wethinkcode.toyrobot.Robot;
import za.co.wethinkcode.toyrobot.world.IWorld;

import java.util.List;

/**
 * Back Command Class
 */
public class BackCommand extends MovementCommand {

    /**
     * Instantiates a new Back command.
     *
     * @param argument The command argument
     */
    public BackCommand(String argument) {
        super("back", argument);
    }

    @Override
    public BackCommand clone() {
        return new BackCommand(getArgument());
    }

    @Override
    public boolean execute(Robot target) {
        int nrSteps = Integer.parseInt(getArgument());
        IWorld.UpdateResponse response =
                target.getWorld().updatePosition(-nrSteps);
        switch (response) {
            case SUCCESS:
                target.updatePosition(-nrSteps);
                target.setStatus("Moved back by "+nrSteps+" steps.");
                break;
            case FAILED_OBSTRUCTED:
                target.setStatus("Sorry, there is an obstacle in the way.");
                break;
            case FAILED_OUTSIDE_WORLD:
                target.setStatus("Sorry, I cannot go outside my safe zone.");
                break;
        }
        target.setRobotStatus(List.of(target.toString()));
        return true;
    }
}
