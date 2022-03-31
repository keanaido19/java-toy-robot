package za.co.wethinkcode.toyrobot.commandhandler;

import za.co.wethinkcode.toyrobot.commands.Command;
import za.co.wethinkcode.toyrobot.commands.HelpCommand;

import java.util.Arrays;

/**
 * Help Command Handler Strategy
 */
public class HelpCommandHandler extends CommandHandlerStrategy {
    @Override
    public boolean checkCommand(String command, String[] arguments) {
        return command.equals("help") && checkArguments(arguments);
    }

    @Override
    public boolean checkArguments(String[] arguments) {
        return Arrays.equals(arguments, new String[] {""});
    }

    @Override
    public Command getCommand() {
        return new HelpCommand();
    }
}
