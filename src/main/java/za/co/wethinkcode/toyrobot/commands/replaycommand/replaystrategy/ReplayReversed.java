package za.co.wethinkcode.toyrobot.commands.replaycommand.replaystrategy;

import za.co.wethinkcode.toyrobot.commands.Command;

import java.util.ArrayList;

/**
 * Replay Reversed Strategy
 */
public class ReplayReversed extends ReplayArgumentStrategy{
    @Override
    public boolean checkArgument(String argument) {
        return argument.equals("reversed");
    }

    @Override
    public ArrayList<Command> processArguments(
            ArrayList<Command> commandHistory) {
        ArrayList<Command> returnArrayList = new ArrayList<>();
        for (int i = commandHistory.size() - 1; i >= 0; i--) {
            returnArrayList.add(commandHistory.get(i));
        }
        return returnArrayList;
    }
}
