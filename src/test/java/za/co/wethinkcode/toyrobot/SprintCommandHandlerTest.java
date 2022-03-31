package za.co.wethinkcode.toyrobot;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.toyrobot.commandhandler.SprintCommandHandler;
import za.co.wethinkcode.toyrobot.commands.movementcommands.SprintCommand;

import static org.junit.jupiter.api.Assertions.*;

public class SprintCommandHandlerTest {
    @Test
    void testCheckCommand() {
        SprintCommandHandler sprintCommandHandler =
                new SprintCommandHandler();
        assertTrue(sprintCommandHandler.checkCommand(
                "sprint", new String[]{"10"}));
        assertFalse(sprintCommandHandler.checkCommand(
                "sprint", new String[]{"-1"}));
        assertFalse(sprintCommandHandler.checkCommand(
                "sprint", new String[]{"lollipop"}));
        assertFalse(sprintCommandHandler.checkCommand(
                "sprint", new String[]{""}));
        assertFalse(sprintCommandHandler.checkCommand(
                "lol", new String[]{"1", "lol"}));
    }

    @Test
    void testGetCommand() {
        SprintCommandHandler SprintCommandHandler =
                new SprintCommandHandler();
        assertSame(SprintCommandHandler.getCommand().getClass(),
                SprintCommand.class);
    }
}
