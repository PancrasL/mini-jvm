package indi.pancras.jvm.rtda.heap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MethodDescriptorTest {

    @Test
    void testParse() {
        String descriptor0 = "()V";
        MethodDescriptor parsedDes = new MethodDescriptor(descriptor0);
        assertEquals(0, parsedDes.getParamTypes().length);
        assertEquals("V", parsedDes.getReturnType());

        String descriptor1 = "([[Ljava/lang/String;[CII[CIII)[Ljava/lang/String;";
        parsedDes = new MethodDescriptor(descriptor1);
        assertEquals(8, parsedDes.getParamTypes().length);
        assertEquals("[Ljava/lang/String;",parsedDes.getReturnType());
    }

}