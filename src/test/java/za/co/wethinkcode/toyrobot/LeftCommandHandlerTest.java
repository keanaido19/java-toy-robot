package za.co.wethinkcode.toyrobot;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.toyrobot.commandhandler.LeftCommandHandler;
import za.co.wethinkcode.toyrobot.commands.movementcommands.LeftCommand;

import static org.junit.jupiter.api.Assertions.*;

public class LeftCommandHandlerTest {
    @Test
    void testCheckCommand() {
        LeftCommandHandler leftCommandHandler =
                new LeftCommandHandler();
        assertTrue(leftCommandHandler.checkCommand(
                "left", new String[]{""}));
        assertFalse(leftCommandHandler.checkCommand(
                "left", new String[]{"1"}));
        assertFalse(leftCommandHandler.checkCommand(
                "lol", new String[]{"1", "lol"}));
    }

    @Test
    void testGetCommand() {
        LeftCommandHandler leftCommandHandler =
                new LeftCommandHandler();
        assertSame(leftCommandHandler.getCommand().getClass(),
                LeftCommand.class);
    }
}
