package indi.pancras.jvm.instruction.references;

import indi.pancras.jvm.instruction.Instruction;
import indi.pancras.jvm.instruction.base.BytecodeReader;
import indi.pancras.jvm.rtda.JClassLoader;
import indi.pancras.jvm.rtda.Reference;
import indi.pancras.jvm.rtda.heap.JClass;
import indi.pancras.jvm.rtda.heap.JObject;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;

public class Newarray implements Instruction {
    private static final int AT_BOOLEAN = 4;
    private static final int AT_CHAR = 5;
    private static final int AT_FLOAT = 6;
    private static final int AT_DOUBLE = 7;
    private static final int AT_BYTE = 8;
    private static final int AT_SHORT = 9;
    private static final int AT_INT = 10;
    private static final int AT_LONG = 11;

    private int atype;

    @Override
    public int getOpCode() {
        return 0xbc;
    }

    @Override
    public String getOpName() {
        return "newarray";
    }

    @Override
    public void fetchOperands(BytecodeReader reader) {
        atype = reader.read8();
    }

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int count = stack.popInt();
        if (count < 0) {
            throw new NegativeArraySizeException();
        }
        JClassLoader loader = frame.getMethod().getClazz().getClassLoader();
        JClass arrayClass = getPrimitiveArrayClass(loader, atype);
        JObject arr = arrayClass.newArray(count);
        stack.pushRef(new Reference(arr));
    }

    private JClass getPrimitiveArrayClass(JClassLoader loader, int atype) {
        switch (atype) {
            case AT_BOOLEAN:
                return loader.loadClass("[Z");
            case AT_BYTE:
                return loader.loadClass("[B");
            case AT_CHAR:
                return loader.loadClass("[C");
            case AT_SHORT:
                return loader.loadClass("[S");
            case AT_INT:
                return loader.loadClass("[I");
            case AT_LONG:
                return loader.loadClass("[J");
            case AT_FLOAT:
                return loader.loadClass("[F");
            case AT_DOUBLE:
                return loader.loadClass("[D");
            default:
                throw new RuntimeException("Invalid atype: " + atype);
        }
    }
}
