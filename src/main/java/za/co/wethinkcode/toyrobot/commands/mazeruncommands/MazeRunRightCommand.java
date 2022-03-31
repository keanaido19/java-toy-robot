package za.co.wethinkcode.toyrobot.commands.mazeruncommands;

import za.co.wethinkcode.toyrobot.Robot;
import za.co.wethinkcode.toyrobot.commands.Command;
import za.co.wethinkcode.toyrobot.world.IWorld;

/**
 * Maze Run Right Command
 */
public class MazeRunRightCommand extends MazeRunCommand {

    /**
     * Instantiates a new Maze run right command.
     */
    public MazeRunRightCommand() {
        super("right");
    }

    @Override
    public boolean execute(Robot target) {
        if (mazeRun(target, IWorld.Direction.RIGHT)) {
            for (Command command : getCommandArrayList()) {
                command.execute(target);
                addToRobotStatus(target.showRobotStatus());
            }
            target.setStatus("I am at the right edge. (Cost: " +
                    getMazeRunCost() +
                    (getMazeRunCost() == 1 ? " step)": " steps)"));
        } else {
            target.setStatus("I am lost. (Cost: " + getMazeRunCost() +
                    (getMazeRunCost() == 1 ? " step)": " steps)"));
        }
        addToRobotStatus(target.toString());
        target.setRobotStatus(getRobotStatus());
        return true;
    }

    @Override
    public Command clone() {
        return new MazeRunRightCommand();
    }
}
