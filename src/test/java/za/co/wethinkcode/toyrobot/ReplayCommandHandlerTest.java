package za.co.wethinkcode.toyrobot;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.toyrobot.commandhandler.ReplayCommandHandler;
import za.co.wethinkcode.toyrobot.commands.replaycommand.ReplayCommand;

import static org.junit.jupiter.api.Assertions.*;

public class ReplayCommandHandlerTest {
    @Test
    void testCheckCommand() {
        ReplayCommandHandler replayCommandHandler = new ReplayCommandHandler();
        assertTrue(replayCommandHandler.checkCommand(
                "replay", new String[] {""}));

        replayCommandHandler = new ReplayCommandHandler();
        assertTrue(replayCommandHandler.checkCommand(
                "replay", new String[] {"3"}));

        replayCommandHandler = new ReplayCommandHandler();
        assertTrue(replayCommandHandler.checkCommand(
                "replay", new String[] {"4-2"}));

        replayCommandHandler = new ReplayCommandHandler();
        assertTrue(replayCommandHandler.checkCommand(
                "replay", new String[] {"silent"}));

        replayCommandHandler = new ReplayCommandHandler();
        assertTrue(replayCommandHandler.checkCommand(
                "replay", new String[] {"reversed"}));

        replayCommandHandler = new ReplayCommandHandler();
        assertTrue(replayCommandHandler.checkCommand(
                "replay", new String[] {"3", "silent"}));

        replayCommandHandler = new ReplayCommandHandler();
        assertTrue(replayCommandHandler.checkCommand(
                "replay", new String[] {"reversed", "5-2"}));

        replayCommandHandler = new ReplayCommandHandler();
        assertTrue(replayCommandHandler.checkCommand(
                "replay", new String[] {"silent", "4", "reversed"}));

        replayCommandHandler = new ReplayCommandHandler();
        assertFalse(replayCommandHandler.checkCommand(
                "replay", new String[] {"lol"}));

        replayCommandHandler = new ReplayCommandHandler();
        assertFalse(replayCommandHandler.checkCommand(
                "replay", new String[] {"silent", "ui"}));

        replayCommandHandler = new ReplayCommandHandler();
        assertFalse(replayCommandHandler.checkCommand(
                "replay", new String[] {"1-2"}));

        replayCommandHandler = new ReplayCommandHandler();
        assertFalse(replayCommandHandler.checkCommand(
                "replay", new String[] {"reversed", "silent", "a"}));

        replayCommandHandler = new ReplayCommandHandler();
        assertFalse(replayCommandHandler.checkCommand(
                "replay", new String[] {"1", "2", "3"}));

        replayCommandHandler = new ReplayCommandHandler();
        assertFalse(replayCommandHandler.checkCommand(
                "replay", new String[] {"silent", "silent"}));

        replayCommandHandler = new ReplayCommandHandler();
        assertFalse(replayCommandHandler.checkCommand(
                "lol", new String[] {""}));
    }

    @Test
    void testGetCommand() {
        ReplayCommandHandler replayCommandHandler =
                new ReplayCommandHandler();
        assertSame(replayCommandHandler.getCommand().getClass(),
                ReplayCommand.class);
    }
}
