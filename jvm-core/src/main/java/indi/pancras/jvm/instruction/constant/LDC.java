package indi.pancras.jvm.instruction.constant;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.ConstantValueType;
import indi.pancras.jvm.rtda.Reference;
import indi.pancras.jvm.rtda.RuntimeConstantPool;
import indi.pancras.jvm.rtda.heap.JClass;
import indi.pancras.jvm.rtda.heap.StringPool;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;


public class LDC extends BaseIndex8 {
    @Override
    public int getOpCode() {
        return 0x12;
    }

    @Override
    public String getOpName() {
        return "ldc";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        JClass clazz = frame.getMethod().getClazz();
        RuntimeConstantPool pool = clazz.getConstantPool();
        ConstantValueType type = pool.getConstantValueType(index);
        switch (type) {
            case INT:
                operandStack.pushInt(pool.getInt(index));
                break;
            case FLOAT:
                operandStack.pushFloat(pool.getFloat(index));
                break;
            case STRING:
                Reference jStr = StringPool.getJString(clazz.getClassLoader(), pool.getString(index));
                operandStack.pushRef(jStr);
                break;
            case REF:
                Reference clazzObj = pool.getClassRef(index).resolvedClass().getClazzObj();
                operandStack.pushRef(clazzObj);
                break;
            default:
                throw new ClassFormatError(type.toString());
        }
    }
}
