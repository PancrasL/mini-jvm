package indi.pancras.jvm.utils;

import indi.pancras.jvm.rtda.JThread;
import indi.pancras.jvm.rtda.heap.Method;
import indi.pancras.jvm.rtda.stack.Frame;

public class InstructionUtil {

    public static void invokeMethod(Frame frame, Method method){
        // 栈帧入栈
        JThread thread = frame.getJThread();
        thread.pushFrame(new Frame(thread, method));

        // 参数传递

    }
}
