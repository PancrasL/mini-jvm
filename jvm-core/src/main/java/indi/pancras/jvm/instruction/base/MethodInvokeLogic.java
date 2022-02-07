package indi.pancras.jvm.instruction.base;

import indi.pancras.jvm.rtda.JThread;
import indi.pancras.jvm.rtda.Slot;
import indi.pancras.jvm.rtda.heap.Method;
import indi.pancras.jvm.rtda.stack.Frame;

public class MethodInvokeLogic {

    /**
     * 为method方法创建一个新栈帧并传递参数
     *
     * @param invokerFrame 当前栈帧，即调用method方法的栈帧
     * @param method       the method
     */
    public static void invokeMethod(Frame invokerFrame, Method method) {
        // 1. 栈帧入栈
        // 1.1 获取当前线程
        JThread thread = invokerFrame.getThread();
        // 1.2 创建栈帧
        Frame newFrame = new Frame(thread, method);
        // 1.3 将新创建的栈帧推入到线程的方法栈中
        thread.pushFrame(newFrame);

        // 2. 传递参数
        // 2.1 确定方法的参数在局部变量表中占用多少位置
        int argSlotCount = method.getArgSlotCount();
        if (argSlotCount > 0) {
            for (int i = argSlotCount - 1; i >= 0; i--) {
                Slot slot = invokerFrame.getOperandStack().popSlot();
                newFrame.getLocalVars().setSlot(i, slot);
            }
        }

        // hack!
        if (method.isNative()) {
            if (method.getMethodName().equals("registerNatives")) {
                thread.popFrame();
            } else {
                throw new RuntimeException("Native method: " + method);
            }
        }
    }
}
