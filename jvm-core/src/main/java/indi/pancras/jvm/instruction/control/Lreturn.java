package indi.pancras.jvm.instruction.control;


import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.JThread;
import indi.pancras.jvm.rtda.stack.Frame;

public class Lreturn extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0xad;
    }

    @Override
    public String getOpName() {
        return "lreturn";
    }

    @Override
    public void execute(Frame frame) {
        JThread thread = frame.getThread();
        Frame currentFrame = thread.popFrame();
        Frame invokerFrame = thread.topFrame();
        long val = currentFrame.getOperandStack().popLong();
        invokerFrame.getOperandStack().pushLong(val);
    }
}
