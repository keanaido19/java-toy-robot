package za.co.wethinkcode.toyrobot.commands.replaycommand.replaystrategy;

import za.co.wethinkcode.toyrobot.commands.Command;

import java.util.ArrayList;

/**
 * Replay Silent Strategy
 */
public class ReplaySilent extends ReplayArgumentStrategy{
    @Override
    public boolean checkArgument(String argument) {
        return argument.equals("silent");
    }

    @Override
    public ArrayList<Command> processArguments(
            ArrayList<Command> commandHistory) {
        return commandHistory;
    }
}
