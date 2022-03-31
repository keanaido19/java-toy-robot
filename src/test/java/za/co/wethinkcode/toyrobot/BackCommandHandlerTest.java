package za.co.wethinkcode.toyrobot;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.toyrobot.commandhandler.BackCommandHandler;
import za.co.wethinkcode.toyrobot.commands.movementcommands.BackCommand;

import static org.junit.jupiter.api.Assertions.*;

public class BackCommandHandlerTest {
    @Test
    void testCheckCommand() {
        BackCommandHandler backCommandHandler =
                new BackCommandHandler();
        assertTrue(backCommandHandler.checkCommand(
                "back", new String[]{"10"}));
        assertFalse(backCommandHandler.checkCommand(
                "back", new String[]{"-1"}));
        assertFalse(backCommandHandler.checkCommand(
                "back", new String[]{"lollipop"}));
        assertFalse(backCommandHandler.checkCommand(
                "back", new String[]{""}));
        assertFalse(backCommandHandler.checkCommand(
                "lol", new String[]{"1", "lol"}));
    }

    @Test
    void testGetCommand() {
        BackCommandHandler backCommandHandler =
                new BackCommandHandler();
        assertSame(backCommandHandler.getCommand().getClass(),
                BackCommand.class);
    }
}
