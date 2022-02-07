package indi.pancras.jvm.instruction.control;

import indi.pancras.jvm.instruction.BaseBranch;
import indi.pancras.jvm.instruction.base.BytecodeReader;
import indi.pancras.jvm.rtda.JThread;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;


public class Lookupswitch extends BaseBranch {
    private int defaultOffset;

    private int npairs;

    private int[] matchOffsets;

    @Override
    public int getOpCode() {
        return 0xab;
    }

    @Override
    public String getOpName() {
        return "lookupswitch";
    }

    @Override
    public void fetchOperands(BytecodeReader reader) {
        reader.skipPadding();
        defaultOffset = reader.read32();
        npairs = reader.read32();
        matchOffsets = reader.readInt32s(npairs * 2);
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        JThread thread = frame.getThread();
        int key = operandStack.popInt();
        for (int i = 0; i < npairs * 2; i += 2) {
            if (matchOffsets[i] == key) {
                offset = matchOffsets[i + 1];
                thread.setPc(offset);
                return;
            }
        }
        thread.setPc(defaultOffset);
    }
}
