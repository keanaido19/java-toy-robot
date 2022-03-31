package za.co.wethinkcode.toyrobot.commandhandler;

import za.co.wethinkcode.toyrobot.commands.Command;
import za.co.wethinkcode.toyrobot.commands.replaycommand.ReplayCommand;
import za.co.wethinkcode.toyrobot.commands.replaycommand.replaystrategy.ReplayArgumentStrategy;
import za.co.wethinkcode.toyrobot.commands.replaycommand.replaystrategy.ReplayIntOrRange;
import za.co.wethinkcode.toyrobot.commands.replaycommand.replaystrategy.ReplayReversed;
import za.co.wethinkcode.toyrobot.commands.replaycommand.replaystrategy.ReplaySilent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Replay Command Handler Strategy
 */
public class ReplayCommandHandler extends CommandHandlerStrategy {
    private String[] replayArgs;
    private final List<ReplayArgumentStrategy> replayArgumentStrategies =
            new ArrayList<>();

    /**
     * Instantiates a new Replay command handler.
     */
    public ReplayCommandHandler() {
        this.replayArgumentStrategies.add(new ReplayIntOrRange());
        this.replayArgumentStrategies.add(new ReplaySilent());
        this.replayArgumentStrategies.add(new ReplayReversed());
    }

    @Override
    public boolean checkCommand(String command, String[] arguments) {
        this.replayArgs = arguments;
        return command.equals("replay") && checkArguments(arguments);
    }

    /**
     * Checks if an argument is a valid replay argument
     * @param argument A command argument
     * @return Boolean value
     */
    private boolean checkArgument(String argument) {
        for (ReplayArgumentStrategy replayArgumentStrategy :
                replayArgumentStrategies) {
            if (replayArgumentStrategy.checkArgument(argument)) {
                replayArgumentStrategies.remove(replayArgumentStrategy);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkArguments(String[] arguments) {
        if (Arrays.equals(arguments, new String[] {""})) {
            return true;
        }
        for (String argument : arguments) {
            if (!checkArgument(argument)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Command getCommand() {
        return new ReplayCommand(replayArgs);
    }
}
