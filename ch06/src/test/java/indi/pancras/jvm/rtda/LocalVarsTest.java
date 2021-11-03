package indi.pancras.jvm.rtda;

import org.junit.jupiter.api.Test;

import indi.pancras.jvm.rtda.base.Reference;
import indi.pancras.jvm.rtda.stack.LocalVars;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class LocalVarsTest {
    @Test
    void testAll() {
        LocalVars localVars = new LocalVars(20);

        localVars.setInt(0, 100);
        localVars.setInt(1, -100);
        localVars.setLong(2, -100L);
        localVars.setLong(4, -200L);
        localVars.setDouble(6, -200.5D);
        localVars.setRef(8, null);
        localVars.setRef(9, new Reference("abc"));

        assertEquals(100, localVars.getInt(0));
        assertEquals(-100, localVars.getInt(1));
        assertEquals(-100L, localVars.getLong(2));
        assertEquals(-200L, localVars.getLong(4));
        assertEquals(-200.5D, localVars.getDouble(6));
        assertNull(localVars.getRef(8));
        assertEquals("abc", localVars.getRef(9).getValue());
    }
}