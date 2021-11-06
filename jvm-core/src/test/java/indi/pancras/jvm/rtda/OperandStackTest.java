package indi.pancras.jvm.rtda;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import indi.pancras.jvm.rtda.stack.OperandStack;

import static org.junit.jupiter.api.Assertions.assertEquals;


class OperandStackTest {
    private OperandStack operandStack;

    @BeforeEach
    void init() {
        operandStack = new OperandStack(5);
    }

    @Test
    void testInt() {
        operandStack.pushInt(1);
        operandStack.pushInt(2);

        assertEquals(2, operandStack.popInt());
        assertEquals(1, operandStack.popInt());
    }

    @Test
    void testFloat() {
        operandStack.pushFloat(0.5f);
        operandStack.pushFloat(1.5f);

        assertEquals(1.5f, operandStack.popFloat());
        assertEquals(0.5f, operandStack.popFloat());
    }

    @Test
    void testLong() {
        operandStack.pushLong(1L);
        operandStack.pushLong(2L);
        assertEquals(2L, operandStack.popLong());
        assertEquals(1L, operandStack.popLong());
    }

    @Test
    void testDouble() {
        operandStack.pushDouble(100.0D);
        operandStack.pushDouble(200.0D);
        assertEquals(200.0D, operandStack.popDouble());
        assertEquals(100.0D, operandStack.popDouble());
    }
}