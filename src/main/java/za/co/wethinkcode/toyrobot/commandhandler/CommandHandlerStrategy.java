package za.co.wethinkcode.toyrobot.commandhandler;

import za.co.wethinkcode.toyrobot.commands.Command;

/**
 * Command Handler Strategy.
 */
public abstract class CommandHandlerStrategy {

    /**
     * Checks if the given command and its arguments are valid
     *
     * @param command   The name of the command
     * @param arguments The command arguments
     * @return Boolean value
     */
    public abstract boolean checkCommand(String command, String[] arguments);

    /**
     * Check if the arguments for the command are valid
     *
     * @param arguments The command arguments
     * @return Boolean value
     */
    public abstract boolean checkArguments(String[] arguments);

    /**
     * @return A robot command
     */
    public abstract Command getCommand();
}
