package indi.pancras.jvm.instruction.references;

import indi.pancras.jvm.instruction.BaseIndex16;
import indi.pancras.jvm.rtda.Reference;
import indi.pancras.jvm.rtda.heap.JClass;
import indi.pancras.jvm.rtda.heap.Method;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.symbolref.MethodRef;
import indi.pancras.jvm.instruction.base.MethodInvokeLogic;
import indi.pancras.jvm.utils.LookupUtil;

public class Invokevirtual extends BaseIndex16 {
    @Override
    public int getOpCode() {
        return 0xb6;
    }

    @Override
    public String getOpName() {
        return "invokevirtual";
    }

    @Override
    public void execute(Frame frame) {
        // 1. 获取当前栈帧所属的类和方法符号引用
        JClass currentClazz = frame.getMethod().getClazz();
        MethodRef methodRef = currentClazz.getConstantPool().getMethodRef(index);

        // 2. 获取待触发的方法
        Method method = methodRef.resolvedMethod();
        // 弹出this引用
        Reference ref = frame.getOperandStack().getRefFromTop(method.getArgSlotCount() - 1);
        if (ref.targetIsNull()) {
            throw new NullPointerException("ref is null");
        }
        Method methodToBeInvoked = LookupUtil.lookupMethodInClass(currentClazz, methodRef.getMethodName(), methodRef.getDescriptor(), true);
        MethodInvokeLogic.invokeMethod(frame, methodToBeInvoked);
    }
}
