package indi.pancras.jvm.instruction.constant;

import indi.pancras.jvm.instruction.BaseIndex16;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.heap.RuntimeConstantPool;
import indi.pancras.jvm.rtda.stack.OperandStack;


public class LDCW extends BaseIndex16 {
    @Override
    public int getOpCode() {
        return 0x13;
    }

    @Override
    public String getOpName() {
        return "ldc_w";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        RuntimeConstantPool pool = frame.getMethod().getClazz().getConstantPool();
        String type = pool.getConstantValueType(index);
        switch (type) {
            case "int":
                operandStack.pushInt(pool.getInt(index));
                break;
            case "float":
                operandStack.pushFloat(pool.getFloat(index));
                break;
            case "string":
            case "ref":
                throw new RuntimeException("Haven't implemented");
            default:
                throw new ClassFormatError();
        }
    }
}
