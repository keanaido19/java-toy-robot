package za.co.wethinkcode.toyrobot.commandhandler;

import za.co.wethinkcode.toyrobot.commands.Command;
import za.co.wethinkcode.toyrobot.commands.ShutdownCommand;

import java.util.Arrays;

/**
 * Shutdown Command Handler Strategy
 */
public class ShutdownCommandHandler extends CommandHandlerStrategy {
    @Override
    public boolean checkCommand(String command, String[] arguments) {
        return (command.equals("off") || command.equals("shutdown"))
                && checkArguments(arguments);
    }

    @Override
    public boolean checkArguments(String[] arguments) {
        return Arrays.equals(arguments, new String[] {""});
    }

    @Override
    public Command getCommand() {
        return new ShutdownCommand();
    }
}
