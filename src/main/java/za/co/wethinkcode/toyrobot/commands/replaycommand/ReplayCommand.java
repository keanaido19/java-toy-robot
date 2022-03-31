package za.co.wethinkcode.toyrobot.commands.replaycommand;

import za.co.wethinkcode.toyrobot.Robot;
import za.co.wethinkcode.toyrobot.commands.Command;
import za.co.wethinkcode.toyrobot.commands.replaycommand.replaystrategy.ReplayArgumentStrategy;
import za.co.wethinkcode.toyrobot.commands.replaycommand.replaystrategy.ReplayIntOrRange;
import za.co.wethinkcode.toyrobot.commands.replaycommand.replaystrategy.ReplayReversed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Replay Command Class
 */
public class ReplayCommand extends Command {
    private boolean silent = false;
    private String[] replayArgs;
    private List<String> robotStatus = new ArrayList<>();
    private ArrayList<Command> movementCommandHistory = new ArrayList<>();
    private final List<ReplayArgumentStrategy> replayArgumentStrategies =
            new ArrayList<>();

    /**
     * Instantiates a new Replay command.
     */
    public ReplayCommand() {
        super("replay", "");
        this.replayArgumentStrategies.add(new ReplayIntOrRange());
    }

    /**
     * Instantiates a new Replay command.
     *
     * @param argument the command arguments
     */
    public ReplayCommand(String[] argument) {
        super("replay", Arrays.toString(argument));
        this.replayArgs = argument;
        this.replayArgumentStrategies.add(new ReplayIntOrRange());
    }

    /**
     * @return A list of replay arguments
     */
    private String[] getReplayArgs() {
        return this.replayArgs;
    }

    /**
     * Sets the replay silent method for the replay command
     */
    private void setReplaySilent() {
        this.silent = Arrays.toString(this.replayArgs).contains("silent");
    }

    /**
     * Creates a copy of a list of commands and stores it privately
     * @param movementCommandHistory List of commands
     */
    private void setMovementCommandHistory(
            ArrayList<Command> movementCommandHistory) {
        for (Command command : movementCommandHistory) {
            this.movementCommandHistory.add(command.clone());
        }
    }

    /**
     * @return List of movement commands
     */
    private ArrayList<Command> getMovementCommandHistory() {
        return this.movementCommandHistory;
    }

    /**
     * Sets the list of movement commands to the given list of commands
     * @param commandHistory A list of commands
     */
    private void updateMovementCommandHistory(ArrayList<Command> commandHistory)
    {
        this.movementCommandHistory = commandHistory;
    }

    /**
     * Processes all the replay arguments
     */
    private void processReplayArguments() {
        for (String argument : replayArgs) {
            processReplayArgument(argument);
        }
    }

    /**
     * Processes the replay argument strategy
     * @param replayArgumentStrategy A replay argument strategy
     */
    private void processReplayArgumentStrategy(
            ReplayArgumentStrategy replayArgumentStrategy) {
        updateMovementCommandHistory(
                replayArgumentStrategy.processArguments(
                        getMovementCommandHistory()));
    }

    /**
     * Processes a single replay argument
     * @param argument A replay argument
     */
    private void processReplayArgument(String argument) {
        for (ReplayArgumentStrategy replayArgumentStrategy :
                replayArgumentStrategies) {
            if (replayArgumentStrategy.checkArgument(argument)) {
                processReplayArgumentStrategy(replayArgumentStrategy);
            }
        }
    }

    @Override
    public ReplayCommand clone() {
        return new ReplayCommand(getReplayArgs());
    }

    /**
     * Sets the output status of the given robot
     * @param target A given robot
     */
    private void setRobotStatus(Robot target) {
        int commandNumber = getMovementCommandHistory().size();
        target.setStatus("replayed " + commandNumber + (commandNumber == 1 ?
                " command" : " commands") + ".");
        if (silent) {
            robotStatus = List.of(new String[] {target.toString()});
        } else {
            robotStatus.add(target.toString());
        }
        target.setRobotStatus(robotStatus);
    }

    @Override
    public boolean execute(Robot target) {
        try {
            setReplaySilent();
            setMovementCommandHistory(target.getMovementCommandHistory());
            processReplayArguments();

            if (Arrays.toString(this.replayArgs).contains("reversed")) {
                this.movementCommandHistory =
                        new ReplayReversed().processArguments(
                                this.movementCommandHistory);
            }

            for (Command command : movementCommandHistory) {
                command.execute(target);
                robotStatus.add(target.showRobotStatus());
            }
        } catch (IndexOutOfBoundsException e) {
            target.setStatus("Invalid range for REPLAY command!");
            robotStatus.add(target.toString());
            target.setRobotStatus(robotStatus);
            return true;
        }
        setRobotStatus(target);
        return true;
    }
}
