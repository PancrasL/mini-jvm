package indi.pancras.jvm;

import indi.pancras.jvm.classfile.attribute.attrinfo.CodeAttr;
import indi.pancras.jvm.classfile.method.MethodInfo;
import indi.pancras.jvm.instruction.BytecodeReader;
import indi.pancras.jvm.instruction.Instruction;
import indi.pancras.jvm.instruction.InstructionFactory;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.Thread;

public class Interpreter {
    public static void execute(MethodInfo methodInfo) {
        if (methodInfo == null) {
            throw new IllegalArgumentException("MethodInfo is null");
        }
        CodeAttr code = methodInfo.getCodeAttr();
        int maxLocals = code.getMaxLocals();
        int maxStack = code.getMaxStack();
        byte[] byteCode = code.getCode();

        Thread thread = new Thread(maxStack);
        Frame frame = new Frame(thread, maxLocals, maxStack);
        thread.pushFrame(frame);

        executeCode(thread, byteCode);
    }

    private static void executeCode(Thread thread, byte[] byteCode) {
        Frame frame = thread.popFrame();
        BytecodeReader reader = new BytecodeReader(byteCode, frame.getNextPc());
        int opCode;
        do {
            int pc = frame.getNextPc();
            thread.setPc(pc);

            // decode
            reader.reset(byteCode, pc);
            opCode = reader.read8();
            Instruction instruction = InstructionFactory.getByOpcode(opCode);
            instruction.fetchOperands(reader);
            System.out.printf("pc: %d, opName: %s%n", frame.getNextPc(), instruction.getOpName());
            frame.setNextPc(reader.getPc());

            // execute
            instruction.execute(frame);
            System.out.println(frame);
        } while (opCode != 0xb1);
    }
}
