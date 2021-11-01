package indi.pancras.jvm.instruction.extend;

import com.github.jvmgo.instructions.base.BranchInstruction;
import com.github.jvmgo.rtda.Frame;
import com.github.jvmgo.rtda.OperandStack;

import java.lang.ref.Reference;

public class Ifnull extends BranchInstruction {
    @Override
    public int getOpCode() {
        return 0xc6;
    }

    @Override
    public void execute(Frame frame) throws Exception {
        OperandStack operandStack = frame.getOperandStack();
        Reference reference = operandStack.popRef();
        if (reference.get() == null) {
            branch(frame, offset);
        }
    }
}
