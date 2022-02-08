package indi.pancras.jvm.instruction.constant;

import indi.pancras.jvm.instruction.BaseIndex16;
import indi.pancras.jvm.rtda.Reference;
import indi.pancras.jvm.rtda.RuntimeConstantPool;
import indi.pancras.jvm.rtda.heap.JClass;
import indi.pancras.jvm.rtda.heap.JObject;
import indi.pancras.jvm.rtda.heap.StringPool;
import indi.pancras.jvm.rtda.stack.Frame;
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
        JClass clazz = frame.getMethod().getClazz();
        RuntimeConstantPool pool = clazz.getConstantPool();
        String type = pool.getConstantValueType(index);
        switch (type) {
            case "int":
                operandStack.pushInt(pool.getInt(index));
                break;
            case "float":
                operandStack.pushFloat(pool.getFloat(index));
                break;
            case "string":
                JObject jStr = StringPool.getJString(clazz.getClassLoader(), pool.getString(index));
                operandStack.pushRef(new Reference(jStr));
                break;
            case "ref":
                throw new RuntimeException("Haven't implemented");
            default:
                throw new ClassFormatError();
        }
    }
}
