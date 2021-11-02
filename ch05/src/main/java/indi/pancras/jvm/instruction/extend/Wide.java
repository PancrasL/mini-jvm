package indi.pancras.jvm.instruction.extend;


import indi.pancras.jvm.instruction.BaseBranch;
import indi.pancras.jvm.instruction.BytecodeReader;
import indi.pancras.jvm.instruction.Instruction;
import indi.pancras.jvm.instruction.InstructionFactory;
import indi.pancras.jvm.instruction.load.Aload;
import indi.pancras.jvm.instruction.load.Dload;
import indi.pancras.jvm.instruction.load.Fload;
import indi.pancras.jvm.instruction.load.Iload;
import indi.pancras.jvm.instruction.load.Lload;
import indi.pancras.jvm.instruction.math.Iinc;
import indi.pancras.jvm.instruction.store.Astore;
import indi.pancras.jvm.instruction.store.Dstore;
import indi.pancras.jvm.instruction.store.Fstore;
import indi.pancras.jvm.instruction.store.Istore;
import indi.pancras.jvm.instruction.store.Lstore;
import indi.pancras.jvm.rtda.Frame;

public class Wide extends BaseBranch {

    private Instruction modifiedInstruction;

    @Override
    public int getOpCode() {
        return 0xc4;
    }

    @Override
    public String getOpName() {
        return "wide";
    }

    @Override
    public void fetchOperands(BytecodeReader reader) {
        int opcode = reader.read8();
        switch (opcode) {
            case 0x15:
                Iload a = (Iload) InstructionFactory.getByOpcode(0x15);
                a.setIndex(reader.read16());
                modifiedInstruction = a;
                break;
            case 0x16:
                Lload b = (Lload) InstructionFactory.getByOpcode(0x16);
                b.setIndex(reader.read16());
                modifiedInstruction = b;
                break;
            case 0x17:
                Fload c = (Fload) InstructionFactory.getByOpcode(0x17);
                c.setIndex(reader.read16());
                modifiedInstruction = c;
                break;
            case 0x18:
                Dload d = (Dload) InstructionFactory.getByOpcode(0x18);
                d.setIndex(reader.read16());
                modifiedInstruction = d;
                break;
            case 0x19:
                Aload e = (Aload) InstructionFactory.getByOpcode(0x19);
                e.setIndex(reader.read16());
                modifiedInstruction = e;
                break;
            case 0x36:
                Istore f = (Istore) InstructionFactory.getByOpcode(0x36);
                f.setIndex(reader.read16());
                modifiedInstruction = f;
                break;
            case 0x37:
                Lstore g = (Lstore) InstructionFactory.getByOpcode(0x37);
                g.setIndex(reader.read16());
                modifiedInstruction = g;
                break;
            case 0x38:
                Fstore h = (Fstore) InstructionFactory.getByOpcode(0x38);
                h.setIndex(reader.read16());
                modifiedInstruction = h;
                break;
            case 0x39:
                Dstore i = (Dstore) InstructionFactory.getByOpcode(0x39);
                i.setIndex(reader.read16());
                modifiedInstruction = i;
                break;
            case 0x3a:
                Astore j = (Astore) InstructionFactory.getByOpcode(0x3a);
                j.setIndex(reader.read16());
                modifiedInstruction = j;
                break;
            case 0x84:
                Iinc k = (Iinc) InstructionFactory.getByOpcode(0x84);
                k.setIndex(reader.read16());
                k.setConstVal(reader.read16());
                modifiedInstruction = k;
                break;
            case 0xa9:
                throw new RuntimeException("not support ret");
            default:
                throw new RuntimeException("Not support code: " + opcode);
        }
    }

    @Override
    public void execute(Frame frame) {
        modifiedInstruction.execute(frame);
    }
}
