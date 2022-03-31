package za.co.wethinkcode.toyrobot.commands;

import za.co.wethinkcode.toyrobot.Robot;

import java.util.List;

/**
 * Help Command Class
 */
public class HelpCommand extends Command {

    /**
     * Instantiates a new Help command.
     */
    public HelpCommand() {
        super("help");
    }

    @Override
    public HelpCommand clone() {
        return new HelpCommand();
    }

    @Override
    public boolean execute(Robot target) {
        target.setStatus("I can understand these commands:\n" +
                "OFF  - Shut down robot\n" +
                "HELP - provide information about commands\n" +
                "FORWARD - move forward by specified number of steps, e.g. " +
                "'FORWARD 10'\n" +
                "BACK - move backward by specified number of steps, e.g. " +
                "'BACK 10'\n" +
                "RIGHT - turn right by 90 degrees\n" +
                "LEFT - turn left by 90 degrees\n" +
                "SPRINT - sprint forward according to a formula\n" +
                "REPLAY - replays all movement commands from history " +
                "[FORWARD, BACK, RIGHT, LEFT, SPRINT]\n" +
                "MAZERUN - solves a maze by moving the the robot to an edge " +
                "of the maze, \n" +
                "          e.g. 'MAZERUN TOP', 'MAZERUN RIGHT', " +
                "'MAZERUN LEFT', 'MAZERUN BOTTOM'");
        target.setRobotStatus(List.of(target.toString()));
        return true;
    }
}
