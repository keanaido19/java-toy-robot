package za.co.wethinkcode.toyrobot.commands;

import za.co.wethinkcode.toyrobot.Robot;

import java.util.List;

/**
 * Null Command Class
 */
public class NullCommand extends Command{
    private final String instruction;

    /**
     * Instantiates a new Null command.
     *
     * @param instruction The given instruction to the robot
     */
    public NullCommand(String instruction) {
        super(instruction);
        this.instruction = instruction;
    }

    @Override
    public NullCommand clone() {
        return new NullCommand(this.instruction);
    }

    @Override
    public boolean execute(Robot target) {
        target.setStatus("Sorry, I did not understand '" + this.instruction +
                "'.");
        target.setRobotStatus(List.of(target.toString()));
        return true;
    }
}
