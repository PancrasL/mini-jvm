package indi.pancras.jvm.instruction.references;

import indi.pancras.jvm.instruction.BaseIndex16;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.base.Reference;
import indi.pancras.jvm.rtda.heap.JClass;
import indi.pancras.jvm.rtda.heap.RuntimeConstantPool;
import indi.pancras.jvm.rtda.stack.OperandStack;

public class Checkcast extends BaseIndex16 {
    @Override
    public int getOpCode() {
        return 0xc0;
    }

    @Override
    public String getOpName() {
        return "checkcast";
    }

    @Override
    public void execute(Frame frame) {
        // 弹出对象引用
        OperandStack operandStack = frame.getOperandStack();
        Reference ref = operandStack.popRef();
        operandStack.pushRef(ref);
        if (ref.targetIsNull()) {
            return;
        }

        // 进行判断
        RuntimeConstantPool currentPool = frame.getMethod().getClazz().getConstantPool();
        JClass clazz = currentPool.getClassRef(index).getTargetClazz();
        if (!ref.getTarget().isInstanceOf(clazz)) {
            throw new ClassCastException();
        }
    }
}
