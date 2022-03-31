package za.co.wethinkcode.toyrobot.commands.mazeruncommands;

import za.co.wethinkcode.toyrobot.Robot;
import za.co.wethinkcode.toyrobot.commands.Command;
import za.co.wethinkcode.toyrobot.world.IWorld;

/**
 * Maze Run Left Command
 */
public class MazeRunLeftCommand extends MazeRunCommand {

    /**
     * Instantiates a new Maze run left command.
     */
    public MazeRunLeftCommand() {
        super("left");
    }

    @Override
    public boolean execute(Robot target) {
        if (mazeRun(target, IWorld.Direction.LEFT)) {
            for (Command command : getCommandArrayList()) {
                command.execute(target);
                addToRobotStatus(target.showRobotStatus());
            }
            target.setStatus("I am at the left edge. (Cost: " + getMazeRunCost()
                    + " steps)");
        } else {
            target.setStatus("I am lost. (Cost: " + getMazeRunCost() +
                    " steps)");
        }
        addToRobotStatus(target.toString());
        target.setRobotStatus(getRobotStatus());
        return true;
    }

    @Override
    public Command clone() {
        return new MazeRunLeftCommand();
    }
}
