package indi.pancras.jvm.instruction.references;

import indi.pancras.jvm.instruction.BaseIndex16;
import indi.pancras.jvm.rtda.Reference;
import indi.pancras.jvm.rtda.RuntimeConstantPool;
import indi.pancras.jvm.rtda.heap.JClass;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;

/**
 * 判断某个对象是某个类的实例，包含两个操作数：
 * <p>
 * 1. int16符号引用的索引（字节码） 2. 对象引用（操作数栈）
 * </p>
 */
public class Instanceof extends BaseIndex16 {
    @Override
    public int getOpCode() {
        return 0xc1;
    }

    @Override
    public String getOpName() {
        return "instanceof";
    }

    @Override
    public void execute(Frame frame) {
        // 弹出对象引用
        OperandStack operandStack = frame.getOperandStack();
        Reference ref = operandStack.popRef();
        if (ref.targetIsNull()) {
            operandStack.pushInt(0);
            return;
        }

        // 进行判断
        RuntimeConstantPool currentPool = frame.getMethod().getClazz().getConstantPool();
        JClass clazz = currentPool.getClassRef(index).resolvedClass();
        if (ref.getTarget().isInstanceOf(clazz)) {
            operandStack.pushInt(1);
        } else {
            operandStack.pushInt(0);
        }
    }
}
