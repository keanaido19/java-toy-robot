package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.commandhandler.CommandHandler;
import za.co.wethinkcode.toyrobot.maze.*;
import za.co.wethinkcode.toyrobot.world.AbstractWorld;
import za.co.wethinkcode.toyrobot.world.TextWorld;
import za.co.wethinkcode.toyrobot.world.TurtleWorld;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Play Class
 */
public class Play {
    private static String robotName;
    private AbstractWorld world;
    private final Scanner inputScanner = new Scanner(System.in);
    private boolean loop = true;

    /**
     * Instantiates a new Play.
     */
    public Play() {}

    private String getUserInput(){
        return this.inputScanner.nextLine().trim();
    }

    /**
     * Asks the user to enter a name for the robot
     */
    private void getRobotName() {
        System.out.println("What do you want to name your robot?");
        robotName = getUserInput();
    }

    /**
     * Gets the correct world and maze for the robot to reside in based on
     * the commandline arguments
     *
     * @param args Commandline arguments
     * @return The appropriate world loaded message
     */
    private String getWorld(String[] args) {
        Maze maze = new RandomMaze();
        String mazeName = "RandomMaze";

        if (!Arrays.toString(args).equals("[]")) {
            if (args.length >= 2 &&
                    (args[0].equals("text") || args[0].equals("turtle"))) {
                switch (args[1]) {
                    case "SimpleMaze":
                        maze = new SimpleMaze();
                        mazeName = "SimpleMaze";
                        break;
                    case "EmptyMaze":
                        maze = new EmptyMaze();
                        mazeName = "EmptyMaze";
                        break;
                    case "DesignedMaze":
                        maze = new DesignedMaze();
                        mazeName = "DesignedMaze";
                        break;
                }
            }
            switch (args[0]) {
                case "text":

                case "RandomMaze":
                    world = new TextWorld(maze);
                    return "Loaded " + mazeName + ".";

                case "turtle":
                    world = new TurtleWorld(maze);
                    return "Loaded Turtle " + mazeName + ".";

                case "SimpleMaze":
                    maze = new SimpleMaze();
                    mazeName = "SimpleMaze";
                    world = new TextWorld(maze);
                    return "Loaded " + mazeName + ".";

                case "EmptyMaze":
                    maze = new EmptyMaze();
                    mazeName = "EmptyMaze";
                    world = new TextWorld(maze);
                    return "Loaded " + mazeName + ".";

                case "DesignedMaze":
                    maze = new DesignedMaze();
                    mazeName = "DesignedMaze";
                    world = new TextWorld(maze);
                    return "Loaded " + mazeName + ".";
            }
        }
        world = new TextWorld(maze);
        return "Loaded " + mazeName + ".";
    }

    /**
     * Run method to use the robot
     *
     * @param args Commandline arguments
     */
    private void run(String[] args) {
        getRobotName();
        System.out.println(getWorld(args));
        world.showObstacles();
        Robot toyRobot = new Robot(robotName, world);
        System.out.println(toyRobot.greetUser());
        while (loop) {
            System.out.println(toyRobot.doNext());
            CommandHandler commandHandler =
                    new CommandHandler(getUserInput());
            loop = toyRobot.handleCommand(commandHandler.getRobotCommand());
            System.out.println(toyRobot.showRobotStatus());
        }
        world.destroyWorld();
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        new Play().run(args);
    }
}
