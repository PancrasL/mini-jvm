package indi.pancras.jvm.instruction;

import java.util.HashMap;
import java.util.Map;

import indi.pancras.jvm.instruction.comparison.Dcmpg;
import indi.pancras.jvm.instruction.comparison.Dcmpl;
import indi.pancras.jvm.instruction.comparison.Fcmpg;
import indi.pancras.jvm.instruction.comparison.Fcmpl;
import indi.pancras.jvm.instruction.comparison.IfAcmpeq;
import indi.pancras.jvm.instruction.comparison.IfAcmpne;
import indi.pancras.jvm.instruction.comparison.IfIcmpeq;
import indi.pancras.jvm.instruction.comparison.IfIcmpge;
import indi.pancras.jvm.instruction.comparison.IfIcmpgt;
import indi.pancras.jvm.instruction.comparison.IfIcmple;
import indi.pancras.jvm.instruction.comparison.IfIcmplt;
import indi.pancras.jvm.instruction.comparison.IfIcmpne;
import indi.pancras.jvm.instruction.comparison.Ifeq;
import indi.pancras.jvm.instruction.comparison.Ifge;
import indi.pancras.jvm.instruction.comparison.Ifgt;
import indi.pancras.jvm.instruction.comparison.Ifle;
import indi.pancras.jvm.instruction.comparison.Iflt;
import indi.pancras.jvm.instruction.comparison.Ifne;
import indi.pancras.jvm.instruction.comparison.Lcmp;
import indi.pancras.jvm.instruction.constant.AconstNull;
import indi.pancras.jvm.instruction.constant.BIpush;
import indi.pancras.jvm.instruction.constant.Dconst0;
import indi.pancras.jvm.instruction.constant.Dconst1;
import indi.pancras.jvm.instruction.constant.Fconst0;
import indi.pancras.jvm.instruction.constant.Fconst1;
import indi.pancras.jvm.instruction.constant.Fconst2;
import indi.pancras.jvm.instruction.constant.Iconst0;
import indi.pancras.jvm.instruction.constant.Iconst1;
import indi.pancras.jvm.instruction.constant.Iconst2;
import indi.pancras.jvm.instruction.constant.Iconst3;
import indi.pancras.jvm.instruction.constant.Iconst4;
import indi.pancras.jvm.instruction.constant.Iconst5;
import indi.pancras.jvm.instruction.constant.Iconstm1;
import indi.pancras.jvm.instruction.constant.LDC;
import indi.pancras.jvm.instruction.constant.LDC2W;
import indi.pancras.jvm.instruction.constant.LDCW;
import indi.pancras.jvm.instruction.constant.Lconst0;
import indi.pancras.jvm.instruction.constant.Lconst1;
import indi.pancras.jvm.instruction.constant.Nop;
import indi.pancras.jvm.instruction.constant.SIpush;
import indi.pancras.jvm.instruction.control.Areturn;
import indi.pancras.jvm.instruction.control.Dreturn;
import indi.pancras.jvm.instruction.control.Freturn;
import indi.pancras.jvm.instruction.control.Goto;
import indi.pancras.jvm.instruction.control.Ireturn;
import indi.pancras.jvm.instruction.control.Jsr;
import indi.pancras.jvm.instruction.control.Lookupswitch;
import indi.pancras.jvm.instruction.control.Lreturn;
import indi.pancras.jvm.instruction.control.Ret;
import indi.pancras.jvm.instruction.control.Return;
import indi.pancras.jvm.instruction.control.Tableswitch;
import indi.pancras.jvm.instruction.conversion.D2F;
import indi.pancras.jvm.instruction.conversion.D2I;
import indi.pancras.jvm.instruction.conversion.D2L;
import indi.pancras.jvm.instruction.conversion.F2D;
import indi.pancras.jvm.instruction.conversion.F2I;
import indi.pancras.jvm.instruction.conversion.F2L;
import indi.pancras.jvm.instruction.conversion.I2B;
import indi.pancras.jvm.instruction.conversion.I2C;
import indi.pancras.jvm.instruction.conversion.I2D;
import indi.pancras.jvm.instruction.conversion.I2F;
import indi.pancras.jvm.instruction.conversion.I2L;
import indi.pancras.jvm.instruction.conversion.I2S;
import indi.pancras.jvm.instruction.conversion.L2D;
import indi.pancras.jvm.instruction.conversion.L2F;
import indi.pancras.jvm.instruction.conversion.L2I;
import indi.pancras.jvm.instruction.load.AAload;
import indi.pancras.jvm.instruction.load.Aload;
import indi.pancras.jvm.instruction.load.Aload0;
import indi.pancras.jvm.instruction.load.Aload1;
import indi.pancras.jvm.instruction.load.Aload2;
import indi.pancras.jvm.instruction.load.Aload3;
import indi.pancras.jvm.instruction.load.BAload;
import indi.pancras.jvm.instruction.load.CAload;
import indi.pancras.jvm.instruction.load.DAload;
import indi.pancras.jvm.instruction.load.Dload;
import indi.pancras.jvm.instruction.load.Dload0;
import indi.pancras.jvm.instruction.load.Dload1;
import indi.pancras.jvm.instruction.load.Dload2;
import indi.pancras.jvm.instruction.load.Dload3;
import indi.pancras.jvm.instruction.load.FAload;
import indi.pancras.jvm.instruction.load.Fload;
import indi.pancras.jvm.instruction.load.Fload0;
import indi.pancras.jvm.instruction.load.Fload1;
import indi.pancras.jvm.instruction.load.Fload2;
import indi.pancras.jvm.instruction.load.Fload3;
import indi.pancras.jvm.instruction.load.IAload;
import indi.pancras.jvm.instruction.load.Iload;
import indi.pancras.jvm.instruction.load.Iload0;
import indi.pancras.jvm.instruction.load.Iload1;
import indi.pancras.jvm.instruction.load.Iload2;
import indi.pancras.jvm.instruction.load.Iload3;
import indi.pancras.jvm.instruction.load.LAload;
import indi.pancras.jvm.instruction.load.Lload;
import indi.pancras.jvm.instruction.load.Lload0;
import indi.pancras.jvm.instruction.load.Lload1;
import indi.pancras.jvm.instruction.load.Lload2;
import indi.pancras.jvm.instruction.load.Lload3;
import indi.pancras.jvm.instruction.load.SAload;
import indi.pancras.jvm.instruction.math.Dadd;
import indi.pancras.jvm.instruction.math.Ddiv;
import indi.pancras.jvm.instruction.math.Dmul;
import indi.pancras.jvm.instruction.math.Dneg;
import indi.pancras.jvm.instruction.math.Drem;
import indi.pancras.jvm.instruction.math.Dsub;
import indi.pancras.jvm.instruction.math.Fadd;
import indi.pancras.jvm.instruction.math.Fdiv;
import indi.pancras.jvm.instruction.math.Fmul;
import indi.pancras.jvm.instruction.math.Fneg;
import indi.pancras.jvm.instruction.math.Frem;
import indi.pancras.jvm.instruction.math.Fsub;
import indi.pancras.jvm.instruction.math.Iadd;
import indi.pancras.jvm.instruction.math.Iand;
import indi.pancras.jvm.instruction.math.Idiv;
import indi.pancras.jvm.instruction.math.Iinc;
import indi.pancras.jvm.instruction.math.Imul;
import indi.pancras.jvm.instruction.math.Ineg;
import indi.pancras.jvm.instruction.math.Ior;
import indi.pancras.jvm.instruction.math.Irem;
import indi.pancras.jvm.instruction.math.Ishl;
import indi.pancras.jvm.instruction.math.Ishr;
import indi.pancras.jvm.instruction.math.Isub;
import indi.pancras.jvm.instruction.math.Iushr;
import indi.pancras.jvm.instruction.math.Ixor;
import indi.pancras.jvm.instruction.math.Ladd;
import indi.pancras.jvm.instruction.math.Land;
import indi.pancras.jvm.instruction.math.Ldiv;
import indi.pancras.jvm.instruction.math.Lmul;
import indi.pancras.jvm.instruction.math.Lneg;
import indi.pancras.jvm.instruction.math.Lor;
import indi.pancras.jvm.instruction.math.Lrem;
import indi.pancras.jvm.instruction.math.Lshl;
import indi.pancras.jvm.instruction.math.Lshr;
import indi.pancras.jvm.instruction.math.Lsub;
import indi.pancras.jvm.instruction.math.Lushr;
import indi.pancras.jvm.instruction.math.Lxor;
import indi.pancras.jvm.instruction.stack.Dup;
import indi.pancras.jvm.instruction.stack.Dup2;
import indi.pancras.jvm.instruction.stack.Dup2x1;
import indi.pancras.jvm.instruction.stack.Dup2x2;
import indi.pancras.jvm.instruction.stack.Dupx1;
import indi.pancras.jvm.instruction.stack.Dupx2;
import indi.pancras.jvm.instruction.stack.Pop;
import indi.pancras.jvm.instruction.stack.Pop2;
import indi.pancras.jvm.instruction.stack.Swap;
import indi.pancras.jvm.instruction.store.AAstore;
import indi.pancras.jvm.instruction.store.Astore;
import indi.pancras.jvm.instruction.store.Astore0;
import indi.pancras.jvm.instruction.store.Astore1;
import indi.pancras.jvm.instruction.store.Astore2;
import indi.pancras.jvm.instruction.store.Astore3;
import indi.pancras.jvm.instruction.store.BAstore;
import indi.pancras.jvm.instruction.store.CAstore;
import indi.pancras.jvm.instruction.store.DAstore;
import indi.pancras.jvm.instruction.store.Dstore;
import indi.pancras.jvm.instruction.store.Dstore0;
import indi.pancras.jvm.instruction.store.Dstore1;
import indi.pancras.jvm.instruction.store.Dstore2;
import indi.pancras.jvm.instruction.store.Dstore3;
import indi.pancras.jvm.instruction.store.FAstore;
import indi.pancras.jvm.instruction.store.Fstore;
import indi.pancras.jvm.instruction.store.Fstore0;
import indi.pancras.jvm.instruction.store.Fstore1;
import indi.pancras.jvm.instruction.store.Fstore2;
import indi.pancras.jvm.instruction.store.Fstore3;
import indi.pancras.jvm.instruction.store.IAstore;
import indi.pancras.jvm.instruction.store.Istore;
import indi.pancras.jvm.instruction.store.Istore0;
import indi.pancras.jvm.instruction.store.Istore1;
import indi.pancras.jvm.instruction.store.Istore2;
import indi.pancras.jvm.instruction.store.Istore3;
import indi.pancras.jvm.instruction.store.LAstore;
import indi.pancras.jvm.instruction.store.Lstore;
import indi.pancras.jvm.instruction.store.Lstore0;
import indi.pancras.jvm.instruction.store.Lstore1;
import indi.pancras.jvm.instruction.store.Lstore2;
import indi.pancras.jvm.instruction.store.Lstore3;
import indi.pancras.jvm.instruction.store.SAstore;


public class InstructionFactory {
    private static Map<Integer, Instruction> codeMap = new HashMap<>();
    private static Map<String, Instruction> nameMap = new HashMap<>();

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
        putInstruction(new Lload3());
        putInstruction(new Fload0());
        putInstruction(new Fload1());
        putInstruction(new Fload2());
        putInstruction(new Fload3());
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


        putInstruction(new gotow());
        putInstruction(new ifnotnull());
        putInstruction(new ifnull());
        putInstruction(new wide());


    }

    public static Instruction getByOpcode(int opcode) throws Exception {
        Instruction instruction = codeMap.get(opcode);
        if (instruction == null) {
            throw new Exception("no operation code");
        }
        return instruction;
    }

    public static Instruction getByOpcode(String name) throws Exception {
        Instruction instruction = codeMap.get(name);
        if (instruction == null) {
            throw new Exception("no operation code");
        }
        return instruction;
    }

    private static void putInstruction(Instruction instruction) {
        codeMap.put(instruction.getOpCode(), instruction);
        nameMap.put(instruction.getReName(), instruction);
    }

}
