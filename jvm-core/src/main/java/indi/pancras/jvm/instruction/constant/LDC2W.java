package indi.pancras.jvm.instruction.constant;

import indi.pancras.jvm.instruction.BaseIndex16;
import indi.pancras.jvm.rtda.RuntimeConstantPool;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;


public class LDC2W extends BaseIndex16 {
    @Override
    public int getOpCode() {
        return 0x14;
    }

    @Override
    public String getOpName() {
        return "ldc2_w";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        RuntimeConstantPool pool = frame.getMethod().getClazz().getConstantPool();
        String type = pool.getConstantValueType(index);
        switch (type) {
            case "long":
                operandStack.pushLong(pool.getLong(index));
                break;
            case "double":
                operandStack.pushDouble(pool.getDouble(index));
                break;
            default:
                throw new ClassFormatError();
        }
    }
}
