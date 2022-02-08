package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Reference;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;


public class CAload extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x34;
    }

    @Override
    public String getOpName() {
        return "caload";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int index = stack.popInt();
        Reference arrRef = stack.popRef();

        stack.pushInt(arrRef.getTarget().chars()[index]);
    }
}
