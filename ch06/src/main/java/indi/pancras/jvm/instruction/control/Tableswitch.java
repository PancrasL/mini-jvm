package indi.pancras.jvm.instruction.control;

import indi.pancras.jvm.instruction.BaseBranch;
import indi.pancras.jvm.instruction.BytecodeReader;
import indi.pancras.jvm.rtda.JThread;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;


public class Tableswitch extends BaseBranch {
    private int defaultOffset;
    private int low;
    private int high;
    private int[] jumpOffsets;

    @Override
    public int getOpCode() {
        return 0xaa;
    }

    @Override
    public String getOpName() {
        return "tableswitch";
    }

    @Override
    public void fetchOperands(BytecodeReader reader) {
        reader.skipPadding();
        defaultOffset = reader.read32();
        low = reader.read32();
        high = reader.read32();

        int jumpOffsetsCount = high - low + 1;
        jumpOffsets = reader.readInt32s(jumpOffsetsCount);
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int index = operandStack.popInt();
        if (index >= low && index <= high) {
            offset = jumpOffsets[index - low];
        } else {
            offset = defaultOffset;
        }
        JThread JThread = frame.getJThread();
        JThread.setPc(offset);
    }
}
