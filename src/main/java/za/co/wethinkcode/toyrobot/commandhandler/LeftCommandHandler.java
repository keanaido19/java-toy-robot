package za.co.wethinkcode.toyrobot.commandhandler;

import za.co.wethinkcode.toyrobot.commands.Command;
import za.co.wethinkcode.toyrobot.commands.movementcommands.LeftCommand;

import java.util.Arrays;

/**
 * Left Command Handler Strategy
 */
public class LeftCommandHandler extends CommandHandlerStrategy {
    @Override
    public boolean checkCommand(String command, String[] arguments) {
        return command.equals("left") && checkArguments(arguments);
    }

    @Override
    public boolean checkArguments(String[] arguments) {
        return Arrays.equals(arguments, new String[] {""});
    }

    @Override
    public Command getCommand() {
        return new LeftCommand();
    }
}
