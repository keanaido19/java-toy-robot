package za.co.wethinkcode.toyrobot.commandhandler;

import za.co.wethinkcode.toyrobot.commands.Command;
import za.co.wethinkcode.toyrobot.commands.NullCommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Command Handler Class
 */
public class CommandHandler {
    private String commandInput;
    private final CommandHandlerStrategy[] RobotCommandHandlerStrategies =
            {new ShutdownCommandHandler(),
                    new HelpCommandHandler(),
                    new ForwardCommandHandler(),
                    new BackCommandHandler(),
                    new RightCommandHandler(),
                    new LeftCommandHandler(),
                    new SprintCommandHandler(),
                    new ReplayCommandHandler(),
                    new MazeRunCommandHandler()};

    /**
     * Instantiates a new Command handler.
     *
     * @param commandInput The command input from the user
     */
    public CommandHandler(String commandInput) {
        this.commandInput = commandInput;
    }

    /**
     * Gets the command input
     *
     * @return The command input
     */
    public String getCommandInput() {
        return this.commandInput.trim();
    }

    /**
     * Gets the name of the command from the command input
     *
     * @return The name of the command
     */
    public String getCommand() {
        return this.commandInput.toLowerCase().split(" ")[0];
    }

    /**
     * Gets the command arguments form the command input
     *
     * @return The command arguments
     */
    public String[] getCommandArguments() {
        String[] commandAndArguments =
                this.commandInput.toLowerCase().split(" ", 2);
        return commandAndArguments.length < 2 ?
                new String[] {""} : commandAndArguments[1].split(" ");
    }

    /**
     * Gets the appropriate robot command from the command input by iterating
     * through a list of handler strategies
     *
     * @return A command for the robot to execute
     */
    public Command getRobotCommand() {
        String command = getCommand();
        String[] arguments = getCommandArguments();

        try {
            String argument = arguments[0];
            Pattern pattern = Pattern.compile("^\\d+$");
            Matcher matcher = pattern.matcher(argument);
            if (matcher.find()) Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            command = "null";
            commandInput = commandInput + " - Number too large!";
        }

        for (CommandHandlerStrategy commandHandlerStrategy :
                RobotCommandHandlerStrategies) {
            if (commandHandlerStrategy.checkCommand(command, arguments)) {
                return commandHandlerStrategy.getCommand();
            }
        }
        return new NullCommand(commandInput);
    }
}
