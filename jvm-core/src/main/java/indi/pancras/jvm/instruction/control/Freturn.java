package indi.pancras.jvm.instruction.control;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.JThread;
import indi.pancras.jvm.rtda.stack.Frame;

public class Freturn extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0xae;
    }

    @Override
    public String getOpName() {
        return "freturn";
    }

    @Override
    public void execute(Frame frame) {
        JThread thread = frame.getJThread();
        Frame currentFrame = thread.popFrame();
        Frame invokerFrame = thread.topFrame();
        float val = currentFrame.getOperandStack().popFloat();
        invokerFrame.getOperandStack().pushFloat(val);
    }
}
