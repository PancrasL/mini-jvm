package indi.pancras.jvm.instruction.control;

import indi.pancras.jvm.instruction.BaseBranch;
import indi.pancras.jvm.instruction.BytecodeReader;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.JThread;
import indi.pancras.jvm.rtda.OperandStack;


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
        JThread JThread = frame.getJThread();
        int key = operandStack.popInt();
        for (int i = 0; i < npairs * 2; i += 2) {
            if (matchOffsets[i] == key) {
                offset = matchOffsets[i + 1];
                JThread.setPc(offset);
                return;
            }
        }
        JThread.setPc(defaultOffset);
    }
}
