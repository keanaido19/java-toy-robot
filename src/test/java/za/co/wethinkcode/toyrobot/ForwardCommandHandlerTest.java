package za.co.wethinkcode.toyrobot;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.toyrobot.commandhandler.ForwardCommandHandler;
import za.co.wethinkcode.toyrobot.commands.movementcommands.ForwardCommand;

import static org.junit.jupiter.api.Assertions.*;

public class ForwardCommandHandlerTest {
    @Test
    void testCheckCommand() {
        ForwardCommandHandler forwardCommandHandler =
                new ForwardCommandHandler();
        assertTrue(forwardCommandHandler.checkCommand(
                "forward", new String[]{"10"}));
        assertFalse(forwardCommandHandler.checkCommand(
                "forward", new String[]{"-1"}));
        assertFalse(forwardCommandHandler.checkCommand(
                "forward", new String[]{"lollipop"}));
        assertFalse(forwardCommandHandler.checkCommand(
                "forward", new String[]{""}));
        assertFalse(forwardCommandHandler.checkCommand(
                "lol", new String[]{"1", "lol"}));
    }

    @Test
    void testGetCommand() {
        ForwardCommandHandler forwardCommandHandler =
                new ForwardCommandHandler();
        assertSame(forwardCommandHandler.getCommand().getClass(),
                ForwardCommand.class);
    }
}
