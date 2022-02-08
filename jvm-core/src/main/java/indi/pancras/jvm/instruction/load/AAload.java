package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Reference;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;


public class AAload extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x32;
    }

    @Override
    public String getOpName() {
        return "aaload";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int index = stack.popInt();
        Reference arrRef = stack.popRef();
        Reference[] refs = arrRef.getTarget().refs();
        checkIndex(refs.length, index);
        stack.pushRef(refs[index]);
    }

    private void checkIndex(int length, int index) {
        if (index < 0 || index >= length) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
