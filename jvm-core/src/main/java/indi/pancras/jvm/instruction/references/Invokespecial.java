package indi.pancras.jvm.instruction.references;

import indi.pancras.jvm.instruction.BaseIndex16;
import indi.pancras.jvm.rtda.Reference;
import indi.pancras.jvm.rtda.RuntimeConstantPool;
import indi.pancras.jvm.rtda.heap.JClass;
import indi.pancras.jvm.rtda.heap.Method;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.symbolref.MethodRef;

public class Invokespecial extends BaseIndex16 {
    @Override
    public int getOpCode() {
        return 0xb7;
    }

    @Override
    public String getOpName() {
        return "invokespecial";
    }

    @Override
    public void execute(Frame frame) {
        JClass clazz = frame.getMethod().getClazz();
        RuntimeConstantPool pool = clazz.getConstantPool();
        MethodRef methodRef = pool.getMethodRef(index);

        Method targetMethod = methodRef.getTargetMethod();
        JClass targetClazz = methodRef.getTargetClazz();
        // 如果从符号引用中解析出来的类是C，方法是M，如果方法是构造函数，则声明M的类必须是C
        if ("<init>".equals(targetMethod.getMethodName()) && !targetMethod.getClazz().equals(methodRef.getTargetClazz())) {
            throw new NoSuchMethodError();
        }
        if (targetMethod.isStatic()) {
            throw new IncompatibleClassChangeError();
        }
        Reference ref = frame.getOperandStack().getRefFromTop(targetMethod.getArgSlotCount());
        if (ref.targetIsNull()) {
            throw new NullPointerException();
        }
    }
}
