package za.co.wethinkcode.toyrobot.commands.replaycommand.replaystrategy;

import za.co.wethinkcode.toyrobot.commands.Command;

import java.util.ArrayList;

/**
 * Replay Argument Strategy.
 */
public abstract class ReplayArgumentStrategy {

    /**
     * Checks the argument for the replay command
     *
     * @param argument replay argument
     * @return A boolean value
     */
    public abstract boolean checkArgument(String argument);

    /**
     * Processes the given list of commands based on the replay
     * argument
     *
     * @param commandHistory A list of commands
     * @return A processed list of commands
     */
    public abstract ArrayList<Command> processArguments(
            ArrayList<Command> commandHistory);
}
