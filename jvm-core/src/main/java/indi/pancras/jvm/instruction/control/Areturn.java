package indi.pancras.jvm.instruction.control;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.JThread;
import indi.pancras.jvm.rtda.Reference;
import indi.pancras.jvm.rtda.stack.Frame;

public class Areturn extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0xb0;
    }

    @Override
    public String getOpName() {
        return "areturn";
    }

    @Override
    public void execute(Frame frame) {
        JThread thread = frame.getJThread();
        Frame currentFrame = thread.popFrame();
        Frame invokerFrame = thread.topFrame();
        Reference val = currentFrame.getOperandStack().popRef();
        invokerFrame.getOperandStack().pushRef(val);
    }
}
