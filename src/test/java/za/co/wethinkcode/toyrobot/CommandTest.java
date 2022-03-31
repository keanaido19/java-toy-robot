package za.co.wethinkcode.toyrobot;


import org.junit.jupiter.api.Test;
import za.co.wethinkcode.toyrobot.commands.*;
import za.co.wethinkcode.toyrobot.commands.mazeruncommands.MazeRunBottomCommand;
import za.co.wethinkcode.toyrobot.commands.mazeruncommands.MazeRunLeftCommand;
import za.co.wethinkcode.toyrobot.commands.mazeruncommands.MazeRunRightCommand;
import za.co.wethinkcode.toyrobot.commands.mazeruncommands.MazeRunTopCommand;
import za.co.wethinkcode.toyrobot.commands.movementcommands.*;
import za.co.wethinkcode.toyrobot.commands.replaycommand.ReplayCommand;
import za.co.wethinkcode.toyrobot.maze.SimpleMaze;
import za.co.wethinkcode.toyrobot.world.TextWorld;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest {

    @Test
    void getShutdownName() {
        Command test = new ShutdownCommand();
        assertEquals("off", test.getName());
    }

    @Test
    void executeShutdown() {
        Robot robot = new Robot("CrashTestDummy");
        Command shutdown = Command.create("shutdown");
        assertFalse(shutdown.execute(robot));
        assertEquals("Shutting down...", robot.getStatus());
    }

    @Test
    void getForwardName() {
        ForwardCommand test = new ForwardCommand("100");
        assertEquals("forward", test.getName());
        assertEquals("100", test.getArgument());
    }

    @Test
    void executeForward() {
        Robot robot = new Robot("CrashTestDummy");
        Command forward100 = Command.create("forward 10");
        assertTrue(forward100.execute(robot));
        Position expectedPosition = new Position(Robot.CENTRE.getX(),
                Robot.CENTRE.getY() + 10);
        assertEquals(expectedPosition, robot.getPosition());
        assertEquals("Moved forward by 10 steps.", robot.getStatus());
    }

    @Test
    void getHelpName() {
        Command test = new HelpCommand();
        assertEquals("help", test.getName());
    }

    @Test
    void executeHelp() {
        Robot robot = new Robot("CrashTestDummy");
        Command help = Command.create("help");
        assertTrue(help.execute(robot));
        assertEquals("I can understand these commands:\n" +
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
                "'MAZERUN LEFT', 'MAZERUN BOTTOM'", robot.getStatus());
    }

    @Test
    void createCommand() {
        Command forward = Command.create("forward 10");
        assertEquals("forward", forward.getName());
        assertEquals("10", forward.getArgument());

        Command shutdown = Command.create("shutdown");
        assertEquals("off", shutdown.getName());

        Command help = Command.create("help");
        assertEquals("help", help.getName());
    }

    @Test
    void createInvalidCommand() {
        try {
            Command forward = Command.create("say hello");
            fail("Should have thrown an exception");
        } catch (IllegalArgumentException e) {
            assertEquals("Unsupported command: say hello",
                    e.getMessage());
        }
    }

    @Test
    void executeBack() {
        Robot robot = new Robot("CrashTestDummy");
        Command backCommand = new BackCommand("10");
        assertTrue(backCommand.execute(robot));
        Position expectedPosition = new Position(Robot.CENTRE.getX(),
                Robot.CENTRE.getY() - 10);
        assertEquals(expectedPosition, robot.getPosition());
        assertEquals("Moved back by 10 steps.", robot.getStatus());
    }

    @Test
    void executeRight() {
        Robot robot = new Robot("CrashTestDummy");
        Command rightCommand = new RightCommand();
        assertTrue(rightCommand.execute(robot));
        Position expectedPosition = new Position(Robot.CENTRE.getX(),
                Robot.CENTRE.getY());
        assertEquals(Direction.EAST, robot.getCurrentDirection());
        assertEquals(expectedPosition, robot.getPosition());
        assertEquals("Turned right.", robot.getStatus());
    }

    @Test
    void executeLeft() {
        Robot robot = new Robot("CrashTestDummy");
        Command leftCommand = new LeftCommand();
        assertTrue(leftCommand.execute(robot));
        Position expectedPosition = new Position(Robot.CENTRE.getX(),
                Robot.CENTRE.getY());
        assertEquals(Direction.WEST, robot.getCurrentDirection());
        assertEquals(expectedPosition, robot.getPosition());
        assertEquals("Turned left.", robot.getStatus());
    }

    @Test
    void executeSprint() {
        Robot robot = new Robot("CrashTestDummy");
        Command sprintCommand = new SprintCommand("10");
        assertTrue(sprintCommand.execute(robot));
        Position expectedPosition = new Position(Robot.CENTRE.getX(),
                Robot.CENTRE.getY() + 55);
        assertEquals(expectedPosition, robot.getPosition());
        assertEquals(
                "[0,10] CrashTestDummy> Moved forward by 10 " +
                        "steps.\n" +
                        "[0,19] CrashTestDummy> Moved forward by 9 " +
                        "steps.\n" +
                        "[0,27] CrashTestDummy> Moved forward by 8 " +
                        "steps.\n" +
                        "[0,34] CrashTestDummy> Moved forward by 7 " +
                        "steps.\n" +
                        "[0,40] CrashTestDummy> Moved forward by 6 " +
                        "steps.\n" +
                        "[0,45] CrashTestDummy> Moved forward by 5 " +
                        "steps.\n" +
                        "[0,49] CrashTestDummy> Moved forward by 4 " +
                        "steps.\n" +
                        "[0,52] CrashTestDummy> Moved forward by 3 " +
                        "steps.\n" +
                        "[0,54] CrashTestDummy> Moved forward by 2 " +
                        "steps.\n" +
                        "[0,55] CrashTestDummy> Moved forward by 1 " +
                        "steps.",
                robot.showRobotStatus());
    }

    @Test
    void executeReplay() {
        Robot robot = new Robot("CrashTestDummy");
        ForwardCommand forwardCommand5 = new ForwardCommand("5");
        ForwardCommand forwardCommand10 = new ForwardCommand("10");
        RightCommand rightCommand = new RightCommand();
        ReplayCommand replayCommand = new ReplayCommand(new String[] {"4-2"});
        robot.handleCommand(forwardCommand10);
        robot.handleCommand(rightCommand);
        robot.handleCommand(forwardCommand10);
        robot.handleCommand(rightCommand);
        robot.handleCommand(forwardCommand5);
        robot.handleCommand(replayCommand);
        assertEquals("[10,5] CrashTestDummy> Turned right.\n" +
                        "[0,5] CrashTestDummy> Moved forward by 10 steps.\n" +
                        "[0,5] CrashTestDummy> replayed 2 commands.",
                robot.showRobotStatus());
    }

    @Test
    void executeReplaySilent() {
        Robot robot = new Robot("CrashTestDummy");
        ForwardCommand forwardCommand5 = new ForwardCommand("5");
        ForwardCommand forwardCommand10 = new ForwardCommand("10");
        RightCommand rightCommand = new RightCommand();
        ReplayCommand replayCommand = new ReplayCommand(new String[] {
                "silent", "2"});
        robot.handleCommand(forwardCommand10);
        robot.handleCommand(rightCommand);
        robot.handleCommand(forwardCommand10);
        robot.handleCommand(rightCommand);
        robot.handleCommand(forwardCommand5);
        robot.handleCommand(replayCommand);
        assertEquals(
                "[5,5] CrashTestDummy> replayed 2 commands.",
                robot.showRobotStatus());
    }

    @Test
    void executeReplayReversed() {
        Robot robot = new Robot("CrashTestDummy");
        ForwardCommand forwardCommand5 = new ForwardCommand("5");
        ForwardCommand forwardCommand10 = new ForwardCommand("10");
        RightCommand rightCommand = new RightCommand();
        ReplayCommand replayCommand = new ReplayCommand(new String[] {
                "reversed"});
        robot.handleCommand(forwardCommand10);
        robot.handleCommand(rightCommand);
        robot.handleCommand(forwardCommand10);
        robot.handleCommand(rightCommand);
        robot.handleCommand(forwardCommand5);
        robot.handleCommand(replayCommand);
        assertEquals(
                "[10,0] CrashTestDummy> Moved forward by 5 steps.\n" +
                        "[10,0] CrashTestDummy> Turned right.\n" +
                        "[0,0] CrashTestDummy> Moved forward by 10 steps.\n" +
                        "[0,0] CrashTestDummy> Turned right.\n" +
                        "[0,10] CrashTestDummy> Moved forward by 10 steps.\n" +
                        "[0,10] CrashTestDummy> replayed 5 commands."
                , robot.showRobotStatus());
    }

    @Test
    void executeReplayReversedSilent() {
        Robot robot = new Robot("CrashTestDummy");
        ForwardCommand forwardCommand5 = new ForwardCommand("5");
        ForwardCommand forwardCommand10 = new ForwardCommand("10");
        RightCommand rightCommand = new RightCommand();
        ReplayCommand replayCommand = new ReplayCommand(new String[] {
                "reversed", "silent"});
        robot.handleCommand(forwardCommand10);
        robot.handleCommand(rightCommand);
        robot.handleCommand(forwardCommand10);
        robot.handleCommand(rightCommand);
        robot.handleCommand(forwardCommand5);
        robot.handleCommand(replayCommand);
        assertEquals(
                "[0,10] CrashTestDummy> replayed 5 commands.",
                robot.showRobotStatus());
    }

    @Test
    void executeMazeRunTop() {
        Robot robot = new Robot("CrashTestDummy");
        robot.handleCommand(new MazeRunTopCommand());
        assertEquals(
                "[0,0] CrashTestDummy> Starting maze run..\n" +
                        "[0,200] CrashTestDummy> Moved forward by 200 steps." +
                        "\n" +
                        "[0,200] CrashTestDummy> I am at the top edge. " +
                        "(Cost: 1 step)", robot.showRobotStatus());

        robot = new Robot("CrashTestDummy",
                new TextWorld(new SimpleMaze()));
        robot.handleCommand(new RightCommand());
        robot.handleCommand(new ForwardCommand("3"));
        robot.handleCommand(new MazeRunTopCommand());
        assertEquals(
                "[3,0] CrashTestDummy> Starting maze run..\n" +
                        "[6,0] CrashTestDummy> Moved forward by 3 steps.\n" +
                        "[6,0] CrashTestDummy> Turned left.\n" +
                        "[6,200] CrashTestDummy> Moved forward by 200 steps." +
                        "\n" +
                        "[6,200] CrashTestDummy> I am at the top edge. " +
                        "(Cost: 3 steps)", robot.showRobotStatus());
    }

    @Test
    void executeMazeRunBottom() {
        Robot robot = new Robot("CrashTestDummy");
        robot.handleCommand(new MazeRunBottomCommand());
        assertEquals(
                "[0,0] CrashTestDummy> Starting maze run..\n" +
                        "[0,-200] CrashTestDummy> Moved back by 200 steps.\n"
                        +"[0,-200] CrashTestDummy> I am at the bottom edge. " +
                        "(Cost: 1 step)", robot.showRobotStatus());

        robot = new Robot("CrashTestDummy",
                new TextWorld(new SimpleMaze()));
        robot.handleCommand(new ForwardCommand("6"));
        robot.handleCommand(new RightCommand());
        robot.handleCommand(new ForwardCommand("3"));
        robot.handleCommand(new MazeRunBottomCommand());
        assertEquals(
                "[3,6] CrashTestDummy> Starting maze run..\n" +
                        "[6,6] CrashTestDummy> Moved forward by 3 steps.\n" +
                        "[6,6] CrashTestDummy> Turned right.\n" +
                        "[6,-200] CrashTestDummy> Moved forward by 206 " +
                        "steps.\n" +
                        "[6,-200] CrashTestDummy> I am at the bottom edge. " +
                        "(Cost: 3 steps)", robot.showRobotStatus());
    }

    @Test
    void executeMazeRunLeft() {
        Robot robot = new Robot("CrashTestDummy");
        robot.handleCommand(new MazeRunLeftCommand());
        assertEquals(
                "[0,0] CrashTestDummy> Starting maze run..\n" +
                        "[0,0] CrashTestDummy> Turned left.\n" +
                        "[-100,0] CrashTestDummy> Moved forward by 100 steps.\n"
                        + "[-100,0] CrashTestDummy> I am at the left edge. " +
                        "(Cost: 2 steps)", robot.showRobotStatus());

        robot = new Robot("CrashTestDummy",
                new TextWorld(new SimpleMaze()));
        robot.handleCommand(new RightCommand());
        robot.handleCommand(new ForwardCommand("6"));
        robot.handleCommand(new LeftCommand());
        robot.handleCommand(new ForwardCommand("3"));
        robot.handleCommand(new MazeRunLeftCommand());
        assertEquals(
                "[6,3] CrashTestDummy> Starting maze run..\n" +
                        "[6,6] CrashTestDummy> Moved forward by 3 steps.\n" +
                        "[6,6] CrashTestDummy> Turned left.\n" +
                        "[-100,6] CrashTestDummy> Moved forward by 106 " +
                        "steps.\n" +
                        "[-100,6] CrashTestDummy> I am at the left edge. " +
                        "(Cost: 3 steps)", robot.showRobotStatus());
    }

    @Test
    void executeMazeRunRight() {
        Robot robot = new Robot("CrashTestDummy");
        robot.handleCommand(new MazeRunRightCommand());
        assertEquals(
                "[0,0] CrashTestDummy> Starting maze run..\n" +
                        "[0,0] CrashTestDummy> Turned right.\n" +
                        "[100,0] CrashTestDummy> Moved forward by 100 steps.\n"
                        + "[100,0] CrashTestDummy> I am at the right edge. " +
                        "(Cost: 2 steps)", robot.showRobotStatus());

        robot = new Robot("CrashTestDummy",
                new TextWorld(new SimpleMaze()));
        robot.handleCommand(new ForwardCommand("3"));
        robot.handleCommand(new MazeRunRightCommand());
        assertEquals(
                "[0,3] CrashTestDummy> Starting maze run..\n" +
                        "[0,6] CrashTestDummy> Moved forward by 3 steps.\n" +
                        "[0,6] CrashTestDummy> Turned right.\n" +
                        "[100,6] CrashTestDummy> Moved forward by 100 steps.\n"
                        + "[100,6] CrashTestDummy> I am at the right edge." +
                        " (Cost: 3 steps)", robot.showRobotStatus());
    }
}
