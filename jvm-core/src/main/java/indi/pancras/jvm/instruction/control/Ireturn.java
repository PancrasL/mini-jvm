package indi.pancras.jvm.instruction.control;


import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.JThread;
import indi.pancras.jvm.rtda.stack.Frame;

public class Ireturn extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0xac;
    }

    @Override
    public String getOpName() {
        return "ireturn";
    }

    @Override
    public void execute(Frame frame) {
        JThread thread = frame.getJThread();
        Frame currentFrame = thread.popFrame();
        Frame invokerFrame = thread.topFrame();
        int val = currentFrame.getOperandStack().popInt();
        invokerFrame.getOperandStack().pushInt(val);
    }
}
