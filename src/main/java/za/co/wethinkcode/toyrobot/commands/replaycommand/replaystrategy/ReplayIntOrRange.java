package za.co.wethinkcode.toyrobot.commands.replaycommand.replaystrategy;

import za.co.wethinkcode.toyrobot.commands.Command;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Replay Int/Range Strategy
 */
public class ReplayIntOrRange extends ReplayArgumentStrategy{
    private String argument;

    /**
     * Checks if the argument is a positive integer
     *
     * @return Boolean value
     */
    private boolean checkInt() {
        Pattern pattern = Pattern.compile("^\\d+$");
        Matcher matcher = pattern.matcher(argument);
        return matcher.find();
    }

    /**
     * Gets the range values of n and m from a range string
     *
     * @return The range values of n and m
     */
    private Integer[] getRange() {
        String[] range = argument.split("-");
        return new Integer[] {Integer.parseInt(range[0]),
                Integer.parseInt(range[1])};
    }

    /**
     * Checks if the argument is a range of 'n-m' where n > m
     *
     * @return Boolean value
     */
    private boolean checkRange() {
        Pattern pattern = Pattern.compile("^\\d+-\\d+$");
        Matcher matcher = pattern.matcher(argument);
        if (matcher.find()) {
            Integer[] range = getRange();
            return range[0] >= range[1];
        }
        return false;
    }

    @Override
    public boolean checkArgument(String argument) {
        this.argument = argument;
        return checkInt() || checkRange();
    }

    /**
     * Processes the given list of commands using the integer
     * processing method
     *
     * @param commandHistory A list of commands
     * @return A processed list of commands
     */
    private ArrayList<Command> processInt(ArrayList<Command> commandHistory) {
        ArrayList<Command> returnArrayList = new ArrayList<>();
        int index = commandHistory.size() - Integer.parseInt(argument);
        if (!argument.equals("0")) {
            for (int i = index; i < commandHistory.size(); i++) {
                returnArrayList.add(commandHistory.get(i));
            }
        }
        return returnArrayList;
    }

    /**
     * Processes the given list of commands using the range processing method
     *
     * @param commandHistory A list of commands
     * @return A processed list of commands
     */
    private ArrayList<Command> processRange(ArrayList<Command> commandHistory) {
        ArrayList<Command> returnArrayList = new ArrayList<>();
        Integer[] range = getRange();
        int n = range[0];
        int m = range[1];
        if (m == 0) {
            this.argument = String.valueOf(n);
            return processInt(commandHistory);
        } else {
            int upperBound = commandHistory.size() - n;
            int lowerBound = commandHistory.size() - m;
            for (int i = upperBound; i < lowerBound; i++) {
                returnArrayList.add(commandHistory.get(i));
            }
        }
        return returnArrayList;
    }

    @Override
    public ArrayList<Command> processArguments(
            ArrayList<Command> commandHistory) {
        if (checkInt()) {
            return processInt(commandHistory);
        }
        return processRange(commandHistory);
    }
}
