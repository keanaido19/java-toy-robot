package za.co.wethinkcode.toyrobot.commandhandler;

import za.co.wethinkcode.toyrobot.commands.Command;
import za.co.wethinkcode.toyrobot.commands.movementcommands.RightCommand;

import java.util.Arrays;

/**
 * Right Command Handler Strategy
 */
public class RightCommandHandler extends CommandHandlerStrategy {
    @Override
    public boolean checkCommand(String command, String[] arguments) {
        return command.equals("right") && checkArguments(arguments);
    }

    @Override
    public boolean checkArguments(String[] arguments) {
        return Arrays.equals(arguments, new String[] {""});
    }

    @Override
    public Command getCommand() {
        return new RightCommand();
    }
}
