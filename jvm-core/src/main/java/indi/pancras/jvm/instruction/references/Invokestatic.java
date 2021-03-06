package indi.pancras.jvm.instruction.references;

import indi.pancras.jvm.instruction.BaseIndex16;
import indi.pancras.jvm.rtda.RuntimeConstantPool;
import indi.pancras.jvm.rtda.heap.Method;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.instruction.base.MethodInvokeLogic;

public class Invokestatic extends BaseIndex16 {
    @Override
    public int getOpCode() {
        return 0xb8;
    }

    @Override
    public String getOpName() {
        return "invokestatic";
    }

    @Override
    public void execute(Frame frame) {
        RuntimeConstantPool pool = frame.getMethod().getClazz().getConstantPool();
        Method method = pool.getMethodRef(index).resolvedMethod();
        if (!method.isStatic()) {// method必须是静态方法
            throw new IncompatibleClassChangeError();
        }
        MethodInvokeLogic.invokeMethod(frame, method);
    }
}
