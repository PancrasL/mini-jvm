package indi.pancras.jvm.instruction.references;

import indi.pancras.jvm.instruction.BaseIndex16;
import indi.pancras.jvm.rtda.Reference;
import indi.pancras.jvm.rtda.RuntimeConstantPool;
import indi.pancras.jvm.rtda.heap.JClass;
import indi.pancras.jvm.rtda.heap.JObject;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;

public class Anewarray extends BaseIndex16 {
    @Override
    public int getOpCode() {
        return 0xbd;
    }

    @Override
    public String getOpName() {
        return "anewarray";
    }

    @Override
    public void execute(Frame frame) {
        RuntimeConstantPool pool = frame.getMethod().getClazz().getConstantPool();
        JClass componentClass = pool.getClassRef(index).resolvedClass();
        OperandStack stack = frame.getOperandStack();
        int count = stack.popInt();
        if (count < 0) {
            throw new NegativeArraySizeException();
        }
        JClass arrClass = componentClass.arrayClass();
        JObject arr = arrClass.newArray(count);
        stack.pushRef(new Reference(arr));
    }
}
