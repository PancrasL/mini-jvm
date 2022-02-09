package indi.pancras.jvm.instruction.reserved;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.natives.NativeMethod;
import indi.pancras.jvm.natives.NativeRegistry;
import indi.pancras.jvm.rtda.heap.Method;
import indi.pancras.jvm.rtda.stack.Frame;

public class Impdep1 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0xfe;
    }

    @Override
    public String getOpName() {
        return "impdep1(invokenative)";
    }

    @Override
    public void execute(Frame frame) {
        Method method = frame.getMethod();
        String className = method.getClazz().getClassName();
        String methodName = method.getMethodName();
        String descriptor = method.getDescriptor();
        NativeMethod nativeMethod = NativeRegistry.INSTANCE.findNativeMethod(className, methodName, descriptor);
        if (nativeMethod == null) {
            throw new UnsatisfiedLinkError(method.toString());
        }
        nativeMethod.invokeMethod(frame);
    }
}
