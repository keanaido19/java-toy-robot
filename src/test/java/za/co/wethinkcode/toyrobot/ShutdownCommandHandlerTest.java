package za.co.wethinkcode.toyrobot;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.toyrobot.commandhandler.ShutdownCommandHandler;
import za.co.wethinkcode.toyrobot.commands.ShutdownCommand;

import static org.junit.jupiter.api.Assertions.*;

public class ShutdownCommandHandlerTest {
    @Test
    void testCheckCommand() {
        ShutdownCommandHandler shutdownCommandHandler =
                new ShutdownCommandHandler();
        assertTrue(shutdownCommandHandler.checkCommand(
                "off", new String[]{""}));
        assertTrue(shutdownCommandHandler.checkCommand(
                "shutdown", new String[]{""}));
        assertFalse(shutdownCommandHandler.checkCommand(
                "off", new String[]{"1"}));
        assertFalse(shutdownCommandHandler.checkCommand(
                "lol", new String[]{"1", "lol"}));
    }

    @Test
    void testGetCommand() {
        ShutdownCommandHandler shutdownCommandHandler =
                new ShutdownCommandHandler();
        assertSame(shutdownCommandHandler.getCommand().getClass(),
                ShutdownCommand.class);
    }
}
