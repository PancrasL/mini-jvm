package indi.pancras.jvm.instruction;

import java.util.HashMap;
import java.util.Map;

import indi.pancras.jvm.instruction.comparison.*;
import indi.pancras.jvm.instruction.constant.*;
import indi.pancras.jvm.instruction.control.*;
import indi.pancras.jvm.instruction.conversion.*;
import indi.pancras.jvm.instruction.extend.Gotow;
import indi.pancras.jvm.instruction.extend.Ifnonnull;
import indi.pancras.jvm.instruction.extend.Ifnull;
import indi.pancras.jvm.instruction.extend.Jsrw;
import indi.pancras.jvm.instruction.extend.Multianewarray;
import indi.pancras.jvm.instruction.extend.Wide;
import indi.pancras.jvm.instruction.load.*;
import indi.pancras.jvm.instruction.math.*;
import indi.pancras.jvm.instruction.references.*;
import indi.pancras.jvm.instruction.reserved.Breakpoint;
import indi.pancras.jvm.instruction.reserved.Impdep1;
import indi.pancras.jvm.instruction.reserved.Impdep2;
import indi.pancras.jvm.instruction.stack.*;
import indi.pancras.jvm.instruction.store.*;


public class InstructionFactory {
    private static final Map<Integer, Instruction> codeMap = new HashMap<>();
    private static final Map<String, Instruction> nameMap = new HashMap<>();

    static {
        // Constants
        putInstruction(new Nop());
        putInstruction(new AconstNull());
        putInstruction(new Iconstm1());
        putInstruction(new Iconst0());
        putInstruction(new Iconst1());
        putInstruction(new Iconst2());
        putInstruction(new Iconst3());
        putInstruction(new Iconst4());
        putInstruction(new Iconst5());
        putInstruction(new Lconst0());
        putInstruction(new Lconst1());
        putInstruction(new Fconst0());
        putInstruction(new Fconst1());
        putInstruction(new Fconst2());
        putInstruction(new Dconst0());
        putInstruction(new Dconst1());
        putInstruction(new BIpush());
        putInstruction(new SIpush());
        putInstruction(new LDC());
        putInstruction(new LDCW());
        putInstruction(new LDC2W());

        // Loads
        putInstruction(new Iload());
        putInstruction(new Lload());
        putInstruction(new Fload());
        putInstruction(new Dload());
        putInstruction(new Aload());
        putInstruction(new Iload0());
        putInstruction(new Iload1());
        putInstruction(new Iload2());
        putInstruction(new Iload3());
        putInstruction(new Lload0());
        putInstruction(new Lload1());
        putInstruction(new Lload2());
        putInstruction(new Lload3());
        putInstruction(new Fload0());
        putInstruction(new Fload1());
        putInstruction(new Fload2());
        putInstruction(new Fload3());
        putInstruction(new Dload0());
        putInstruction(new Dload1());
        putInstruction(new Dload2());
        putInstruction(new Dload3());
        putInstruction(new Aload0());
        putInstruction(new Aload1());
        putInstruction(new Aload2());
        putInstruction(new Aload3());
        putInstruction(new IAload());
        putInstruction(new LAload());
        putInstruction(new FAload());
        putInstruction(new DAload());
        putInstruction(new AAload());
        putInstruction(new BAload());
        putInstruction(new CAload());
        putInstruction(new SAload());

        // Store
        putInstruction(new Istore());
        putInstruction(new Lstore());
        putInstruction(new Fstore());
        putInstruction(new Dstore());
        putInstruction(new Astore());
        putInstruction(new Istore0());
        putInstruction(new Istore1());
        putInstruction(new Istore2());
        putInstruction(new Istore3());
        putInstruction(new Lstore0());
        putInstruction(new Lstore1());
        putInstruction(new Lstore2());
        putInstruction(new Lstore3());
        putInstruction(new Fstore0());
        putInstruction(new Fstore1());
        putInstruction(new Fstore2());
        putInstruction(new Fstore3());
        putInstruction(new Dstore0());
        putInstruction(new Dstore1());
        putInstruction(new Dstore2());
        putInstruction(new Dstore3());
        putInstruction(new Astore0());
        putInstruction(new Astore1());
        putInstruction(new Astore2());
        putInstruction(new Astore3());
        putInstruction(new IAstore());
        putInstruction(new LAstore());
        putInstruction(new FAstore());
        putInstruction(new DAstore());
        putInstruction(new AAstore());
        putInstruction(new BAstore());
        putInstruction(new CAstore());
        putInstruction(new SAstore());

        // Stack
        putInstruction(new Pop());
        putInstruction(new Pop2());
        putInstruction(new Dup());
        putInstruction(new Dupx1());
        putInstruction(new Dupx2());
        putInstruction(new Dup2());
        putInstruction(new Dup2x1());
        putInstruction(new Dup2x2());
        putInstruction(new Swap());

        // Math
        putInstruction(new Iadd());
        putInstruction(new Ladd());
        putInstruction(new Fadd());
        putInstruction(new Dadd());
        putInstruction(new Isub());
        putInstruction(new Lsub());
        putInstruction(new Fsub());
        putInstruction(new Dsub());
        putInstruction(new Imul());
        putInstruction(new Lmul());
        putInstruction(new Fmul());
        putInstruction(new Dmul());
        putInstruction(new Idiv());
        putInstruction(new Ldiv());
        putInstruction(new Fdiv());
        putInstruction(new Ddiv());
        putInstruction(new Irem());
        putInstruction(new Lrem());
        putInstruction(new Frem());
        putInstruction(new Drem());
        putInstruction(new Ineg());
        putInstruction(new Lneg());
        putInstruction(new Fneg());
        putInstruction(new Dneg());
        putInstruction(new Ishl());
        putInstruction(new Lshl());
        putInstruction(new Ishr());
        putInstruction(new Lshr());
        putInstruction(new Iushr());
        putInstruction(new Lushr());
        putInstruction(new Iand());
        putInstruction(new Land());
        putInstruction(new Ior());
        putInstruction(new Lor());
        putInstruction(new Ixor());
        putInstruction(new Lxor());
        putInstruction(new Iinc());

        // Conversions
        putInstruction(new I2L());
        putInstruction(new I2F());
        putInstruction(new I2D());
        putInstruction(new L2I());
        putInstruction(new L2F());
        putInstruction(new L2D());
        putInstruction(new F2I());
        putInstruction(new F2L());
        putInstruction(new F2D());
        putInstruction(new D2I());
        putInstruction(new D2L());
        putInstruction(new D2F());
        putInstruction(new I2B());
        putInstruction(new I2C());
        putInstruction(new I2S());

        // Comparisons
        putInstruction(new Lcmp());
        putInstruction(new Fcmpl());
        putInstruction(new Fcmpg());
        putInstruction(new Dcmpl());
        putInstruction(new Dcmpg());
        putInstruction(new Ifeq());
        putInstruction(new Ifne());
        putInstruction(new Iflt());
        putInstruction(new Ifge());
        putInstruction(new Ifgt());
        putInstruction(new Ifle());
        putInstruction(new IfIcmpeq());
        putInstruction(new IfIcmpne());
        putInstruction(new IfIcmplt());
        putInstruction(new IfIcmpge());
        putInstruction(new IfIcmpgt());
        putInstruction(new IfIcmple());
        putInstruction(new IfAcmpeq());
        putInstruction(new IfAcmpne());

        // Control
        putInstruction(new Goto());
        putInstruction(new Jsr());
        putInstruction(new Ret());
        putInstruction(new Tableswitch());
        putInstruction(new Lookupswitch());
        putInstruction(new Ireturn());
        putInstruction(new Lreturn());
        putInstruction(new Freturn());
        putInstruction(new Dreturn());
        putInstruction(new Areturn());
        putInstruction(new Return());

        // References
        putInstruction(new Getstatic());
        putInstruction(new Putstatic());
        putInstruction(new Getfield());
        putInstruction(new Putfield());
        putInstruction(new Invokevirtual());
        putInstruction(new Invokespecial());
        putInstruction(new Invokestatic());
        putInstruction(new Invokeinterface());
        putInstruction(new Invokedynamic());
        putInstruction(new New());
        putInstruction(new Newarray());
        putInstruction(new Anewarray());
        putInstruction(new Arraylength());
        putInstruction(new Athrow());
        putInstruction(new Checkcast());
        putInstruction(new Instanceof());
        putInstruction(new Monitorenter());
        putInstruction(new Monitorexit());

        // Extended
        putInstruction(new Wide());
        putInstruction(new Multianewarray());
        putInstruction(new Ifnull());
        putInstruction(new Ifnonnull());
        putInstruction(new Gotow());
        putInstruction(new Jsrw());

        // Reserved
        putInstruction(new Breakpoint());
        putInstruction(new Impdep1());
        putInstruction(new Impdep2());
    }

    public static Instruction getByOpcode(int opcode) {
        Instruction instruction = codeMap.get(opcode);
        if (instruction == null) {
            throw new RuntimeException("No operation code: " + opcode);
        }
        return instruction;
    }

    public static Instruction getByOpName(String name) {
        Instruction instruction = nameMap.get(name);
        if (instruction == null) {
            throw new RuntimeException("No operation name: " + name);
        }
        return instruction;
    }

    private static void putInstruction(Instruction instruction) {
        codeMap.put(instruction.getOpCode(), instruction);
        nameMap.put(instruction.getOpName(), instruction);
    }
}
