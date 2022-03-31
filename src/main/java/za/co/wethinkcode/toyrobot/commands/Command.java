package za.co.wethinkcode.toyrobot.commands;

import za.co.wethinkcode.toyrobot.Robot;
import za.co.wethinkcode.toyrobot.commands.movementcommands.ForwardCommand;

/**
 * Command Class
 */
public abstract class Command implements Cloneable {
    private final String name;
    private String argument;

    /**
     * Execute method for the command
     *
     * @param target A given Robot
     * @return A boolean value
     */
    public abstract boolean execute(Robot target);

    @Override
    public abstract Command clone();

    /**
     * Instantiates a new Command.
     *
     * @param name The name of the command
     */
    public Command(String name){
        this.name = name.toLowerCase().trim();
        this.argument = "";
    }

    /**
     * Instantiates a new Command.
     *
     * @param name     The name of the command
     * @param argument The command argument
     */
    public Command(String name, String argument){
        this(name);
        this.argument = argument.trim();
    }

    /**
     * Gets the name of the command.
     *
     * @return The name of the command
     */
    public String getName(){
        return this.name;
    }

    /**
     * Gets the command argument.
     *
     * @return The command argument
     */
    public String getArgument() {
        return this.argument;
    }

    /**
     * Sets the command argument.
     *
     * @param argument A given command argument
     */
    public void setArgument(String argument) {
        this.argument = argument;
    }

    /**
     * Creates a new command.
     *
     * @param instruction The instruction given that is used to create the
     *                    appropriate command
     * @return The newly created command
     */
    public static Command create(String instruction){
        String[] args = instruction.toLowerCase().strip().split(" ");
        switch(args[0]){
            case "shutdown":
            case "off":
                return new ShutdownCommand();
            case "help":
                return new HelpCommand();
            case "forward":
                String arg = args.length == 2 ? args[1]:"0";
                return new ForwardCommand(arg);
            default:
                throw new IllegalArgumentException("Unsupported command: " +
                        instruction);
        }
    }
}
