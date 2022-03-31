package za.co.wethinkcode.toyrobot;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.toyrobot.commandhandler.MazeRunCommandHandler;
import za.co.wethinkcode.toyrobot.commands.mazeruncommands.*;

import static org.junit.jupiter.api.Assertions.*;

public class MazeRunCommandHandlerTest {
    @Test
    void testCheckCommand() {
        MazeRunCommandHandler mazeRunCommandHandler =
                new MazeRunCommandHandler();
        assertTrue(mazeRunCommandHandler.checkCommand(
                "mazerun", new String[]{""}));
        assertTrue(mazeRunCommandHandler.checkCommand(
                "mazerun", new String[]{"top"}));
        assertTrue(mazeRunCommandHandler.checkCommand(
                "mazerun", new String[]{"right"}));
        assertTrue(mazeRunCommandHandler.checkCommand(
                "mazerun", new String[]{"left"}));
        assertTrue(mazeRunCommandHandler.checkCommand(
                "mazerun", new String[]{"bottom"}));
        assertFalse(mazeRunCommandHandler.checkCommand(
                "mazerun", new String[]{"-1"}));
        assertFalse(mazeRunCommandHandler.checkCommand(
                "mazerun", new String[]{"lollipop"}));
        assertFalse(mazeRunCommandHandler.checkCommand(
                "mazerun", new String[]{"left", "right"}));
        assertFalse(mazeRunCommandHandler.checkCommand(
                "lol", new String[]{"1", "lol"}));
    }

    @Test
    void testGetCommand() {
        MazeRunCommandHandler mazeRunCommandHandler =
                new MazeRunCommandHandler();

        mazeRunCommandHandler.checkArguments(new String[] {""});
        assertSame(mazeRunCommandHandler.getCommand().getClass(),
                MazeRunTopCommand.class);

        mazeRunCommandHandler.checkArguments(new String[] {"top"});
        assertSame(mazeRunCommandHandler.getCommand().getClass(),
                MazeRunTopCommand.class);

        mazeRunCommandHandler.checkArguments(new String[] {"right"});
        assertSame(mazeRunCommandHandler.getCommand().getClass(),
                MazeRunRightCommand.class);

        mazeRunCommandHandler.checkArguments(new String[] {"left"});
        assertSame(mazeRunCommandHandler.getCommand().getClass(),
                MazeRunLeftCommand.class);

        mazeRunCommandHandler.checkArguments(new String[] {"bottom"});
        assertSame(mazeRunCommandHandler.getCommand().getClass(),
                MazeRunBottomCommand.class);
    }
}
