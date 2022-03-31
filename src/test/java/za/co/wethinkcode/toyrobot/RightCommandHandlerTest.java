package za.co.wethinkcode.toyrobot;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.toyrobot.commandhandler.RightCommandHandler;
import za.co.wethinkcode.toyrobot.commands.movementcommands.RightCommand;

import static org.junit.jupiter.api.Assertions.*;

public class RightCommandHandlerTest {
    @Test
    void testCheckCommand() {
        RightCommandHandler rightCommandHandler =
                new RightCommandHandler();
        assertTrue(rightCommandHandler.checkCommand(
                "right", new String[]{""}));
        assertFalse(rightCommandHandler.checkCommand(
                "right", new String[]{"1"}));
        assertFalse(rightCommandHandler.checkCommand(
                "lol", new String[]{"1", "lol"}));
    }

    @Test
    void testGetCommand() {
        RightCommandHandler rightCommandHandler =
                new RightCommandHandler();
        assertSame(rightCommandHandler.getCommand().getClass(),
                RightCommand.class);
    }
}
