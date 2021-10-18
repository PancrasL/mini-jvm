package indi.pancras.jvm;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author PancrasL
 */
class CmdTest {

    @Test
    void testParseCmd() {
        String[] args1 = {"-v", "--help"};
        Cmd cmd = Cmd.parseCmd(args1);
        assertTrue(cmd.isHelpFLag());
        assertTrue(cmd.isVersionFlag());
        assertNull(cmd.getClasspath());
        assertNull(cmd.getAppArgs());

        String[] args2 = {"-cp", "D:/lib/", "java.lang.String", "1", "2", "3"};
        cmd = Cmd.parseCmd(args2);
        assertEquals("D:/lib/", cmd.getClasspath());
        assertEquals("java.lang.String", cmd.getMainClass());
        assertIterableEquals(Arrays.asList("1", "2", "3"), cmd.getAppArgs());
    }
}