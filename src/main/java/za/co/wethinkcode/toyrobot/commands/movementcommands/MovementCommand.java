package za.co.wethinkcode.toyrobot.commands.movementcommands;

import za.co.wethinkcode.toyrobot.Robot;
import za.co.wethinkcode.toyrobot.commands.Command;

/**
 * Movement Command Class
 */
public abstract class MovementCommand extends Command {
    /**
     * Instantiates a new Movement command.
     *
     * @param name The name of the movement command
     */
    public MovementCommand(String name) {
        super(name);
    }

    /**
     * Instantiates a new Movement command.
     *
     * @param name     The name of the movement command
     * @param argument The command argument
     */
    public MovementCommand(String name, String argument) {
        super(name, argument);
    }

    @Override
    public abstract boolean execute(Robot target);

    @Override
    public abstract MovementCommand clone();
}
