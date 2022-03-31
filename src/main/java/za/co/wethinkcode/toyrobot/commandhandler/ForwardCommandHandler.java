package za.co.wethinkcode.toyrobot.commandhandler;

import za.co.wethinkcode.toyrobot.commands.Command;
import za.co.wethinkcode.toyrobot.commands.movementcommands.ForwardCommand;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Forward Command Handler Strategy
 */
public class ForwardCommandHandler extends CommandHandlerStrategy {
    private String argument = "0";

    @Override
    public boolean checkCommand(String command, String[] arguments) {
        return command.equals("forward") && checkArguments(arguments);
    }

    /**
     * Sets the private command argument
     * @param arguments A list of arguments
     */
    private void setArgument(String[] arguments) {
        String stringArguments = Arrays.toString(arguments);
        this.argument = stringArguments.substring(
                1, stringArguments.length() - 1);
    }

    @Override
    public boolean checkArguments(String[] arguments) {
        setArgument(arguments);
        Pattern pattern = Pattern.compile("^\\d+$");
        Matcher matcher = pattern.matcher(argument);
        return matcher.find();
    }

    @Override
    public Command getCommand() {
        return new ForwardCommand(argument);
    }
}
