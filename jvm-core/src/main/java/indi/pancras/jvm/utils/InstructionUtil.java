package indi.pancras.jvm.utils;

import indi.pancras.jvm.rtda.JThread;
import indi.pancras.jvm.rtda.Slot;
import indi.pancras.jvm.rtda.heap.Method;
import indi.pancras.jvm.rtda.stack.Frame;

public class InstructionUtil {

    /**
     * 为method方法创建一个新栈帧并传递参数
     *
     * @param invokerFrame 当前栈帧，即调用method方法的栈帧
     * @param method       the method
     */
    public static void invokeMethod(Frame invokerFrame, Method method) {
        // 栈帧入栈
        JThread thread = invokerFrame.getThread();
        Frame newFrame = new Frame(thread, method);
        thread.pushFrame(newFrame);

        // 参数传递
        int argSlotCount = method.getArgSlotCount();
        if (argSlotCount > 0) {
            for (int i = argSlotCount - 1; i >= 0; i--) {
                Slot slot = invokerFrame.getOperandStack().popSlot();
                newFrame.getLocalVars().setSlot(i, slot);
            }
        }
    }
}
