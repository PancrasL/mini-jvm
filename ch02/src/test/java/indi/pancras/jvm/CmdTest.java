package indi.pancras.jvm;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author PancrasL
 */
class CmdTest {

    @Test
    void testParseCmd() {
        String[] args1 = {"-v", "--help"};
        Cmd cmd = Cmd.parseCmd(args1);
        assertTrue(cmd.isHelpFlag());
        assertTrue(cmd.isVersionFlag());
        assertEquals("", cmd.getClasspath());
        assertEquals("", cmd.getMainClass());
        assertEquals(0, cmd.getAppArgs().size());

        String[] args2 = {"-cp", "D:/lib/", "java.lang.String", "1", "2", "3"};
        cmd = Cmd.parseCmd(args2);
        assertFalse(cmd.isHelpFlag());
        assertFalse(cmd.isVersionFlag());
        assertEquals("D:/lib/", cmd.getClasspath());
        assertEquals("java.lang.String", cmd.getMainClass());
        assertIterableEquals(Arrays.asList("1", "2", "3"), cmd.getAppArgs());
    }
}