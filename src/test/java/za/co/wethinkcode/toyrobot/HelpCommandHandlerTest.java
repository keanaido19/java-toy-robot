package za.co.wethinkcode.toyrobot;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.toyrobot.commandhandler.HelpCommandHandler;
import za.co.wethinkcode.toyrobot.commands.HelpCommand;

import static org.junit.jupiter.api.Assertions.*;

public class HelpCommandHandlerTest {
    @Test
    void testCheckCommand() {
        HelpCommandHandler helpCommandHandler =
                new HelpCommandHandler();
        assertTrue(helpCommandHandler.checkCommand(
                "help", new String[]{""}));
        assertFalse(helpCommandHandler.checkCommand(
                "help", new String[]{"1"}));
        assertFalse(helpCommandHandler.checkCommand(
                "lol", new String[]{"1", "lol"}));
    }

    @Test
    void testGetCommand() {
        HelpCommandHandler helpCommandHandler =
                new HelpCommandHandler();
        assertSame(helpCommandHandler.getCommand().getClass(),
                HelpCommand.class);
    }
}
