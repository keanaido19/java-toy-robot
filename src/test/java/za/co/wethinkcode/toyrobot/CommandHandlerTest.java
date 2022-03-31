package za.co.wethinkcode.toyrobot;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.toyrobot.commandhandler.CommandHandler;
import za.co.wethinkcode.toyrobot.commands.*;
import za.co.wethinkcode.toyrobot.commands.mazeruncommands.MazeRunBottomCommand;
import za.co.wethinkcode.toyrobot.commands.mazeruncommands.MazeRunLeftCommand;
import za.co.wethinkcode.toyrobot.commands.mazeruncommands.MazeRunRightCommand;
import za.co.wethinkcode.toyrobot.commands.mazeruncommands.MazeRunTopCommand;
import za.co.wethinkcode.toyrobot.commands.movementcommands.*;
import za.co.wethinkcode.toyrobot.commands.replaycommand.ReplayCommand;

import static org.junit.jupiter.api.Assertions.*;

public class CommandHandlerTest {
    @Test
    void testGetCommandInput() {
        CommandHandler handler = new CommandHandler(
                "test command");
        assertEquals("test command", handler.getCommandInput());
    }

    @Test
    void testGetCommand() {
        CommandHandler handler = new CommandHandler(
                "test command 2");
        assertEquals("test", handler.getCommand());
        handler = new CommandHandler("TesT");
        assertEquals("test", handler.getCommand());
        handler = new CommandHandler("");
        assertEquals("", handler.getCommand());
    }

    @Test
    void testGetCommandArguments() {
        CommandHandler handler = new CommandHandler(
                "test command 3 foUr");
        assertArrayEquals(handler.getCommandArguments(),
                new String[]{"command", "3", "four"});
        handler = new CommandHandler("test");
        assertArrayEquals(handler.getCommandArguments(), new String[]{""});
    }

    @Test
    void testGetShutdownCommand() {
        CommandHandler handler = new CommandHandler(
                "off");
        Command robotCommand = handler.getRobotCommand();
        assertSame(ShutdownCommand.class, robotCommand.getClass());

        handler = new CommandHandler("oFf");
        robotCommand = handler.getRobotCommand();
        assertSame(ShutdownCommand.class, robotCommand.getClass());

        handler = new CommandHandler("off lol");
        robotCommand = handler.getRobotCommand();
        assertSame(NullCommand.class, robotCommand.getClass());
    }

    @Test
    void testGetHelpCommand() {
        CommandHandler handler = new CommandHandler(
                "help");
        Command robotCommand = handler.getRobotCommand();
        assertSame(HelpCommand.class, robotCommand.getClass());

        handler = new CommandHandler("HELp");
        robotCommand = handler.getRobotCommand();
        assertSame(HelpCommand.class, robotCommand.getClass());

        handler = new CommandHandler("Help lol");
        robotCommand = handler.getRobotCommand();
        assertSame(NullCommand.class, robotCommand.getClass());
    }

    @Test
    void testGetForwardCommand() {
        CommandHandler handler = new CommandHandler(
                "forward 10");
        Command robotCommand = handler.getRobotCommand();
        assertSame(ForwardCommand.class, robotCommand.getClass());

        handler = new CommandHandler("fOrWard 20");
        robotCommand = handler.getRobotCommand();
        assertSame(ForwardCommand.class, robotCommand.getClass());

        handler = new CommandHandler("forward -2233");
        robotCommand = handler.getRobotCommand();
        assertSame(NullCommand.class, robotCommand.getClass());

        handler = new CommandHandler("forward lol");
        robotCommand = handler.getRobotCommand();
        assertSame(NullCommand.class, robotCommand.getClass());
    }

    @Test
    void testGetBackCommand() {
        CommandHandler handler = new CommandHandler(
                "back 10");
        Command robotCommand = handler.getRobotCommand();
        assertSame(BackCommand.class, robotCommand.getClass());

        handler = new CommandHandler("BaCK 20");
        robotCommand = handler.getRobotCommand();
        assertSame(BackCommand.class, robotCommand.getClass());

        handler = new CommandHandler("back -2233");
        robotCommand = handler.getRobotCommand();
        assertSame(NullCommand.class, robotCommand.getClass());

        handler = new CommandHandler("back lol");
        robotCommand = handler.getRobotCommand();
        assertSame(NullCommand.class, robotCommand.getClass());
    }

    @Test
    void testGetRightCommand() {
        CommandHandler handler = new CommandHandler(
                "right");
        Command robotCommand = handler.getRobotCommand();
        assertSame(RightCommand.class, robotCommand.getClass());

        handler = new CommandHandler("RigHt");
        robotCommand = handler.getRobotCommand();
        assertSame(RightCommand.class, robotCommand.getClass());

        handler = new CommandHandler("right -2233");
        robotCommand = handler.getRobotCommand();
        assertSame(NullCommand.class, robotCommand.getClass());

        handler = new CommandHandler("right lol");
        robotCommand = handler.getRobotCommand();
        assertSame(NullCommand.class, robotCommand.getClass());
    }

    @Test
    void testGetLeftCommand() {
        CommandHandler handler = new CommandHandler(
                "left");
        Command robotCommand = handler.getRobotCommand();
        assertSame(LeftCommand.class, robotCommand.getClass());

        handler = new CommandHandler("lEfT");
        robotCommand = handler.getRobotCommand();
        assertSame(LeftCommand.class, robotCommand.getClass());

        handler = new CommandHandler("left -2233");
        robotCommand = handler.getRobotCommand();
        assertSame(NullCommand.class, robotCommand.getClass());

        handler = new CommandHandler("left lol");
        robotCommand = handler.getRobotCommand();
        assertSame(NullCommand.class, robotCommand.getClass());
    }

    @Test
    void testGetSprintCommand() {
        CommandHandler handler = new CommandHandler(
                "sprint 10");
        Command robotCommand = handler.getRobotCommand();
        assertSame(SprintCommand.class, robotCommand.getClass());

        handler = new CommandHandler("sPrInt 20");
        robotCommand = handler.getRobotCommand();
        assertSame(SprintCommand.class, robotCommand.getClass());

        handler = new CommandHandler("sprint -2233");
        robotCommand = handler.getRobotCommand();
        assertSame(NullCommand.class, robotCommand.getClass());

        handler = new CommandHandler("sprint ?");
        robotCommand = handler.getRobotCommand();
        assertSame(NullCommand.class, robotCommand.getClass());
    }

    @Test
    void testGetReplayCommand() {
        CommandHandler handler = new CommandHandler(
                "replay");
        Command robotCommand = handler.getRobotCommand();
        assertSame(ReplayCommand.class, robotCommand.getClass());

        handler = new CommandHandler("RepLAy 3");
        robotCommand = handler.getRobotCommand();
        assertSame(ReplayCommand.class, robotCommand.getClass());

        handler = new CommandHandler("replay silent");
        robotCommand = handler.getRobotCommand();
        assertSame(ReplayCommand.class, robotCommand.getClass());

        handler = new CommandHandler("replay reversed 3-1");
        robotCommand = handler.getRobotCommand();
        assertSame(ReplayCommand.class, robotCommand.getClass());

        handler = new CommandHandler(
                "replay 4 silent reversed");
        robotCommand = handler.getRobotCommand();
        assertSame(ReplayCommand.class, robotCommand.getClass());

        handler = new CommandHandler(
                "replay 3-2 silent reversed");
        robotCommand = handler.getRobotCommand();
        assertSame(ReplayCommand.class, robotCommand.getClass());

        handler = new CommandHandler("replay -1");
        robotCommand = handler.getRobotCommand();
        assertSame(NullCommand.class, robotCommand.getClass());

        handler = new CommandHandler("replay 3-7");
        robotCommand = handler.getRobotCommand();
        assertSame(NullCommand.class, robotCommand.getClass());

        handler = new CommandHandler("replay 3 3 3");
        robotCommand = handler.getRobotCommand();
        assertSame(NullCommand.class, robotCommand.getClass());

        handler = new CommandHandler(
                "replay silent 3 reversed 4-2");
        robotCommand = handler.getRobotCommand();
        assertSame(NullCommand.class, robotCommand.getClass());

        handler = new CommandHandler("lollipop");
        robotCommand = handler.getRobotCommand();
        assertSame(NullCommand.class, robotCommand.getClass());

        handler = new CommandHandler("replay replay lol");
        robotCommand = handler.getRobotCommand();
        assertSame(NullCommand.class, robotCommand.getClass());
    }

    @Test
    void testGetMazeRunCommand() {
        CommandHandler handler = new CommandHandler(
                "mazerun");
        Command robotCommand = handler.getRobotCommand();
        assertSame(MazeRunTopCommand.class, robotCommand.getClass());

        handler = new CommandHandler("mazErUn");
        robotCommand = handler.getRobotCommand();
        assertSame(MazeRunTopCommand.class, robotCommand.getClass());

        handler = new CommandHandler("mazeRun Top");
        robotCommand = handler.getRobotCommand();
        assertSame(MazeRunTopCommand.class, robotCommand.getClass());

        handler = new CommandHandler("mazErUn lefT");
        robotCommand = handler.getRobotCommand();
        assertSame(MazeRunLeftCommand.class, robotCommand.getClass());

        handler = new CommandHandler("MAZERUN right");
        robotCommand = handler.getRobotCommand();
        assertSame(MazeRunRightCommand.class, robotCommand.getClass());

        handler = new CommandHandler("mazerun bottom");
        robotCommand = handler.getRobotCommand();
        assertSame(MazeRunBottomCommand.class, robotCommand.getClass());

        handler = new CommandHandler("mazerun lol");
        robotCommand = handler.getRobotCommand();
        assertSame(NullCommand.class, robotCommand.getClass());

        handler = new CommandHandler("mazerun 1");
        robotCommand = handler.getRobotCommand();
        assertSame(NullCommand.class, robotCommand.getClass());

        handler = new CommandHandler("mazerun right left");
        robotCommand = handler.getRobotCommand();
        assertSame(NullCommand.class, robotCommand.getClass());

        handler = new CommandHandler("mazerunn");
        robotCommand = handler.getRobotCommand();
        assertSame(NullCommand.class, robotCommand.getClass());
    }
}
