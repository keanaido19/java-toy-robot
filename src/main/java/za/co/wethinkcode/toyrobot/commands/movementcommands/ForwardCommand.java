package za.co.wethinkcode.toyrobot.commands.movementcommands;

import za.co.wethinkcode.toyrobot.Robot;
import za.co.wethinkcode.toyrobot.world.IWorld;

import java.util.List;

/**
 * Forward Command
 */
public class ForwardCommand extends MovementCommand {

    /**
     * Instantiates a new Forward command.
     *
     * @param commandArgs The command argument
     */
    public ForwardCommand(String commandArgs) {
        super("forward", commandArgs);
    }


    @Override
    public ForwardCommand clone() {
        return new ForwardCommand(getArgument());
    }

    @Override
    public boolean execute(Robot target) {
        int nrSteps = Integer.parseInt(getArgument());
        IWorld.UpdateResponse response =
                target.getWorld().updatePosition(nrSteps);
        switch (response) {
            case SUCCESS:
                target.updatePosition(nrSteps);
                target.setStatus("Moved forward by "+nrSteps+" steps.");
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
