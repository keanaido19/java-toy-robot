package za.co.wethinkcode.toyrobot.commandhandler;

import za.co.wethinkcode.toyrobot.commands.Command;
import za.co.wethinkcode.toyrobot.commands.mazeruncommands.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Maze Run Command Handler
 */
public class MazeRunCommandHandler extends CommandHandlerStrategy{
    private String argument = "";

    @Override
    public boolean checkCommand(String command, String[] arguments) {
        return command.equals("mazerun") && checkArguments(arguments);
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
        return new ArrayList<>(
                List.of("", "top", "right", "left", "bottom")
        ).contains(argument);
    }

    @Override
    public Command getCommand() {
        switch (argument) {
            case "":
            case "top":
                return new MazeRunTopCommand();
            case "right":
                return new MazeRunRightCommand();
            case "left":
                return new MazeRunLeftCommand();
            case "bottom":
                return new MazeRunBottomCommand();
        }
        return null;
    }
}
