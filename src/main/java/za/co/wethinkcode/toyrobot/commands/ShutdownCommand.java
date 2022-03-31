package za.co.wethinkcode.toyrobot.commands;

import za.co.wethinkcode.toyrobot.Robot;

import java.util.List;

/**
 * Shutdown Command Class
 */
public class ShutdownCommand extends Command {

    /**
     * Instantiates a new Shutdown command.
     */
    public ShutdownCommand() {
        super("off");
    }

    @Override
    public ShutdownCommand clone() {
        return new ShutdownCommand();
    }

    @Override
    public boolean execute(Robot target) {
        target.setStatus("Shutting down...");
        target.setRobotStatus(List.of(target.toString()));
        return false;
    }
}
