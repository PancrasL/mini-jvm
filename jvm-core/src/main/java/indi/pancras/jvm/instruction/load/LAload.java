package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Reference;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;


public class LAload extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x2f;
    }

    @Override
    public String getOpName() {
        return "laload";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int index = stack.popInt();
        Reference arrRef = stack.popRef();
        stack.pushLong(arrRef.getTarget().longs()[index]);
    }
}
